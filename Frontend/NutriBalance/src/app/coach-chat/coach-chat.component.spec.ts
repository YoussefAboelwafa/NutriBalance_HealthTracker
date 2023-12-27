import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoachChatComponent } from './coach-chat.component';

describe('CoachChatComponent', () => {
  let component: CoachChatComponent;
  let fixture: ComponentFixture<CoachChatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CoachChatComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CoachChatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
