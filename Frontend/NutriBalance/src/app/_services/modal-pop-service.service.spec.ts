import { TestBed } from '@angular/core/testing';

import { ModalPopServiceService } from './modal-pop-service.service';

describe('ModalPopServiceService', () => {
  let service: ModalPopServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ModalPopServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
