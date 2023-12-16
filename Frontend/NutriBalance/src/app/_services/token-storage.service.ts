import { Injectable } from '@angular/core';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';

const DATE_KEY = 'auth-date'
const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';
const COACH_KEY = 'auth-coach';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor(private sanitizer: DomSanitizer) { }

  signOut(): void {
    window.sessionStorage.clear();
  }

  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string | null {
    return sessionStorage.getItem(TOKEN_KEY) as string;
  }

  public savedate(date: any): void {
    window.sessionStorage.removeItem(DATE_KEY);
    window.sessionStorage.setItem(DATE_KEY, date);
  }

  public getdate(): any | null {
    return sessionStorage.getItem(DATE_KEY);
  }

  public saveUser(user: any) {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }
  public saveCoach(coach: any) {
    window.sessionStorage.removeItem(COACH_KEY);
    window.sessionStorage.setItem(COACH_KEY, JSON.stringify(coach));
  }

  public getUser(): any {
    return JSON.parse(sessionStorage.getItem(USER_KEY) as string);
  }
  public getCoach(): any {
    return JSON.parse(sessionStorage.getItem(COACH_KEY) as string);
  }
}

