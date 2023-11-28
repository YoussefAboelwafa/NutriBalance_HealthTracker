import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
const baseUrl = 'http://localhost:8080/coach';
@Injectable({
  providedIn: 'root'
})
export class CoachService {

  constructor(private http: HttpClient) {}
  saveCoach(formdata:FormData): Observable<any> {
    return this.http.post<any>(`${baseUrl}/save`,formdata);
  }
}
