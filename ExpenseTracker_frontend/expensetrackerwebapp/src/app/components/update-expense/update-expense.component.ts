import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { ExpenseService } from 'src/app/services/expense/expense.service';

@Component({
  selector: 'app-update-expense',
  templateUrl: './update-expense.component.html',
  styleUrls: ['./update-expense.component.scss']
})
export class UpdateExpenseComponent {
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
  id:number=this.activatedRoute.snapshot.params['id'];

  constructor(
    private fb: FormBuilder,
    private expenseService: ExpenseService,
    private message: NzMessageService,
    private router:Router,
    private activatedRoute:ActivatedRoute
  ) {}

  // Correct lifecycle hook
  ngOnInit(): void {
    // Initialize the form
    this.expenseForm = this.fb.group({
      title: ['', Validators.required],
      amount: [null, [Validators.required, Validators.min(0)]],
      date: [null, Validators.required],
      category: [null, Validators.required],
      description: ['', Validators.required],
    });
    this.getExpenseById();
  }

  getExpenseById() {
    this.expenseService.getExpenseById(this.id).subscribe(res=>{
      this.expenseForm.patchValue(res);
    },error=>{
      this.message.error("Something went wrong",{nzDuration:5000});
    })
  }
  submitForm(): void {
    this.expenseService.updateExpense(this.id,this.expenseForm.value).subscribe(res=>{
      this.message.success("Expense updated successfully",{nzDuration:5000});
      this.router.navigate(['/expense']);
    },error=>{
      this.message.error("Something went wrong",{nzDuration:5000});
    })
  }
}
