import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarInChargeComponent } from './car-in-charge.component';

describe('CarInChargeComponent', () => {
  let component: CarInChargeComponent;
  let fixture: ComponentFixture<CarInChargeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CarInChargeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CarInChargeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
