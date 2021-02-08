import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AppAPIConfig } from '../app.config';

@Injectable({
  providedIn: 'root'
})
export class ExperienceService {

  constructor(private http: HttpClient, private router: Router) { }

  addExperience(form: FormGroup, callback: CallableFunction){

    let url = AppAPIConfig.API_END_POINT + "/exp/addExp?session="

    // Check Current Session
    let strg = sessionStorage.getItem("hiregapp")
    if(strg != null){
      let obj = JSON.parse(strg)
      url += obj.sessionid
    } else {
      this.router.navigateByUrl("/login")
    }

    let content = form.value.experience
    let files:File[] = form.value.work

    let formData = new FormData()
    
    
    files.forEach(file=>{
      console.log(file)
      formData.append("files", file, file.name)
    })
    formData.append("content", content)

    console.log(formData)

    this.http.post(url, formData, { responseType: 'text'}).subscribe((response: string)=>{
      if(response){
        console.log(response)
      }
      return callback && callback();
    })

  }

  isExpSubmitted(){

    let url = AppAPIConfig.API_END_POINT + "/exp/isAdded?session="

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
        this.router.navigateByUrl("/inspection")
      } 
    })
  }


}
