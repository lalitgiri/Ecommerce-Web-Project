import { Component } from '@angular/core';
import { DataSharingService } from './service/data-sharing.service';
import { AddHeaderInHttpService } from './service/add-header-in-http.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers:[DataSharingService]
  
})
export class AppComponent  {

  
}
