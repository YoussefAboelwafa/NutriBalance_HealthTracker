import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WaitingCoachesComponent } from './waiting-coaches.component';

describe('WaitingCoachesComponent', () => {
  let component: WaitingCoachesComponent;
  let fixture: ComponentFixture<WaitingCoachesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [WaitingCoachesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(WaitingCoachesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
