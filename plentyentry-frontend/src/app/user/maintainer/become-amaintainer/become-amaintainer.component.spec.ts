import {ComponentFixture, TestBed} from '@angular/core/testing';

import {BecomeAMaintainerComponent} from './become-amaintainer.component';

describe('BecomeAMaintainerComponent', () => {
  let component: BecomeAMaintainerComponent;
  let fixture: ComponentFixture<BecomeAMaintainerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BecomeAMaintainerComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BecomeAMaintainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
