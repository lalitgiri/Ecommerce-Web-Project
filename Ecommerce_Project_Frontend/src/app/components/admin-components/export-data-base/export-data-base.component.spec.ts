import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExportDataBaseComponent } from './export-data-base.component';

describe('ExportDataBaseComponent', () => {
  let component: ExportDataBaseComponent;
  let fixture: ComponentFixture<ExportDataBaseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExportDataBaseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExportDataBaseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
