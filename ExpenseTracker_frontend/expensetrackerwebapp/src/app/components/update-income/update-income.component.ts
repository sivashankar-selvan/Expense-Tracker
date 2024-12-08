import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { IncomeService } from 'src/app/services/income/income.service';

@Component({
  selector: 'app-update-income',
  templateUrl: './update-income.component.html',
  styleUrls: ['./update-income.component.scss']
})
export class UpdateIncomeComponent {

  id:number=this.activatedRoute.snapshot.params['id'];
  incomeForm!: FormGroup;
  listOfCategory: string[] = ["salary", "Freelancing", "loan", "Stocks", "Bitcoin", "Bank Interest", "Bank Transfer", "Other"];
  incomeId: any;

  constructor(
    private fb: FormBuilder,
    private message: NzMessageService,
    private router: Router,
    private activatedRoute: ActivatedRoute, // Correctly injected ActivatedRoute
    private incomeService: IncomeService,
    private datePipe: DatePipe
  ) { }

  ngOnInit() {
    // Use activatedRoute to get the ID from URL params
    this.incomeId = this.activatedRoute.snapshot.paramMap.get('id');
    console.log('Income ID:', this.incomeId);

    this.incomeForm = this.fb.group({
      title: [null, Validators.required],
      amount: [null, [Validators.required]],
      date: [null, [Validators.required]],
      category: [null, [Validators.required]],
      description: [null, [Validators.required]]
    });
    this.getIncomeById();
  }

  submitForm() {
    // Handle form submission
    this.incomeService.updateIncome(this.incomeId, this.incomeForm.value).subscribe(res => {
      this.message.success('Income updated successfully', { nzDuration: 5000 });
      this.router.navigate(['/income']);
    },error=>{
      this.message.error("Error while updating income",{nzDuration:5000});
    });
  }

  getIncomeById() {
    // Get income by ID
    this.incomeService.getIncomeById(this.incomeId).subscribe(res => {
      this.incomeForm.patchValue(res);
    },error=>{
      this.message.error("Something went wrong",{nzDuration:5000});
    })
  }
}
