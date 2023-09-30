import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContratFichierComponent } from './contrat-fichier.component';

describe('ContratFichierComponent', () => {
  let component: ContratFichierComponent;
  let fixture: ComponentFixture<ContratFichierComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContratFichierComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ContratFichierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
