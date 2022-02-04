import { TestBed } from '@angular/core/testing';

import { GreenCabService } from './green-cab.service';

describe('GreenCabService', () => {
  let service: GreenCabService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GreenCabService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
