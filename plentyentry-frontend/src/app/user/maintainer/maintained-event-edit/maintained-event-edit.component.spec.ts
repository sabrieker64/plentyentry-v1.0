import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MaintainedEventEditComponent } from './maintained-event-edit.component';

describe('MaintainedEventEditComponent', () => {
  let component: MaintainedEventEditComponent;
  let fixture: ComponentFixture<MaintainedEventEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MaintainedEventEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MaintainedEventEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
