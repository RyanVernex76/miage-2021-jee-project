import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarRechargeComponent } from './car-recharge.component';

describe('CarRechargeComponent', () => {
  let component: CarRechargeComponent;
  let fixture: ComponentFixture<CarRechargeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CarRechargeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CarRechargeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
