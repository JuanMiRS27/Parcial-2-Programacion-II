import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { User } from '../models/user.model';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class UsersService {
  private baseUrl = `${environment.authApiUrl}/api/admin/users`;
  constructor(private http: HttpClient) {}
  findAll(): Observable<User[]> {
    return this.http.get<any>(this.baseUrl).pipe(
      map((res) => Array.isArray(res) ? res : (Array.isArray(res?.value) ? res.value : []))
    );
  }
  delete(id: number): Observable<void> { return this.http.delete<void>(`${this.baseUrl}/${id}`); }
  updateRole(id: number, role: User['role']): Observable<User> {
    return this.http.put<User>(`${this.baseUrl}/${id}/role`, { role });
  }
}
