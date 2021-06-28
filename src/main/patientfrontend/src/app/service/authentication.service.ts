import { environment } from '../../environments/environment';
import { HttpClient } from "@angular/common/http";
import { Injectable } from '@angular/core';
import { User } from '../user';
import {map} from 'rxjs/operators';

export const TOKEN = 'token'
export const AUTHENTICATED_USER = 'authenticaterUser'

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {

    public apiUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient){}

    public executeJWTAuthenticationService(user:User){

        return this.http.post<any>(`${this.apiUrl}/auth`, user)
        .pipe(map(
                data => {
                    sessionStorage.setItem(AUTHENTICATED_USER, user.username);
                    sessionStorage.setItem(TOKEN, `Bearer ${data.token}`);
                    return data
                }
            ))
    }

    public registerUser(user: User){
        return this.http.post<void>(`${this.apiUrl}/register`, user);
    }

    public getUserRole(username: string|null){
        return this.http.post<number>(`${this.apiUrl}/user/user`,
         { username: username })

    }

    public getAuthenticatedUser(): string|null {
        return sessionStorage.getItem(AUTHENTICATED_USER)
      }

    public getAuthenticatedToken(): string|null {
        if(this.getAuthenticatedUser()){
            return sessionStorage.getItem(TOKEN)
        }
        return null;
      }

    public isUserLoggedIn(): boolean {
        let user = sessionStorage.getItem(AUTHENTICATED_USER)
        return !(user === null)
      }

    public logout(): void{
        sessionStorage.removeItem(AUTHENTICATED_USER)
        sessionStorage.removeItem(TOKEN)
      }


}
