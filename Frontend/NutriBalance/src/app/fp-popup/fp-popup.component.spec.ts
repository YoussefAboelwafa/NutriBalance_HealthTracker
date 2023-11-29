import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FpPopupComponent } from './fp-popup.component';

describe('FpPopupComponent', () => {
  let component: FpPopupComponent;
  let fixture: ComponentFixture<FpPopupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FpPopupComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FpPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
