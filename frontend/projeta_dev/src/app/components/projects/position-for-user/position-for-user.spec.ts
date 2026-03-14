import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PositionForUser } from './position-for-user';

describe('PositionForUser', () => {
  let component: PositionForUser;
  let fixture: ComponentFixture<PositionForUser>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PositionForUser]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PositionForUser);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
