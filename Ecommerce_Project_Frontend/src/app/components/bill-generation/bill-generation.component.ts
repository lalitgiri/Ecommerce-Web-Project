import { Component, OnInit } from '@angular/core';
import { TokenDecoderService } from '../../service/token-decoder.service';

@Component({
  selector: 'app-bill-generation',
  templateUrl: './bill-generation.component.html',
  styleUrls: ['./bill-generation.component.css'],
  inputs: ['orderDetails','summaryFlag']
})
export class BillGenerationComponent implements OnInit {

  constructor(private tokenDecoder: TokenDecoderService) {
    if (sessionStorage.getItem("token") != null) {
     
      var parsedToken = tokenDecoder.decodeToken(sessionStorage.getItem("token"));
      this.name = parsedToken.userName;
      
     
    }
  }
  name;
  today = Date.now();
  orderDetails;
  summaryFlag;
  total=0;
  ngOnInit() {
    if(this.summaryFlag=='cart')
      this.hello();
    else
      this.total=this.orderDetails.quantity * this.orderDetails.itemDetail.productPrice;

   
  }
  hello(){
    this.orderDetails.productQuantity.forEach(element => {
      this.total=this.total+(element.quantity * element.productTable.productPrice)
    });
   
  }
  
}
