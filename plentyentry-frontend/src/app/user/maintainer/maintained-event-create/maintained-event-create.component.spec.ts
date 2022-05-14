import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MaintainedEventCreateComponent } from './maintained-event-create.component';

describe('MaintainedEventCreateComponent', () => {
  let component: MaintainedEventCreateComponent;
  let fixture: ComponentFixture<MaintainedEventCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MaintainedEventCreateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MaintainedEventCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
