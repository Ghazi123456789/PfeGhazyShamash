import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DemandeFactureComponent } from './demande-facture.component';

describe('DemandeFactureComponent', () => {
  let component: DemandeFactureComponent;
  let fixture: ComponentFixture<DemandeFactureComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DemandeFactureComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DemandeFactureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
