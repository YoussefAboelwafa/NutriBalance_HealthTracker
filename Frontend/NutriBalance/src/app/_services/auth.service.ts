import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AppConstants} from '../common/app.constants';

const baseUrl = 'http://localhost:8080';
const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) {
  }

  login(credentials: any): Observable<any> {
    return this.http.post(AppConstants.AUTH_API + 'signin', {
      email: credentials.username,
      password: credentials.password
    }, httpOptions);
  }

  register(user: any): Observable<any> {
    return this.http.post(AppConstants.AUTH_API + 'signup', {
      displayName: user.displayName,
      email: user.email,
      password: user.password,
      matchingPassword: user.matchingPassword,
      socialProvider: 'LOCAL'
    }, httpOptions);
  }
  verify(token: any): Observable<any> {
    return this.http.get(AppConstants.AUTH_API + "/verify", {params: {'code': token}});
  }

  forgetPassword(email: any, role: any): Observable<any> {
    return this.http.get(baseUrl + "/forgetPassword", {params: {'email': email, 'role': role}});
  }

  checkOtp(otp: any, email: any): Observable<any> {
    return this.http.get(baseUrl + "/checkOtp", {params: {'otp': otp, 'email': email}});
  }

  resetPassword(email: any, pass: any, role: any): Observable<any> {
    const params = new HttpParams().set('role', role);
    return this.http.post(
      baseUrl + "/resetPassword",
      {login: email, password: pass},
      {params}
    );
  }

}
