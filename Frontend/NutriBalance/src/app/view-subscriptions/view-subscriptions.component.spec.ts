import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewSubscriptionsComponent } from './view-subscriptions.component';

describe('ViewSubscriptionsComponent', () => {
  let component: ViewSubscriptionsComponent;
  let fixture: ComponentFixture<ViewSubscriptionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewSubscriptionsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewSubscriptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
