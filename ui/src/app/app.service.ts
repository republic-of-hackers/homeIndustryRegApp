import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppAPIConfig } from './app.config';
import { Router } from '@angular/router';

@Injectable()
export class AppService {
  constructor(private http: HttpClient, private router: Router) {}

  authenticate(credentials: any, callback: any) {

        let url = AppAPIConfig.API_END_POINT + "/users/login"

        this.http.post(url, { 
            "email": btoa(credentials.username),
            "password": btoa(credentials.password)    
        }, {responseType: 'text'} ).subscribe((response: string) => {
            console.log("Login: " + response)
            if (response) {
                let strg = {
                    "username": credentials.username,
                    "sessionid": response
                }
                sessionStorage.setItem("hiregapp", JSON.stringify(strg))
            } 
            return callback && callback(true, null);
        },
        (error) => {
            return callback && callback(false, error);
        }
        );
    }

    register(credentials: any, callback: any){

        let url = AppAPIConfig.API_END_POINT + "/users/addUser"

        this.http.post(url, { 
            "userEmail": credentials.username,
            "userPass": credentials.password
        }, {responseType: 'text'}).subscribe( (response) => {
            return callback && callback();
        },
        (error)=>{
            console.log(error)
        })

    }

    logout() {

    }

}