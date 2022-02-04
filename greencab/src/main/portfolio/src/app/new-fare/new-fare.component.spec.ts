import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewFareComponent } from './new-fare.component';

describe('NewFareComponent', () => {
  let component: NewFareComponent;
  let fixture: ComponentFixture<NewFareComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewFareComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewFareComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
