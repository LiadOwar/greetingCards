import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FreestyleTemplateComponent } from './freestyle-template.component';

describe('FreestyleTemplateComponent', () => {
  let component: FreestyleTemplateComponent;
  let fixture: ComponentFixture<FreestyleTemplateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FreestyleTemplateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FreestyleTemplateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
