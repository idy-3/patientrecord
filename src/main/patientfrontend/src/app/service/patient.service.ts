import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Patient } from '../patient';
import { environment } from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class PatientService {
    private apiService = environment.apiBaseUrl;

    constructor(private http: HttpClient){}

    public getPatients(): Observable<Patient[]>{
        return this.http.get<Patient[]>(`${this.apiService}/patient/all`)
    }

    public addPatient(patient: Patient): Observable<Patient>{
        return this.http.post<Patient>(`${this.apiService}/patient/add`, patient)
    }

    public updatePatient(patient: Patient): Observable<Patient>{
        return this.http.put<Patient>(`${this.apiService}/patient/update`, patient)
    }

    public deletePatient(patientId: number): Observable<void>{
        return this.http.delete<void>(`${this.apiService}/patient/delete/${patientId}`)
    }
    
}