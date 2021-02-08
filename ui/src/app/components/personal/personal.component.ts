import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PersonalService } from 'src/app/services/personal.service';

@Component({
  selector: 'ngr-personal',
  templateUrl: './personal.component.html',
  styleUrls: ['./personal.component.css']
})
export class PersonalComponent implements OnInit {

  title = "PERSONAL DETAILS"

  current_user: string;
  current_session: string;
  form: FormGroup;
  loading = false;
  submitted = false;

  constructor(private personalService: PersonalService,private formBuilder: FormBuilder, private router: Router) {
    personalService.isPersonalSubmitted()
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
    this.form = this.formBuilder.group({
      firstName: ['', Validators.required],
      middleName: ['', Validators.minLength(0)],
      lastName: ['', Validators.required],

      homeNo: ['', Validators.required],
      street: [''],
      area:['', Validators.required, Validators.minLength(3)],
      city: ['',Validators.required],
      state: ['',Validators.required],
      country: ['', Validators.required],
      landmark: ['',Validators.required],

      phone:['',Validators.required,Validators.pattern("[0-9]{10}")],
      altPhone:['', Validators.pattern("[0-9]{10}")],

      email:['', Validators.required,Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")],
      altEmail:['',Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")],

      userType: ['', Validators.required],

      tax:['', Validators.required],

      fax:['']
  });
  
  }

  get f() { return this.form.controls; }

  onSubmit() {
    this.submitted = true;

    if (this.form.invalid) {
      return;
    }

    this.personalService.addPersonal(this.form, ()=>{
      this.router.navigateByUrl("/organisation")
    })
    
  }
}
