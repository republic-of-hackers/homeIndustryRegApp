import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { InspectionService } from 'src/app/services/inspection.service';

@Component({
  selector: 'ngr-inspection',
  templateUrl: './inspection.component.html',
  styleUrls: ['./inspection.component.css']
})
export class InspectionComponent implements OnInit {

  title = "INSPECTION"

  current_user: string;
  current_session: string;

  form: FormGroup;
  loading = false;
  submitted = false;

  available_dates: string[] = []
  available_times: string[] = []

  selected_date: string = ''
  selected_time:string = ''

  city:string = 'mumbai';

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private inspectionService: InspectionService) 
  {
    inspectionService.isInspectionSubmitted((completed: boolean)=>{
      if(completed){
        this.router.navigateByUrl("/thanks")
      }
    })

    let strg = sessionStorage.getItem('hiregapp')
    if(strg != null){
      let obj = JSON.parse(strg)
      this.current_user = obj.username
      this.current_session = obj.sessionid
    } else {
      this.router.navigateByUrl("/login")
      this.current_user = '';
      this.current_session = '';
    }
    this.form = this.formBuilder.group({})

  }

  ngOnInit(): void {

    // Getting Available Dates
    this.inspectionService.getAvailableDates((dates:string, city:string)=>{
      this.available_dates = JSON.parse(dates)
      this.city = city
    })

  }

  get f() { return this.form.controls; }

  onDateSelect(event:any){
    if(event.target.value){
      let value = event.target.value
      if(value != '--Select--'){
        this.selected_date = value
        this.inspectionService.getAvailableTimes((times:string)=>{
          this.available_times = JSON.parse(times)
        }, value, this.city)

      }
    }
  }

  onTimeSelect(event:any){
    if(event.target.value){
      let value = event.target.value
      if(value != '--Select--'){
        this.selected_time = value
      }
    }

  }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.form.invalid) {
        return;
    }

    this.inspectionService.bookSlot(this.city, this.selected_date, this.selected_time, (completed:boolean)=>{
      if(completed){
        this.router.navigateByUrl("/thanks")
      } else {
        alert("something went wrong")
      }
      
    })




    
  }

}
