import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ContratService {

  baseUrl: string = `${environment.BaseUrl}/contrat`

  constructor(private http: HttpClient) { }

  updateContrat(idContrat: number, contrat: any): Observable<any> {
    const url = `${this.baseUrl}/addDemande/${idContrat}`;
    return this.http.post(url, contrat);
  }
  getContratById(id: number): Observable<any> {
    const url = `${this.baseUrl}/getContrat/${id}`;
    return this.http.get(url);
  }

  
  getAllContratsForUser(id: number): Observable<any> {
    const url = `${this.baseUrl}/getAll/${id}`;
    return this.http.get(url);
  }
  changeContratState(idContrat: number, etat: boolean): Observable<any> {
    const url = `${this.baseUrl}/ChangeEtat/${idContrat}/${etat}`;
    return this.http.put(url, null); 
  }

  getAllContrats(): Observable<any> {
    const url = `${this.baseUrl}/getAll`;
    return this.http.get(url);
  }



  getNbrFacture(): Observable<number> {
    const url = `${this.baseUrl}/getNbrFacture`;
    return this.http.get<number>(url);
  }

  getNbrContrat(): Observable<number> {
    const url = `${this.baseUrl}/getNbrContrat`;
    return this.http.get<number>(url);
  }

  getNbrUser(): Observable<number> {
    const url = `${this.baseUrl}/getNbrUser`;
    return this.http.get<number>(url);
  }

  getNbrIndevedu(): Observable<number> {
    const url = `${this.baseUrl}/getNbrIndevedu`;
    return this.http.get<number>(url);
  }
}
