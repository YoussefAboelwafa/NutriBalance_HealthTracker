import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoachPageComponent } from './coach-page.component';

describe('CoachPageComponent', () => {
  let component: CoachPageComponent;
  let fixture: ComponentFixture<CoachPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CoachPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CoachPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
