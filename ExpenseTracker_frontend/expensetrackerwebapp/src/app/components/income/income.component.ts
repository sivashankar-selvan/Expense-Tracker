import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { IncomeService } from 'src/app/services/income/income.service';


@Component({
  selector: 'app-income',
  templateUrl: './income.component.html',
  styleUrls: ['./income.component.scss'],
  providers: [DatePipe]
})
export class IncomeComponent {

  incomes:any;
  incomeForm!: FormGroup;
  listOfCategory: string[] = ["salary", "Freelancing", "loan", "Stocks","Bitcoin","Bank Interest","Bank Transfer","Other"];

  constructor(private fb: FormBuilder, private message: NzMessageService,private router:Router,
    private activatedRoute:ActivatedRoute,
    private incomeService:IncomeService,
    private datePipe: DatePipe
  ) { }


  ngOnInit(){
    this.getAllIncome();
    this.incomeForm = this.fb.group({
      title: [null, Validators.required],
      amount: [null, [Validators.required]],
      date: [null, [Validators.required]],
      category: [null, [Validators.required]],
      description: [null, [Validators.required]]
    });
    
  }

  submitForm(): void {
    // const formattedDate = this.datePipe.transform(this.incomeForm.value.incomedate, 'yyyy-MM-dd');
    // this.incomeForm.patchValue({ incomedate: formattedDate });
  
    // console.log(this.incomeForm.value); // Ensure the date is transformed

    this.incomeService.postIncome(this.incomeForm.value).subscribe(res=>{
      this.message.success("Income posted successfully",{nzDuration:5000});
      this.getAllIncome();
    },error=>{
      this.message.error("Error while posting income",{nzDuration:5000});
    });
    
  }

  getAllIncome(){
    this.incomeService.getAllIncomes().subscribe(res=>{
      this.incomes=res;
    },error=>{
      this.message.error("Error while fetching income",{nzDuration:5000});
    });
  }

  deleteIncome(id:number){
    this.incomeService.deleteIncome(id).subscribe(res=>{
      this.message.success("Income deleted successfully",{nzDuration:5000});
      this.getAllIncome();
    },error=>{
      this.message.error("Error while deleting income",{nzDuration:5000});
    });
  }
}

