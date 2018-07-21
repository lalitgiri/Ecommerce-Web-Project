import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { environment } from '../../../../environments/environment';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.css']
})
export class DeleteComponent implements OnInit {

  itemData: any;
  idArray=[]
  index = 0;
  url = environment.serverUrl;
  imgFlag = false;
  viewImage;

  constructor(private http: Http,private httpClient:HttpClient) { }

  ngOnInit() {
    this.setTable();
  }
  setTable() {
    this.http.get(environment.serverUrl + "getallproduct").
      map(response => response.json()).
      subscribe(data => {this.itemData = data},
        (error:Error)=>{ alert(error.message)});
  }

  add(id) {
    this.idArray[this.index] = id
    this.index++;
  }
  toggleEditable(event, id) {
    if (event.target.checked)
      this.add(id)
    else
      this.remove(id)

      console.log(this.idArray);
  }

  remove(id) {
    const index: number = this.idArray.indexOf(id);
    if (index !== -1) {
      this.idArray.splice(index, 1);
      
    }
  }
  imageViewFunction(path) {
    this.viewImage = this.url + 'images/' + path;
    this.imgFlag = true;
  }

  cancel(){
    this.setTable();
    this.idArray=[]
    this.index = 0
  }

  deleteItems(){
    this.httpClient.post(environment.serverUrl + 'deleteproduct', this.idArray, { responseType: 'text' })
    .subscribe((response) => {alert(response),this.setTable()
           
      },
              (error:Error)=>{ alert(error.message)});
  }
}
