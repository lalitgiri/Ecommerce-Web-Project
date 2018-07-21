import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { ActivatedRoute } from '@angular/router';
import { environment } from '../../../environments/environment.prod';
import { AddHeaderInHttpService } from '../../service/add-header-in-http.service';

@Component({
  selector: 'app-search-result-component',
  templateUrl: './search-result-component.component.html',
  styleUrls: ['./search-result-component.component.css']
})
export class SearchResultComponentComponent implements OnInit {

  id;
  itemData;
  category;
  name;
  url=environment.serverUrl;
  constructor(private http: AddHeaderInHttpService, private route: ActivatedRoute) {
   
    route.params.subscribe(val => {

      this.category = this.route.snapshot.paramMap.get('category');
      this.name = this.route.snapshot.paramMap.get('name');
      this.http.get(environment.serverUrl + "searchProduct/" +  this.category+"/"+this.name).
        map(response => response.json()).
        subscribe(data => {this.itemData = data
         
          },

          (error:Error)=>{ alert(error.message)});
    });
    
  
   }

  ngOnInit() {
  }

}
