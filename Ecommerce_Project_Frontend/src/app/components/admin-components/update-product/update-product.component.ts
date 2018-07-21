import { Component, OnInit, OnChanges } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Http, Response } from '@angular/http';
import { environment } from '../../../../environments/environment';
import { FormGroup, FormControl } from '@angular/forms'
@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit  {

  updateProductForm;
  selectedFile: any;
  itemData: any;
  data;

  imgFlag=false;
  productid;
  UproductName;
  UproductCategory;
  UproductQuantity;
  UproductPrice;
  description;
  viewImage;
  imgUrl;
  url = environment.serverUrl;
  constructor(private httpClient: HttpClient, private http: Http) { }
  
  ngOnInit() {
    this.setTable();
    this.updateProductForm = new FormGroup({
      productid: new FormControl({ value: "", disabled: true }),
      UproductName: new FormControl(""),
      UproductCategory: new FormControl(""),
      UproductQuantity: new FormControl(""),
      UproductPrice: new FormControl(""),
      description: new FormControl("")
    });
  }

  onSelect(data) {
    this.productid = data.productId;
    this.UproductName = data.productName;
    this.UproductCategory = data.productCategory;
    this.UproductQuantity = data.productQuantity;
    this.UproductPrice = data.productPrice;
    this.description = data.description;
    this.imgUrl = data.imageUrl;

  }
  setTable() {
    this.http.get(environment.serverUrl + "getallproduct").
      map(response => response.json()).
      subscribe(data => this.itemData = data,
        (error:Error)=>{ alert(error.message)});
  }

  onImageSelected(event) {

    this.selectedFile = <File>event.target.files[0];
    let fd = new FormData();
    fd.append('file', this.selectedFile, this.selectedFile.name);
    this.httpClient.post(environment.serverUrl + 'upload/' + this.UproductCategory,
     fd, { responseType: 'text' }).subscribe((res) =>this.imgUrl = res,
                                             (error:Error)=>{ alert(error.message)});
  }
  imageViewFunction(path){
    this.viewImage=this.url+'images/'+path;
    this.imgFlag=true;
  }
  onUpdate(formValue) {
    this.data =
      {
        "productId": this.productid,
        "productName": formValue.UproductName,
        "productCategory": formValue.UproductCategory,
        "productQuantity": formValue.UproductQuantity,
        "productPrice": formValue.UproductPrice,
        "description": formValue.description,
        "imageUrl": this.imgUrl,
        "status":true
      };

      console.log(formValue)
      
  this.httpClient.post(environment.serverUrl + '/updateproduct/' + this.productid, this.data, { responseType: 'text' })
      .subscribe(res => {
        alert(res);
        this.setTable();
        this.updateProductForm.reset();
      });

  }

}
