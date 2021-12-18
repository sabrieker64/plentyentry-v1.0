import {ComponentFixture, TestBed} from '@angular/core/testing';

import {EventTileOverviewComponent} from './event-tile-overview.component';

describe('EventTileOverviewComponent', () => {
  let component: EventTileOverviewComponent;
  let fixture: ComponentFixture<EventTileOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EventTileOverviewComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EventTileOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
