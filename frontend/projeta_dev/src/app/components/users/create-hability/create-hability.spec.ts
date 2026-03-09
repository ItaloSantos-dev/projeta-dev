import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateHability } from './create-hability';

describe('CreateHability', () => {
  let component: CreateHability;
  let fixture: ComponentFixture<CreateHability>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateHability]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateHability);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
