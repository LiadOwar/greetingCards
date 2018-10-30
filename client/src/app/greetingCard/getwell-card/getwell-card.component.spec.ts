import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetwellCardComponent } from './getwell-card.component';

describe('GetwellCardComponent', () => {
  let component: GetwellCardComponent;
  let fixture: ComponentFixture<GetwellCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetwellCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetwellCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
