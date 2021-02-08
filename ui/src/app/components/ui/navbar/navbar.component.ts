import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { finalize } from 'rxjs/operators';
import { AppAPIConfig } from 'src/app/app.config';
import { AppService } from 'src/app/app.service';

@Component({
  selector: 'ngr-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  
  current_user: string;
  current_session:string;

  auth: boolean;

  constructor(private app: AppService, private http: HttpClient,private router: Router) {
    let strg = sessionStorage.getItem("hiregapp")
    if(strg != null){
      let obj = JSON.parse(strg)
      let user_email = obj.username
      this.current_user =  user_email.split("@")[0]

      this.current_session = obj.sessionid
      this.auth = true
      this.router.navigateByUrl("/personal")
    } else {
      this.current_session = ''
      this.current_user = ''
      this.auth = false
      this.router.navigateByUrl("/login")
    }
   }

  ngOnInit(): void {
  }

  isauth(){
    return this.auth
  }

  logout() {
    let strg = sessionStorage.getItem("hiregapp")
    if(strg != null){
      let obj = JSON.parse(strg)
    let url = AppAPIConfig.API_END_POINT + "/users/logout?useremail=" + obj.username
      this.http.post(url, {}).pipe(finalize(() => {
          sessionStorage.clear()
          this.router.navigateByUrl('/login');
          window.location.reload();
      })).subscribe();
    }
    this.router.navigateByUrl("/login")
  }

}
