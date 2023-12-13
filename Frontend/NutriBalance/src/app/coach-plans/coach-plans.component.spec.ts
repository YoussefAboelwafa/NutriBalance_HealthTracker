import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoachPlansComponent } from './coach-plans.component';

describe('CoachPlansComponent', () => {
  let component: CoachPlansComponent;
  let fixture: ComponentFixture<CoachPlansComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CoachPlansComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CoachPlansComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
