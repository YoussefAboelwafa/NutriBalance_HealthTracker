import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
const baseUrl = 'http://localhost:8080';
@Injectable({
  providedIn: 'root'
})
export class signinService {

  constructor(private http: HttpClient) {}
  usersignin(params:HttpParams): Observable<any> {
    return this.http.get<any>(`${baseUrl}/user/usersignin`,{params});
  }
  coachsignin(params:HttpParams): Observable<any> {
    return this.http.post<any>(`${baseUrl}/coach/coachsignin`,{params});
  }
  adminsignin(params:HttpParams): Observable<any> {
    return this.http.post<any>(`${baseUrl}/admin/coachsignin`,{params});
  }
}