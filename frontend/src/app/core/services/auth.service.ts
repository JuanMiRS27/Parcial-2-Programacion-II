import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { AuthResponse } from '../models/auth-response.model';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private baseUrl = `${environment.authApiUrl}/api/auth`;
  constructor(private http: HttpClient) {}

  register(payload: { nombre: string; email: string; password: string }): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, payload);
  }

  login(payload: { email: string; password: string }): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.baseUrl}/login`, payload).pipe(
      tap((res) => {
        localStorage.setItem('token', res.token);
        localStorage.setItem('role', res.user.role);
      })
    );
  }

  me() { return this.http.get(`${this.baseUrl}/me`); }
  logout() { localStorage.clear(); }
  hasToken() { return !!localStorage.getItem('token'); }
  isAdmin() { return localStorage.getItem('role') === 'ADMIN'; }
}
