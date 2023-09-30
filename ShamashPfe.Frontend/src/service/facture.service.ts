import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Facture } from 'src/modal/facture.model';

@Injectable({
  providedIn: 'root'
})
export class FactureService {

  baseUrl: string = `${environment.BaseUrl}/facture`

  constructor(private http: HttpClient) { }

  // addDemande(idUser: number, facture: Facture): Observable<any> {
  //   const url = `${this.baseUrl}/addDemande/${idUser}`;
  //   return this.http.post(url, facture);
  // }
  addDemande(idUser: number, facture: any, files: File[]): Observable<any> {
    const formData = new FormData();
    formData.append('facture', new Blob([JSON.stringify(facture)], { type: 'application/json' }));
    
    for (let i = 0; i < files.length; i++) {
      formData.append('files', files[i]);
    }
    
    return this.http.post(`${this.baseUrl}/addDemande/${idUser}`, formData);
  
  }
  
  getFacturesForUser(idUser: number): Observable<any> {
    const url = `${this.baseUrl}/getFactureUser/${idUser}`;
    return this.http.get(url);
  }

  getFactureForUser(idUser: number): Observable<any> {
    const url = `${this.baseUrl}/getFacture/${idUser}`;
    return this.http.get(url);
  }

  getAllFactures(): Observable<any> {
    const url = `${this.baseUrl}/getAll`;
    return this.http.get(url);
  } 
  updateFactureState(idUser: number, newState: number): Observable<any> {
    const url = `${this.baseUrl}/etatFacture/${idUser}/${newState}`;
    return this.http.put(url, null);
  }
  getFactureFiles(idfacture: number): Observable<any[]> {
 
    return this.http.get<any[]>(`${this.baseUrl}/getFactureFile/${idfacture}`);
  }

}
