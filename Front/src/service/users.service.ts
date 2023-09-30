import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { indevedu } from 'src/modal/indevedu.model';

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  baseUrl: string = `${environment.BaseUrl}/user`

  constructor(private http: HttpClient) { }


  getAllUsers(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/allUsers`);
  }

  getUserById(idUser: number): Observable<any> {
    const url = `${this.baseUrl}/getUserById/${idUser}`;
    return this.http.get<any>(url);
  }

  getIndeveduById(idUser: number): Observable<any> {
    const url = `${this.baseUrl}/getIndeveduById/${idUser}`;
    return this.http.get<any>(url);
  }
  activeUser(idUser: number): Observable<any> {
    const url = `${this.baseUrl}/activeUser/${idUser}`;
    return this.http.get<any>(url);
  }

  deleteUser(idUser: number): Observable<any> {
    const url = `${this.baseUrl}/deleteUser/${idUser}`;
    return this.http.delete<any>(url);
  }

  updateFicheIndevedu(idUser: any, indevedu: any){
    const url = `${this.baseUrl}/updateIndevedu/${idUser}`;
    return this.http.post(url, indevedu);
  }
}
