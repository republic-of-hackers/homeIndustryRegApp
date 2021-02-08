import { HttpClient } from '@angular/common/http';
import { Component, ErrorHandler, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppService } from 'src/app/app.service';

@Component({
  selector: 'ngr-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent{

  title = "LOGIN"

  credentials = { username: '', password: '' }
  error:string;

  constructor(private app: AppService, private http: HttpClient, private router: Router) {
    let strg = sessionStorage.getItem("hiregapp")
    if(strg != null) this.router.navigateByUrl("/personal")
    this.error = ''
  }

  ngOnInit(): void { }
  
  login() {
    this.app.authenticate(this.credentials, (check:boolean, error:any) => {
      // Authenticated
      if(error == null){
        this.router.navigateByUrl('/personal');
        window.location.reload();
      } else {
        this.error = "Invalid Credential"
      }
    });
    return false;
  }
  
}
