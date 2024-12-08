import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


// const baseUrl = 'http://localhost:5001/';
const baseUrl = 'http://expensetracker-env.eba-vp6hmw64.eu-north-1.elasticbeanstalk.com/';

@Injectable({
  providedIn: 'root'
})
export class ExpenseService {

  constructor(private http:HttpClient) { }

  postExpense(expenseDTO:any):Observable<any>{
    return this.http.post(baseUrl+"api/expense",expenseDTO);
  }

  getAllExpenses():Observable<any>{
    return this.http.get(baseUrl+"api/expense/all");
  }

  deleteExpense(id:number):Observable<any>{
    return this.http.delete(baseUrl+"api/expense/"+id);
    
  }

  getExpenseById(id:number):Observable<any>{
    return this.http.get(baseUrl+"api/expense/"+id);
  }

  updateExpense(id:number,expenseDTO:any):Observable<any>{
    return this.http.put(baseUrl+"api/expense/"+id,expenseDTO);
  }
}
