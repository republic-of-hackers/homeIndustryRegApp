import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PayaccountComponent } from './payaccount.component';

describe('PayaccountComponent', () => {
  let component: PayaccountComponent;
  let fixture: ComponentFixture<PayaccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PayaccountComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PayaccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
