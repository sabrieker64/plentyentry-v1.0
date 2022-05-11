import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MaintainedEventScanComponent } from './maintained-event-scan.component';

describe('MaintainedEventScanComponent', () => {
  let component: MaintainedEventScanComponent;
  let fixture: ComponentFixture<MaintainedEventScanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MaintainedEventScanComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MaintainedEventScanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
