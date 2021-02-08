import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { OrganisationService } from 'src/app/services/organisation.service';

@Component({
  selector: 'ngr-organisation',
  templateUrl: './organisation.component.html',
  styleUrls: ['./organisation.component.css']
})
export class OrganisationComponent implements OnInit {

  title = "ORGANISATION DETAILS"

  current_user: string;
  current_session: string;

  form: FormGroup;
  loading = false;
  submitted = false;

  constructor(private organisationService: OrganisationService,private formBuilder: FormBuilder, private router: Router ) {
    organisationService.isOrg()
    organisationService.isOrgSubmitted()

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
      licence: ['', Validators.required],
      regName: ['', Validators.required],
      owner: ['', Validators.required]
  });
  }

  get f() { return this.form.controls; }

    onSubmit() {
      this.submitted = true;

      // stop here if form is invalid
      if (this.form.invalid) {
          return;
      }

      this.organisationService.addOrganisation(this.form, ()=>{
        this.router.navigateByUrl("/document")
      })

    
    }

}
