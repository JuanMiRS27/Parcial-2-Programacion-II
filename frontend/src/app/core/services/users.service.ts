import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class UsersService {
  private baseUrl = `${environment.authApiUrl}/api/admin/users`;
  constructor(private http: HttpClient) {}
  findAll(): Observable<User[]> { return this.http.get<User[]>(this.baseUrl); }
  delete(id: number): Observable<void> { return this.http.delete<void>(`${this.baseUrl}/${id}`); }
}
