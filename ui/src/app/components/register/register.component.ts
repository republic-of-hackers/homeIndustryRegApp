import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppService } from 'src/app/app.service';

@Component({
  selector: 'ngr-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  title = "CREATE ACCOUNT"

  credentials = {username: '', password: ''}

  message = ''

  constructor(private app: AppService, private http: HttpClient, private router: Router) {
    let strg = sessionStorage.getItem("hiregapp")
    if(strg != null){
      this.router.navigateByUrl("/personal")
    }
  }

  ngOnInit(): void {
  }

  register() {
    this.app.register(this.credentials, () => {
      this.router.navigateByUrl('/login')
    });
    return false
  }
}
