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
  deleteUser(id:any):Observable<any>{
    return this.http.delete<any>(`${'http://localhost:8080/user'}/deleteuser/${id}`)
  }
  deleteCoach(id:any):Observable<any>{
    return this.http.delete<any>(`${'http://localhost:8080/coach'}/deletecoach/${id}`)
  }
  deleteReport(user_id:any,coach_id:any):Observable<any>{
    return this.http.delete<any>(`${'http://localhost:8080/report'}/deletereport`,{params:{user_id:user_id,coach_id:coach_id}})
  }
}
