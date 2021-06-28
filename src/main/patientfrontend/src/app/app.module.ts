import { PatientService } from './service/patient.service';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { HttpIntercepterAuthService } from './service/http-intercepter-auth.service';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    PatientService,
  {provide: HTTP_INTERCEPTORS, useClass: HttpIntercepterAuthService, multi: true}
],
  bootstrap: [AppComponent]
})
export class AppModule { }
