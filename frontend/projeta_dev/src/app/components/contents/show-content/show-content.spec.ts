import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowContent } from './show-content';

describe('ShowContent', () => {
  let component: ShowContent;
  let fixture: ComponentFixture<ShowContent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShowContent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowContent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
