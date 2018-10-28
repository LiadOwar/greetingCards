import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BirthdayTemplateComponent } from './birthday-template.component';

describe('BirthdayTemplateComponent', () => {
  let component: BirthdayTemplateComponent;
  let fixture: ComponentFixture<BirthdayTemplateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BirthdayTemplateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BirthdayTemplateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
