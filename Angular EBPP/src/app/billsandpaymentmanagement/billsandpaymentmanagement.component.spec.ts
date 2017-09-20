import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BillsandpaymentmanagementComponent } from './billsandpaymentmanagement.component';

describe('BillsandpaymentmanagementComponent', () => {
  let component: BillsandpaymentmanagementComponent;
  let fixture: ComponentFixture<BillsandpaymentmanagementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BillsandpaymentmanagementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BillsandpaymentmanagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });


  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
