import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BillsmanagementComponent } from './billsmanagement.component';

describe('BillsmanagementComponent', () => {
  let component: BillsmanagementComponent;
  let fixture: ComponentFixture<BillsmanagementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BillsmanagementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BillsmanagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
