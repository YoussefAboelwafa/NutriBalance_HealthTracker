import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConstants } from '../common/app.constants';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) { }

  login(credentials:any): Observable<any> {
    return this.http.post(AppConstants.AUTH_API + 'signin', {
      email: credentials.username,
      password: credentials.password
    }, httpOptions);
  }

  register(user:any): Observable<any> {
    return this.http.post(AppConstants.AUTH_API + 'signup', {
      displayName: user.displayName,
      email: user.email,
      password: user.password,
      matchingPassword: user.matchingPassword,
      socialProvider: 'LOCAL'
    }, httpOptions);
  }
  verify(token:any):Observable<any>{
    return this.http.get(AppConstants.AUTH_API+ `/auth/verify`,{params:{'code':token}} );
  }
  forgetPassword(email:any):Observable<any>{
    return this.http.get(AppConstants.AUTH_API+ `/auth/forgetPassword`, {params:{'email':email}} );
  }
  checkOtp(otp:any,email:any):Observable<any>{
    return this.http.get(AppConstants.AUTH_API+ `/auth/checkOtp`, {params:{'otp':otp,'email':email}} );
  }
  resetPassword(email:any,pass:any):Observable<any>{
    return this.http.post(AppConstants.AUTH_API+ `/auth/resetPassword`, {login:email,password:pass} );
  }

}
