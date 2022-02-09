import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FareTableComponent } from './fare-table.component';

describe('FareTableComponent', () => {
  let component: FareTableComponent;
  let fixture: ComponentFixture<FareTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FareTableComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FareTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
