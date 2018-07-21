import { Component, OnInit, Input, ChangeDetectorRef } from '@angular/core';
import { HttpModule, Http } from '@angular/http';
import 'rxjs/add/operator/map';
import { environment } from '../../../environments/environment';
import { AddHeaderInHttpService } from '../../service/add-header-in-http.service';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {

  constructor(private http: AddHeaderInHttpService, private cdRef: ChangeDetectorRef) { }
  httpData = [];
  itemData;
  url = environment.serverUrl;
  dateNow: Date;
  ngOnInit() {
    this.http.get(environment.serverUrl + "getallproductCategory").
      map(response => response.json()).
      subscribe(data => {
        this.httpData = data
      },
      (error:Error)=>{ alert(error.message)});
  }
  ngAfterViewChecked() {
    this.cdRef.detectChanges();
  }
}
