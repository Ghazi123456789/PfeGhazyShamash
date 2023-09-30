import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuccFactureComponent } from './succ-facture.component';

describe('SuccFactureComponent', () => {
  let component: SuccFactureComponent;
  let fixture: ComponentFixture<SuccFactureComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SuccFactureComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SuccFactureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
