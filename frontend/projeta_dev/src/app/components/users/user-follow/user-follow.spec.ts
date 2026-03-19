import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserFollow } from './user-follow';

describe('UserFollow', () => {
  let component: UserFollow;
  let fixture: ComponentFixture<UserFollow>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserFollow]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserFollow);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
