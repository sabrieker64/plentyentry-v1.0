import {TestBed} from '@angular/core/testing';

import {SpecialPrivilegesService} from './special-privileges.service';

describe('SpecialPrivilegesService', () => {
  let service: SpecialPrivilegesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SpecialPrivilegesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
