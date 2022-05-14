import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MaintainedEventsListComponent } from './maintained-events-list.component';

describe('MaintainedEventsListComponent', () => {
  let component: MaintainedEventsListComponent;
  let fixture: ComponentFixture<MaintainedEventsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MaintainedEventsListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MaintainedEventsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
