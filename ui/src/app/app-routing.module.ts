import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DocumentComponent } from './components/document/document.component';
import { ExperienceComponent } from './components/experience/experience.component';
import { InspectionComponent } from './components/inspection/inspection.component';
import { LoginComponent } from './components/login/login.component';
import { OrganisationComponent } from './components/organisation/organisation.component';
import { PayaccountComponent } from './components/payaccount/payaccount.component';
import { PersonalComponent } from './components/personal/personal.component';
import { RegisterComponent } from './components/register/register.component';
import { FinishComponent } from './components/ui/finish/finish.component';

const routes: Routes = [
  // { path: '', pathMatch: 'full', redirectTo:"" },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'personal', component: PersonalComponent},
  { path: 'organisation', component: OrganisationComponent},
  { path: 'document', component: DocumentComponent},
  { path: 'account', component: PayaccountComponent},
  { path: 'experience', component: ExperienceComponent},
  { path: 'inspection', component: InspectionComponent},
  { path: 'thanks', component: FinishComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
