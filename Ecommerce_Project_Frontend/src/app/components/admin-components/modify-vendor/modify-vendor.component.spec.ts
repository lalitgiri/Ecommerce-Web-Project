import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifyVendorComponent } from './modify-vendor.component';

describe('ModifyVendorComponent', () => {
  let component: ModifyVendorComponent;
  let fixture: ComponentFixture<ModifyVendorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModifyVendorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModifyVendorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
