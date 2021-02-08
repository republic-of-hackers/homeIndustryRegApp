import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ExperienceService } from 'src/app/services/experience.service';

@Component({
  selector: 'ngr-experience',
  templateUrl: './experience.component.html',
  styleUrls: ['./experience.component.css']
})
export class ExperienceComponent implements OnInit {

  
  @ViewChild('fileInput') fileInputVar!: ElementRef ;

  title = "EXPERIENCE DETAILS"

  current_user: string;
  current_session: string;

  form: FormGroup;
  loading = false;
  submitted = false;
  myFiles:File [] = [];
  counter:number;

  warn:string;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private experienceService: ExperienceService,
              ) 
  {
    experienceService.isExpSubmitted()
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
    this.counter = 0
    this.warn = ''
    
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      experience: ['', Validators.required],
      work: [FileList],
    });
  }

  get f() { return this.form.controls; }

  onSelectFile(event:any){
    if(event.target.files && this.counter < 10){
      let files:FileList = event.target.files
      let file = files.item(0)
      if(file != null){
        this.myFiles.push(file)
        this.counter += 1
        console.log(file.name + "is added")
        this.fileInputVar.nativeElement.value = ''
      }
    }

    if(this.counter >= 10){
      this.warn = 'Max Limit is 10.'
      console.log("reach to max limit.")
    }
  }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.form.invalid) {
        return;
    }

    this.form.value.work = this.myFiles

    this.experienceService.addExperience(this.form, ()=>{
      this.router.navigateByUrl("/inspection")
    })

    
  }

}
