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
  getwaitingcoaches(): Observable<any> {
    return this.http.get<any>(`${baseUrl}/get_waiting_coaches`);
  }
  deleteCoach(id:any): Observable<string> {
    return this.http.delete(`${baseUrl}/delete/${id}`,{ responseType: 'text' });
  }
  approveCoach(id:any): Observable<any> {
    return this.http.get<any>(`${baseUrl}/approve/${id}`);
  }
  checksignin(email:string,password:string): Observable<any> {
    return this.http.get<any>(`${baseUrl}/checksignin/${email}/${password}`);
  }
}
