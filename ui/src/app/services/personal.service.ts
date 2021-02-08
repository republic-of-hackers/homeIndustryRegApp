import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Capability } from 'protractor';
import { AppAPIConfig } from '../app.config';

@Injectable({
  providedIn: 'root'
})
export class PersonalService {

  constructor(private http: HttpClient, private router: Router) { }

  addPersonal(form: FormGroup, callback: CallableFunction){
    
    let url = AppAPIConfig.API_END_POINT + "/personal/addPersonal?session="

    // Check Current Session
    let strg = sessionStorage.getItem("hiregapp")
    if(strg != null){
      let obj = JSON.parse(strg)
      url += obj.sessionid
    } else {
      this.router.navigateByUrl("/login")
    }

    let values = form.value

    let headers = new HttpHeaders()
    headers.append("Access-Control-Allow-Origin", "http://localhost:8080")

    let body = {
      first_name: values.firstName,
      middle_name: values.middleName,
      last_name: values.lastName,
      house_no: values.homeNo,
      street_name: values.street,
      area: values.area,
      city: values.city,
      state: values.state,
      country: values.country,
      landmark: values.landmark,
      phone_no: values.phoneNo,
      alt_phone_no: values.altPhone, 
      email: values.email,
      alt_email: values.altEmail,
      tax_id: values.tax,
      fax: values.fax,
      user_type: values.userType
    }

    this.http.post(url, body, {headers: headers, responseType: 'text'} ).subscribe((response:string)=>{
      if(response){
        console.log(response)
      }
      return callback && callback();
    })
  }

  isPersonalSubmitted(){

    let url = AppAPIConfig.API_END_POINT + "/personal/isAdded?session="

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
        this.router.navigateByUrl("/organisation")
      } 
    })
  }
}
