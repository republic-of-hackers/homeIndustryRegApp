import { TestBed } from '@angular/core/testing';

import { PayaccountService } from './payaccount.service';

describe('PayaccountService', () => {
  let service: PayaccountService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PayaccountService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
