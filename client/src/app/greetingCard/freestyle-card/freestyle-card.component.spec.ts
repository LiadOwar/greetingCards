import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FreestyleCardComponent } from './freestyle-card.component';

describe('FreestyleCardComponent', () => {
  let component: FreestyleCardComponent;
  let fixture: ComponentFixture<FreestyleCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FreestyleCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FreestyleCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
