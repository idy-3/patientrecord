import { User } from './user';
import { PatientService } from './service/patient.service';
import {AuthenticationService} from './service/authentication.service';
import { Patient } from './patient';
import { Component, OnInit, AfterViewInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, AfterViewInit {
  public patients: Patient[] = [];
  public editPatient!: Patient | null;
  public deletePatient!: Patient | null;
  public userRole: boolean = false;
  public isLoggedIn: boolean = false;
  public currentDate = new Date();
  public authUser!: string | null ;

  constructor(private patientService: PatientService, private authenticationService: AuthenticationService){}

  ngOnInit(){
    this.isLoggedIn = this.authenticationService.isUserLoggedIn();    
  }

  ngAfterViewInit() {
    if (!this.isLoggedIn){
      this.onOpenModal(null, 'login');
    }else{
      this.getPatients();
      this.getUserPermissions()
    }    
  }

  // Authentication Methods
  public handleJWTAuthLogin(user:User){
    this.authenticationService.executeJWTAuthenticationService(user)
    .subscribe(
      (response) => {
        this.isLoggedIn = this.authenticationService.isUserLoggedIn();
        this.getPatients();
        this.getUserPermissions()
        document.getElementById('login-form')?.click();
      },
      (error: HttpErrorResponse) => {        
        alert(error.error)
        // console.log(error);
      }
      ) 
  }

  public handleAuthRegister(user:User){
    this.authenticationService.registerUser(user).subscribe(
      (response: void) => {
        alert("New Patient Added")
      },(error: HttpErrorResponse) =>{
        alert(error.error)
      }
    )
  }

  public getAuthUser(){
    return this.authenticationService.getAuthenticatedUser();
  }

  public getUserPermissions(){
    
    this.authenticationService
    .getUserRole(
      this.authenticationService.getAuthenticatedUser()
    ).subscribe(
      (response: number) => {
        this.userRole = response === 1;        
      },(error: HttpErrorResponse)=>{
        console.log(error);
      }
    )
  }

  public handleJWTAuthLogout(){
    this.authenticationService.logout();
    window.location.replace('/')
  }

  // Patients Methods
  public getPatients(): void {
    this.patientService.getPatients().subscribe(
      (response: Patient[]) => {
        this.patients = response
        
      },(error: HttpErrorResponse) => {
        alert(error.message)
      }
    )
  }

  public onAddPatient(addForm: NgForm): void {
    document.getElementById('add-patient-form')?.click();
    this.patientService.addPatient(addForm.value).subscribe(
      (response: Patient) => {
        this.getPatients()
        addForm.reset()
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
        addForm.reset()
      }
    )
  }

  public onUpdatePatient(patient: Patient): void {
    this.patientService.updatePatient(patient).subscribe(
      (response: Patient) => {
        this.getPatients()
        
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
      }
    )
  }

  public onDeletePatient(patientId: number): void {
    this.patientService.deletePatient(patientId).subscribe(
      (response: void) => {
        this.getPatients()
        
      },
      (error: HttpErrorResponse) => {
        alert(error.message)
      }
    )
  }

  public searchPatients(key: string): void {
    const results: Patient[] = [];
    for (const patient of this.patients){
      if (patient.name.toLowerCase().indexOf(key.toLowerCase()) !== -1 
      || patient.patientNo.indexOf(key.toUpperCase()) !== -1){
        results.push(patient)
      }
    }
    this.patients = results;
    if(results.length === 0 || !key){
      this.getPatients()
    }
  }
  // Trigger modals for CRUD operations
  public onOpenModal(patient: Patient | null, mode: string): void{
    const container = document.getElementById('main-container')
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.innerText = 'Modal'
    button.setAttribute('data-toggle', 'modal')
    if (mode === 'add'){
      button.setAttribute('data-target', '#addPatientModal')
    }
    if (mode === 'edit'){
      this.editPatient = patient;
      button.setAttribute('data-target', '#editPatientModal')
    }
    if (mode === 'delete'){
      this.deletePatient = patient
      button.setAttribute('data-target', '#deletePatientModal')
    }
    if(mode ==='login'){
      button.setAttribute('data-target', '#loginModal')
    }
    if(mode ==='register'){
      button.setAttribute('data-target', '#registerModal')
    }
    container?.appendChild(button);
    button.click();
  }
}
