import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DocumentService } from 'src/app/services/document.service';

@Component({
  selector: 'ngr-document',
  templateUrl: './document.component.html',
  styleUrls: ['./document.component.css']
})
export class DocumentComponent implements OnInit {

  title = "IDENTITY DOCUMENT"

  current_user: string;
  current_session: string;

  form: FormGroup;
  loading = false;
  submitted = false;

  constructor(private documentService: DocumentService, 
              private formBuilder: FormBuilder, 
              private router: Router,
              private cd: ChangeDetectorRef) {

    documentService.isDocumentSubmitted()
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
      aadhaar: [File, Validators.required],
      taxCopy: [File, Validators.required],
  });
  }

  get f() { return this.form.controls; }

  onAadharFileChange(event: any){
    let files:FileList = event.target.files
    this.form.patchValue({
      aadhaar: files.item(0)
    })
    
  }

  onTaxFileChange(event: any){

    let files:FileList = event.target.files
    this.form.patchValue({
      taxCopy:files.item(0)
    })
   
  }


  onSubmit() {
    this.submitted = true;

    if(this.form.invalid){
      return
    }

    this.documentService.addDocument(this.form, ()=>{
      this.router.navigateByUrl("/account")
    })

    
  }

}
