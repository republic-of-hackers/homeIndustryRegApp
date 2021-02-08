import { BrowserModule } from '@angular/platform-browser';
import { Injectable, NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from '@angular/common/http';

// Constants
import { AppAPIConfig } from './app.config';

// Auth Service
import { AppService } from './app.service';

// Main Component
import { AppComponent } from './app.component';

// Custom Components
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { PersonalComponent } from './components/personal/personal.component';
import { OrganisationComponent } from './components/organisation/organisation.component';
import { DocumentComponent } from './components/document/document.component';
import { PayaccountComponent } from './components/payaccount/payaccount.component';
import { ExperienceComponent } from './components/experience/experience.component';
import { InspectionComponent } from './components/inspection/inspection.component';
import { NavbarComponent } from './components/ui/navbar/navbar.component';
import { FinishComponent } from './components/ui/finish/finish.component';

@Injectable()
export class XhrInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const xhr = req.clone({
      headers: req.headers.set('X-Requested-With', 'XMLHttpRequest')
    });
    return next.handle(xhr);
  }
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    PersonalComponent,
    OrganisationComponent,
    DocumentComponent,
    PayaccountComponent,
    ExperienceComponent,
    InspectionComponent,
    NavbarComponent,
    FinishComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [AppAPIConfig, 
              AppService, 
              {   provide: HTTP_INTERCEPTORS, 
                  useClass: XhrInterceptor, 
                  multi: true 
              },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }


