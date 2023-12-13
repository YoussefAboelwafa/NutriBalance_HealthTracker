import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConstants } from '../common/app.constants';
import { Plan } from '../Objects/Plan';
const baseUrl = 'http://localhost:8080/plan';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class PlanService {

  constructor(private http: HttpClient) {}
  savePlan(plan: Plan): Observable<any> {
    return this.http.post<any>(`${baseUrl}/save`, plan);
  }
  getAllPlans(): Observable<any> {
    return this.http.get<any>(`${baseUrl}/getall`);
  }
}
