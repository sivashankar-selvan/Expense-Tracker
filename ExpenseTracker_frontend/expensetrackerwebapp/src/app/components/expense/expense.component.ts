import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { ExpenseService } from 'src/app/services/expense/expense.service';
import { StatsService } from 'src/app/services/stats/stats.service';

@Component({
  selector: 'app-expense',
  templateUrl: './expense.component.html',
  styleUrls: ['./expense.component.scss'],
})
export class ExpenseComponent implements OnInit {
  expenseForm!: FormGroup;

  // Define category list with specific type
  listOfCategory: string[] = [
    'Education',
    'Groceries',
    'Health',
    'Subscription',
    'Takeaways',
    'Clothing',
    'Travel',
    'Miscellaneous',
  ];

  expenses:any;


  constructor(
    private fb: FormBuilder,
    private expenseService: ExpenseService,
    private message: NzMessageService,
    private router:Router,
    private statsService:StatsService
  ) {}

  // Correct lifecycle hook
  ngOnInit(): void {
    // Initialize the form
    this.getAllExpense();
    this.expenseForm = this.fb.group({
      title: ['', Validators.required],
      amount: [null, [Validators.required, Validators.min(0)]],
      date: [null, Validators.required],
      category: [null, Validators.required],
      description: ['', Validators.required],
    });
  }

  // Submit form method
  submitForm(): void {
    if (this.expenseForm.invalid) {
      this.message.error('Please fill out all required fields.', { nzDuration: 5000 });
      return;
    }

    // Call the service to post the expense
    this.expenseService.postExpense(this.expenseForm.value).subscribe(
      (res) => {
        this.message.success('Expense posted successfully.', { nzDuration: 5000 });
        this.expenseForm.reset(); // Clear the form on success
        this.getAllExpense(); // Refresh the expense list
      },
      (error) => {
        this.message.error('Error while posting expense.', { nzDuration: 5000 });
      }
    );
  }

  getAllExpense(){
    this.expenseService.getAllExpenses().subscribe(res=>{
      this.expenses=res;
      console.log(this.expenses);

    });
  }

  updateExpense(id:number){
    this.router.navigate(['/expense/'+id+'/edit']);
  }


  deleteExpense(id:number){
    this.expenseService.deleteExpense(id).subscribe(res=>{
      this.message.success('Expense deleted successfully.', { nzDuration: 5000 });
      this.getAllExpense(); // Refresh the expense list
    },
    (error) => {
      this.message.error('Error while deleting expense.', { nzDuration: 5000 });
    });
  }

  
 
}
