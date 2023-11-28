import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoachSignupComponent } from './coach-signup.component';

describe('CoachSignupComponent', () => {
  let component: CoachSignupComponent;
  let fixture: ComponentFixture<CoachSignupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CoachSignupComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CoachSignupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
