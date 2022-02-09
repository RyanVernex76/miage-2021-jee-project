import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JuicerComponent } from './juicer.component';

describe('JuicerComponent', () => {
  let component: JuicerComponent;
  let fixture: ComponentFixture<JuicerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JuicerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(JuicerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
