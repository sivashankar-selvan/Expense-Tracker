import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

// const baseUrl = 'http://localhost:8081/';

// const baseUrl = 'http://localhost:5001/';

const baseUrl = 'http://expensetracker-env.eba-vp6hmw64.eu-north-1.elasticbeanstalk.com/';

@Injectable({
  providedIn: 'root'
})
export class StatsService {

  constructor(private http:HttpClient) { }

  getStats():Observable<any>{
    return this.http.get(baseUrl+"api/stats");
  }

  getChart():Observable<any>{
    return this.http.get(baseUrl+"api/stats/chart");
  }
}
