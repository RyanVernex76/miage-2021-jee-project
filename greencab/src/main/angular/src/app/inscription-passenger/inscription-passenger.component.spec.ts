import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InscriptionPassengerComponent } from './inscription-passenger.component';

describe('InscriptionPassengerComponent', () => {
  let component: InscriptionPassengerComponent;
  let fixture: ComponentFixture<InscriptionPassengerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InscriptionPassengerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InscriptionPassengerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
