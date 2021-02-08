import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PayaccountService } from 'src/app/services/payaccount.service';

@Component({
  selector: 'ngr-payaccount',
  templateUrl: './payaccount.component.html',
  styleUrls: ['./payaccount.component.css']
})
export class PayaccountComponent implements OnInit {

  title = "ACCOUNT DETAILS"

  current_user: string;
  current_session: string;

  form: FormGroup;
  loading = false;
  submitted = false;

  constructor(private payaccountService: PayaccountService ,private formBuilder: FormBuilder, private router: Router) {
    payaccountService.isAccountSubmitted()
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
      account: ['', Validators.required, Validators.pattern("[0-9]")],
      ifsc: ['', Validators.required, Validators.pattern("[A-Z0-9]")],
      altAaccount: ['', Validators.required, Validators.pattern("[0-9]")],
      altifsc: ['', Validators.required, Validators.pattern("[A-Z0-9]")],
      nominee:['',Validators.required],
      nomineeRel:['',Validators.required],
      nomineePhone:['',Validators.required, Validators.pattern("[0-9]")],
      nomineeEmail:['', Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")],
      nomineeAddress:['', Validators.required]
  });
  }

  get f() { return this.form.controls; }

  onSubmit() {
    this.submitted = true;

    if (this.form.invalid) {
        return;
    }

    this.payaccountService.addAccount(this.form, ()=>{
      this.router.navigateByUrl("/experience")
    })

 
  }

}
