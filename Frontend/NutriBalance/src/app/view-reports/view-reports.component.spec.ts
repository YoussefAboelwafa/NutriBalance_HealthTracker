import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewReportsComponent } from './view-reports.component';

describe('ViewReportsComponent', () => {
  let component: ViewReportsComponent;
  let fixture: ComponentFixture<ViewReportsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewReportsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewReportsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
