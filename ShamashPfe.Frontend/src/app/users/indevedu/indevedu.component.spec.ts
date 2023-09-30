import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IndeveduComponent } from './indevedu.component';

describe('IndeveduComponent', () => {
  let component: IndeveduComponent;
  let fixture: ComponentFixture<IndeveduComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IndeveduComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IndeveduComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
