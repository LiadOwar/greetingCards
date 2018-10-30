import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BirthdayCardComponent } from './birthday-card.component';

describe('BirthdayCardComponent', () => {
  let component: BirthdayCardComponent;
  let fixture: ComponentFixture<BirthdayCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BirthdayCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BirthdayCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
