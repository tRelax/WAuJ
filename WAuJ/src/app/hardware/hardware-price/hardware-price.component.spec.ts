import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HardwarePriceComponent } from './hardware-price.component';

describe('HardwarePriceComponent', () => {
  let component: HardwarePriceComponent;
  let fixture: ComponentFixture<HardwarePriceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HardwarePriceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HardwarePriceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
