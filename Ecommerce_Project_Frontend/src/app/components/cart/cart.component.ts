import { Component, OnInit } from '@angular/core';
import { environment } from '../../../environments/environment';
import { TokenDecoderService } from '../../service/token-decoder.service';
import { Router } from '@angular/router';
import { DataSharingService } from '../../service/data-sharing.service';
import { AddHeaderInHttpService } from '../../service/add-header-in-http.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  itemData;
  productData;
  imgurl = environment.serverUrl;;
  parsedToken = this.tokenDecoder.decodeToken(sessionStorage.getItem("token"));
  id = this.parsedToken.userId;
  name = this.parsedToken.userName;
  address;
  email = this.parsedToken.sub;
  cartPrice = 0;
  quantity: number[];
  quantityx: number[];


  constructor(private http:AddHeaderInHttpService, private tokenDecoder:TokenDecoderService,private router:Router) {
  
    this.http.get(environment.serverUrl + "token/getcart/" + this.id).
      map(response => response.json()).
      subscribe(data => {
      //  console.log(data);
        this.itemData = data
        this.productData = this.itemData.productQuantity;
        this.address = this.itemData.deliveryAddress;
       
        this.findProductPrice()
      //console.log(this.quantity);

      }, (error: Error) => { /*alert(error.message) */});

  }

 

  findProductPrice() {
    this.productData.forEach(element => {
      this.cartPrice = (element.productTable.productPrice * element.quantity )+ this.cartPrice;

    });

  }
  

  removeData(data) {
   // console.log(data);
    this.id;
    this.http.post(environment.serverUrl +'token/removefromcart/'+this.id, data)
      .subscribe((response) => { window.location.reload(); },
        (error: Error) => { /*alert(error.message)*/
        });
        
      }

  ngOnInit() {

  }

  onClick(){
    sessionStorage.setItem('services_assigned', JSON.stringify(this.itemData));
    //this.dataSharingService.setProduct(this.itemData);
    this.router.navigate(['/order',"cart"]);
  }
}
