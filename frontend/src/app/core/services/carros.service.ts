import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CarroTaller } from '../models/carro-taller.model';
import { map, Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class CarrosService {
  private baseUrl = `${environment.tallerApiUrl}/api/carros`;
  constructor(private http: HttpClient) {}

  findAll(): Observable<CarroTaller[]> {
    return this.http.get<any>(this.baseUrl).pipe(
      map((res) => Array.isArray(res) ? res : (Array.isArray(res?.value) ? res.value : []))
    );
  }
  findById(id: number): Observable<CarroTaller> { return this.http.get<CarroTaller>(`${this.baseUrl}/${id}`); }
  create(payload: CarroTaller): Observable<CarroTaller> { return this.http.post<CarroTaller>(this.baseUrl, payload); }
  update(id: number, payload: CarroTaller): Observable<CarroTaller> { return this.http.put<CarroTaller>(`${this.baseUrl}/${id}`, payload); }
  updateEstado(id: number, estado: CarroTaller['estado']): Observable<CarroTaller> {
    return this.http.put<CarroTaller>(`${this.baseUrl}/${id}/estado`, { estado });
  }
  delete(id: number): Observable<void> { return this.http.delete<void>(`${this.baseUrl}/${id}`); }
}
