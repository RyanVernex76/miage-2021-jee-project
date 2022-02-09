import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InscriptionJuicerComponent } from './inscription-juicer.component';

describe('InscriptionJuicerComponent', () => {
  let component: InscriptionJuicerComponent;
  let fixture: ComponentFixture<InscriptionJuicerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InscriptionJuicerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InscriptionJuicerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
