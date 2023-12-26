import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../Objects/User';
import { Coach } from '../Objects/Coach';


@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  getReports(){
    return this.http.get<any>(`${'http://localhost:8080/report'}/get_reports`);
  }
  deleteUser(user:any){
    this.http.get<any>(`${'http://localhost:8080/user'}/deleteuser`,user)
  }
  deleteCoach(coach:any){
    this.http.get<any>(`${'http://localhost:8080/coach'}/deletecoach`,coach)
  }
}
