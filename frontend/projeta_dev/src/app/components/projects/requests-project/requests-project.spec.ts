import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestsProject } from './requests-project';

describe('RequestsProject', () => {
  let component: RequestsProject;
  let fixture: ComponentFixture<RequestsProject>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RequestsProject]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RequestsProject);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
