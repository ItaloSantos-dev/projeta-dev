import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowProjectsOfUser } from './show-projects-of-user';

describe('ShowProjectsOfUser', () => {
  let component: ShowProjectsOfUser;
  let fixture: ComponentFixture<ShowProjectsOfUser>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShowProjectsOfUser]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowProjectsOfUser);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
