import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AppAPIConfig } from '../app.config';

@Injectable({
  providedIn: 'root'
})
export class OrganisationService {

  constructor(private http: HttpClient, private router: Router) { }

  addOrganisation(form: FormGroup, callback: CallableFunction){

    let url = AppAPIConfig.API_END_POINT + "/organisation/addOrg?session="

    // Check Current Session
    let strg = sessionStorage.getItem("hiregapp")
    if(strg != null){
      let obj = JSON.parse(strg)
      url += obj.sessionid
    } else {
      this.router.navigateByUrl("/login")
    }

    let values = form.value

    let body = {
      license_number: values.licence,
      registered_name: values.regName,
      ownership_details: values.owner,
    }

    this.http.post(url, body, { responseType: 'text'} ).subscribe((response:string)=>{
      if(response){
        console.log(response)
      }
      return callback && callback();
    })
  }


  isOrg(){

    let url = AppAPIConfig.API_END_POINT + "/organisation/isOrg?session="

    // Check Current Session
    let strg = sessionStorage.getItem("hiregapp")
    if(strg != null){
      let obj = JSON.parse(strg)
      url += obj.sessionid
    } else {
      this.router.navigateByUrl("/login")
    }

    this.http.get(url, {responseType:'text'}).subscribe((response)=>{
      if(response == 'false'){
        this.router.navigateByUrl("/document")
      }
    })
  }

  isOrgSubmitted(){
    let url = AppAPIConfig.API_END_POINT + "/organisation/isAdded?session="

    // Check Current Session
    let strg = sessionStorage.getItem("hiregapp")
    if(strg != null){
      let obj = JSON.parse(strg)
      url += obj.sessionid
    } else {
      this.router.navigateByUrl("/login")
    }

    this.http.get(url, {responseType: "text"}).subscribe((res)=>{ 
      if(res === 'true'){
        this.router.navigateByUrl("/document")
      }
    })

  }

}
