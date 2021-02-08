import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AppAPIConfig } from '../app.config';

@Injectable({
  providedIn: 'root'
})
export class InspectionService {

  constructor(private http: HttpClient ,private router: Router) { }

  // getCityOfCurrentUser(){

  //   let city_of_current_user = ''

  //   let url = AppAPIConfig.API_END_POINT + "/personal/getCity?session="
  //    // Check Current Session
  //    let strg = sessionStorage.getItem("hiregapp")
  //    if(strg != null){
  //      let obj = JSON.parse(strg)
  //      url += obj.sessionid
  //    } else {
  //      this.router.navigateByUrl("/login")
  //    }

  //    this.http.get(url, { responseType: 'text'}).subscribe(res=>{
  //      console.log(res)
  //      return res
  //    })
  // }

  getAvailableDates(callback:CallableFunction){
    let url1 = AppAPIConfig.API_END_POINT + "/insp/getAvailableDates?session="
    let url2 = AppAPIConfig.API_END_POINT + "/personal/getCity?session="

     // Check Current Session
     let strg = sessionStorage.getItem("hiregapp")
     if(strg != null){
       let obj = JSON.parse(strg)
       url1 += obj.sessionid +"&city="
        url2 += obj.sessionid
     } else {
       this.router.navigateByUrl("/login")
     }

    //  url1 += city

    //  this.http.get(url1, { responseType: 'text'}).subscribe((res)=>{
    //   return callback && callback(res)          
    //  })

     this.http.get(url2, { responseType: 'text'}).subscribe(city=>{
        url1 += city
        this.http.get(url1, { responseType: 'text'}).subscribe((res)=>{
          return callback && callback(res, city)          
        })
      })
  }

  getAvailableTimes(callback:CallableFunction, date:string, city:string){

    let url1 = AppAPIConfig.API_END_POINT + "/insp/getTimeSlots?session="

    // Check Current Session
    let strg = sessionStorage.getItem("hiregapp")
    if(strg != null){
      let obj = JSON.parse(strg)
      url1 += obj.sessionid
      url1 += "&date=" + date 
      url1 +="&city=" + city
    } else {
      this.router.navigateByUrl("/login")
    }

    this.http.get(url1, { responseType: 'text'} ).subscribe((res)=>{
      return callback && callback(res)
    })

    // this.http.get(url2, { responseType: 'text'}).subscribe(city=>{
    //   url1 += city
    //   this.http.get(url1, { responseType: 'text'}).subscribe((res)=>{
    //     return callback && callback(res)          
    //   })
    // })

  }

  bookSlot(city:string, date:string, time:string, callback:CallableFunction){

    let url = AppAPIConfig.API_END_POINT + "/insp/bookSlot?session="

     // Check Current Session
     let strg = sessionStorage.getItem("hiregapp")
     if(strg != null){
       let obj = JSON.parse(strg)
       url += obj.sessionid
       url += "&city=" + city + "&date=" + date + "&time=" + time
     } else {
       this.router.navigateByUrl("/login")
     }

     this.http.post(url, {responseType: "text"}).subscribe((res)=>{
      if(res === 'true'){
        this.router.navigateByUrl("/thanks")
        return callback && callback(true)
      } else {
        return callback && callback(false)
      }
    })
  }

  isInspectionSubmitted(callback:CallableFunction){
    let url = AppAPIConfig.API_END_POINT + "/insp/isAdded?session="

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
        return callback && callback(true)
      } else {
        return callback && callback(false)
      }
    })

  }
}
