import { HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HttpIntercepterAuthService implements HttpInterceptor {

  constructor() { }

  public authToken: any = sessionStorage.getItem('token');

  intercept(request: HttpRequest<any>, next: HttpHandler){

    if(this.authToken){
      request = request.clone({
        setHeaders: { Authorization: this.authToken }
      })

    }
    return next.handle(request)
  }
}
