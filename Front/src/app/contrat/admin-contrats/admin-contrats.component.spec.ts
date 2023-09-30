import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminContratsComponent } from './admin-contrats.component';

describe('AdminContratsComponent', () => {
  let component: AdminContratsComponent;
  let fixture: ComponentFixture<AdminContratsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminContratsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminContratsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
