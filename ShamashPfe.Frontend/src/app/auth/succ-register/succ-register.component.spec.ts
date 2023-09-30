import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuccRegisterComponent } from './succ-register.component';

describe('SuccRegisterComponent', () => {
  let component: SuccRegisterComponent;
  let fixture: ComponentFixture<SuccRegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SuccRegisterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SuccRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
