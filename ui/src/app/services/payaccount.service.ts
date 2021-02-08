import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AppAPIConfig } from '../app.config';

@Injectable({
  providedIn: 'root'
})
export class PayaccountService {

  constructor(private http: HttpClient, private router: Router) { }

  addAccount(form: FormGroup, callback: CallableFunction){

    let url = AppAPIConfig.API_END_POINT + "/account/addAccount?session="

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
      account_no: values.account,
      ifsc_code: values.ifsc,
      alt_account_no: values.altAaccount,
      alt_ifsc_code:values.altifsc,
      nominee_name:values.nominee,
      nominee_relation:values.nomineeRel,
      nominee_contact_no:values.nomineePhone,
      nominee_email:values.nomineeEmail,
      nominee_address:values.nomineeAddress
    }

    this.http.post(url, body, { responseType: 'text'} ).subscribe((response:string)=>{
      if(response){
        console.log(response)
      }
      return callback && callback();
    })

  }

  isAccountSubmitted(){

    let url = AppAPIConfig.API_END_POINT + "/account/isAdded?session="

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
        this.router.navigateByUrl("/experience")
      } 
    })

  }
}
