import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetwellTemplateComponent } from './getwell-template.component';

describe('GetwellTemplateComponent', () => {
  let component: GetwellTemplateComponent;
  let fixture: ComponentFixture<GetwellTemplateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetwellTemplateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetwellTemplateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
