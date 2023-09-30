import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MesFactureComponent } from './mes-facture.component';

describe('MesFactureComponent', () => {
  let component: MesFactureComponent;
  let fixture: ComponentFixture<MesFactureComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MesFactureComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MesFactureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
