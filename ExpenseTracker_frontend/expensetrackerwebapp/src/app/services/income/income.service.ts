import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

// const baseUrl = 'http://localhost:5001/';
const baseUrl = 'http://expensetracker-env.eba-vp6hmw64.eu-north-1.elasticbeanstalk.com/';

@Injectable({
  providedIn: 'root'
})
export class IncomeService {

  constructor(private http:HttpClient) { }

  postIncome(incomeDTO:any){
    return this.http.post(baseUrl+"api/income",incomeDTO);
  }

  getAllIncomes(){
    return this.http.get(baseUrl+"api/income/all");
  }

  getIncomeById(id:number){
    return this.http.get(baseUrl+"api/income/"+id);
  }

  updateIncome(id:number,incomeDTO:any){
    return this.http.put(baseUrl+"api/income/"+id,incomeDTO);
  }
  
  deleteIncome(id:number){
    return this.http.delete(baseUrl+"api/income/"+id);
  }
  
}
