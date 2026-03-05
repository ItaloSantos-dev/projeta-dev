import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowProject } from './show-project';

describe('ShowProject', () => {
  let component: ShowProject;
  let fixture: ComponentFixture<ShowProject>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShowProject]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowProject);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
