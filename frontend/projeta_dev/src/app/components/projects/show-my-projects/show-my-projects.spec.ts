import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowMyProjects } from './show-my-projects';

describe('ShowMyProjects', () => {
  let component: ShowMyProjects;
  let fixture: ComponentFixture<ShowMyProjects>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShowMyProjects]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowMyProjects);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
