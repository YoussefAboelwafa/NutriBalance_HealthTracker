import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConstants } from '../common/app.constants';
import { User } from '../Objects/User';
const baseUrl = 'http://localhost:8080/user';
const httpOptions = {
		  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
		};


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }
  saveUser(user:User): Observable<any> {
    return this.http.post<any>(`${baseUrl}/save`,user);
  }
  checksignin(email:string,password:string): Observable<any> {
    return this.http.get<any>(`${baseUrl}/usersignin/${email}/${password}`);
  }

  getPublicContent(): Observable<any> {
    return this.http.get(AppConstants.API_URL + 'all', { responseType: 'text' });
  }

  getUserBoard(): Observable<any> {
    return this.http.get(AppConstants.API_URL + 'user', { responseType: 'text' });
  }

  getModeratorBoard(): Observable<any> {
    return this.http.get(AppConstants.API_URL + 'mod', { responseType: 'text' });
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(AppConstants.API_URL + 'admin', { responseType: 'text' });
  }

  getCurrentUser(): Observable<any> {
    return this.http.get(AppConstants.API_URL + 'user/me', httpOptions);
  }
  addImage(email:any,formdata:any){
    return this.http.post<any>(`${baseUrl}/addImageToUser/${email}`,formdata);
  }
  updateUser(user: any):Observable<any> {
    return this.http.put<any>(`${baseUrl}/updateUser`,user);
  }
  getFoodCalorie(): Observable<any> {
    return this.http.get<any>(`${baseUrl}/food_calorie`);
  }
}
