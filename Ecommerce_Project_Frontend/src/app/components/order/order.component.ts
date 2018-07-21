import { Component, OnInit, Input } from '@angular/core';
import { TokenDecoderService } from '../../service/token-decoder.service';
import { Http } from '@angular/http';
import { environment } from '../../../environments/environment';
import { DataSharingService } from '../../service/data-sharing.service';
import { Route } from '@angular/compiler/src/core';
import { ActivatedRoute } from '@angular/router';
import { AddHeaderInHttpService } from '../../service/add-header-in-http.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',

  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {


  flag = 0;
  pFlag;
  addCardDetail
  parsedToken = this.tokenDecoder.decodeToken(sessionStorage.getItem("token"));
  userId = this.parsedToken.phoneNumber;
  cartId = this.parsedToken.userId;
  userDetails;

  address;
  editAddressFlag = true;
  editAddressButton = 'edit';

  cartDetails;
  products;
  productId;
  summaryFlag = null;
  imgUrl = environment.serverUrl + "images/";
  cost = 0;
  order;
  quantity = 0;

  constructor(private tokenDecoder: TokenDecoderService, private route: ActivatedRoute, private http: AddHeaderInHttpService, private dataSharingService: DataSharingService) { }


  ngOnInit() {
    //this.products = this.dataSharingService.getProduct().productId;

    this.http.get(environment.serverUrl + "getuser/" + this.userId)
      .map(response => response.json())
      .subscribe(data => {
        this.userDetails = data;
        this.address = this.userDetails.cart.deliveryAddress;
        //  console.log(this.userDetails)
        //console.log(this.address);

      }, (error: Error) => { alert(error.message) });

    //console.log(this.imgUrl)
    this.summaryFlag = this.route.snapshot.paramMap.get('id');

    if (this.summaryFlag == 'product') {
      this.products = JSON.parse(sessionStorage.getItem('services_assigned'));
      this.quantity = JSON.parse(sessionStorage.getItem('quantity'));
      this.cost = this.products.productPrice;
    }
    else {
      this.products = JSON.parse(sessionStorage.getItem('services_assigned')).productQuantity;

      this.findProductPrice();
    }
    // console.log("hello" + this.products)

  }


  edit() {
    this.editAddressFlag = !this.editAddressFlag;
    if (this.editAddressButton == "save")
      this.editAddressButton = 'edit';
    else
      this.editAddressButton = 'save';

  }

  onclick(flag) {
    if (this.flag == flag)
      flag = 0;
    this.flag = flag;
    if (flag == 6)
      this.pFlag = "payment"
    else
      this.pFlag = null;
  }


  findProductPrice() {
    this.products.forEach(element => {
      this.cost = (element.productTable.productPrice * element.quantity) + this.cost;

    });
  }

  payment(data) {
    if (this.summaryFlag == 'product') {
      this.order = {
        "userId": this.userDetails,
        "address": this.address,
        "itemDetail": this.products,
        "quantity": this.quantity,
        "status": true
      }
      console.log(this.address);
      if (this.address != '')
        if (this.address != '') {

          this.http.post(environment.serverUrl + 'addorder', this.order)
            .subscribe((response) => {
              this.flag = data;
              this.pFlag = 'hi';
            },
              (error: Error) => { alert(error.message) });
        }

        else
          alert("Provide Address !!!")
    }
    if (this.summaryFlag == 'cart') {

      this.order = {
        "userId": this.userDetails.phoneNumber,
        "deliveryAddress": this.address,
        "productQuantity": this.products

      }

      if (this.address != '')
        if (this.address != null) {
          this.http.post(environment.serverUrl + 'add_cart_order', this.order)
            .subscribe((response) => {


              this.flag = data;
              this.pFlag = 'hi';
            }//,(error: Error) => { alert(error.message) }
            );
        }
        else
          alert("Provide Address !!!")

      // console.log(this.order);


    }

  }

  print(): void {
    let printContents, popupWin;
    printContents = document.getElementById('printContent').innerHTML;
    popupWin = window.open('', '_blank', 'top=10px,left=10px,height=100%,width=auto');
    popupWin.document.open();
    popupWin.document.write(`
      <html>
        <head>
          <title>Print tab</title>
         
          <style>
          .parent-div{
            background: white;
            text-align: initial;
            vertical-align: text-top;
        }
        .details{
            margin-left: 3vw;
            padding: 1vw;
            text-align: left;
            width:40%;
            height:auto;
            display: inline-block;
            vertical-align: text-top;
        
        }
        
        .date-header{
          
            padding: 1vw;
            text-align: right;
            width:50%;
            height:auto;
            display: inline-block;
            vertical-align: text-top;
        }
        
        .user{
            background: wheat;
        }
        
        .user-details{
        
            margin-left: 3vw;
            padding: 1vw;
            text-align: left;
            width:40%;
            height:auto;
            display: block;
           
        }
        
        .order-details{
            margin: 3vw;
            min-height: 40vw;
            padding-bottom: 20%;
            
        }
        table{
            border:1px solid black;
            margin: 0 auto;
            min-height:40vh;
            vertical-align: text-top;
        }
        .item{
            padding: 1vw;
            width:10%;
        }
        
        .description{
            text-align: center;
            padding: 1vw;
            width:62%;
        }
        thead{
            border-bottom: 1px solid black;
        }
        td{
            vertical-align: text-top;
            text-align: center;
        }
        h1{
            text-align: center;
        }
        
        
          </style>
        </head>
    <body onload="window.print();window.close()">${printContents}</body>
      </html>`
    );
    popupWin.document.close();
  }

}
