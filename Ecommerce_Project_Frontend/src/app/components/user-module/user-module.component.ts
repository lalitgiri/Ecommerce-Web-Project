import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { environment } from '../../../environments/environment';
import { TokenDecoderService } from '../../service/token-decoder.service';
import { AddHeaderInHttpService } from '../../service/add-header-in-http.service';

@Component({
  selector: 'app-user-module',
  templateUrl: './user-module.component.html',
  styleUrls: ['./user-module.component.css']
})
export class UserModuleComponent implements OnInit {

  constructor(private http: AddHeaderInHttpService, private tokenDecoder: TokenDecoderService) { }

  url = environment.serverUrl;
  orders;
  products;
  imgUrl = environment.serverUrl + 'images/';
  flag = 0;
  parsedToken = this.tokenDecoder.decodeToken(sessionStorage.getItem("token"));
  id = this.parsedToken.phoneNumber;
  userDetails: any;
  name;
  address;
  email;
  phone;
  response = false;
  currentPass;
  cpass;
  npass;
  rpass;
  ngOnInit() {
    this.http.get(this.url + '/getuser/' + this.id).map(res => res.json()).subscribe(data => {
      // console.log("user Data : "+data);
      this.userDetails = data;
      this.name = data.name;
      this.address = data.cart.deliveryAddress;
      this.email = data.emailId;
      this.phone = data.phoneNumber;
      this.currentPass = data.password;
      this.response = true;
    });

    this.http.get(this.url + 'getallorder').map(res => res.json()).subscribe(data => {
      this.orders = data;
      this.products = data.itemDetail;
      //console.log(data);
      //console.log(data[0].itemDetail);
    })

  }

  saveButtonFlag(num) {
    this.flag = num;
    this.name = this.userDetails.name;
    this.address = this.userDetails.cart.deliveryAddress;
    this.email = this.userDetails.emailId;
    this.phone = this.userDetails.phoneNumber;
  }
  save() {
    this.flag = 0;

    this.userDetails.name = this.name;
    this.userDetails.cart.deliveryAddress = this.address;
    this.userDetails.emailId = this.email;
    this.userDetails.phoneNumber = this.phone;

    this.http.post(this.url + '/updateuser/' + this.id, this.userDetails).map(res => res.text())
      .subscribe(data => {
        alert(data);
      })

  }

  changePass() {
    
    if (this.currentPass == this.cpass) {
      if (this.rpass == this.npass) {
          this.userDetails.password=this.npass;
        this.http.post(this.url + '/updateuser/' + this.id, this.userDetails).map(res => res.text())
      .subscribe(data => {
        alert(data);
      })

        this.currentPass=this.npass;
        this.cpass = '';
        this.npass = '';
        this.rpass = '';
        window.location.reload();
       
      }

      else
        alert("New And Confirm Password Does Not Match !!!")
    }
    else
      alert("Invalid Current Password");

     

  }


}
