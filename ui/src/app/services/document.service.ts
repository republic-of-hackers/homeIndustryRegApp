import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AppAPIConfig } from '../app.config';

@Injectable({
  providedIn: 'root'
})
export class DocumentService {

  constructor(private http: HttpClient, private router: Router) {}

  addDocument(form: FormGroup, callback: CallableFunction){

    let url = AppAPIConfig.API_END_POINT + "/identity/addIdDoc?session="

    // Check Current Session
    let strg = sessionStorage.getItem("hiregapp")
    if(strg != null){
      let obj = JSON.parse(strg)
      url += obj.sessionid
    } else {
      this.router.navigateByUrl("/login")
    }

    let values = form.value

    let formData = new FormData()
    formData.append('aadhar', values.aadhaar, values.aadhaar.name)
    formData.append("taxid", values.taxCopy, values.taxCopy.name) 
    this.http.post(url, formData, { responseType: 'text'}).subscribe((response: string)=>{
      if(response){
        console.log(response)
      }

      return callback && callback();

    })

  }

  isDocumentSubmitted(){
    let url = AppAPIConfig.API_END_POINT + "/identity/isAdded?session="

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
        this.router.navigateByUrl("/account")
      } 
    })
  }
}
