import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateplanComponent } from './createplan.component';

describe('CreateplanComponent', () => {
  let component: CreateplanComponent;
  let fixture: ComponentFixture<CreateplanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateplanComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateplanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
