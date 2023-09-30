import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  baseUrl: string = `${environment.BaseUrl}/auth`
  private tokenKey = 'token'; // Key to access the token in local storage

  constructor(private http: HttpClient) { }

  authenticateUser(loginRequest: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/signin`, loginRequest);
  }
  registerUser(signUpData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/signup`, signUpData);
  }
  isLoggedIn(): boolean {
    const token = localStorage.getItem(this.tokenKey);
    return token !== null; // Return true if token is present, else false
  }
  logout(): void {
    localStorage.removeItem(this.tokenKey);
  }

}
