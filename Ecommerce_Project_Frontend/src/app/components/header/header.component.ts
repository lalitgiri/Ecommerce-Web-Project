import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Http, RequestOptions, Headers } from '@angular/http';
import { Router } from '@angular/router';
import { TokenDecoderService } from '../../service/token-decoder.service';
import { ControlValueAccessor } from '@angular/forms';
import { AddHeaderInHttpService } from '../../service/add-header-in-http.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  token = false;
  tokenValue = true;
  status: boolean = false;
  name = "";
  category="";
  user;
  constructor(private http: AddHeaderInHttpService, private router: Router, private cdRef: ChangeDetectorRef,
    private tokenDecoder: TokenDecoderService) {
    if (sessionStorage.getItem("token") != null) {
      this.token = true;
      this.tokenValue = false;
      var parsedToken = tokenDecoder.decodeToken(sessionStorage.getItem("token"));
      this.name = parsedToken.userName.split(" ");
      this.user = parsedToken.role;
     
    }
   // console.log("token : " + sessionStorage.getItem("token"));
  }

  itemData;
  flag = false;


  ngOnInit() {
    this.http.get(environment.serverUrl + "getallproductCategory").
      map(response => response.json()).
      subscribe(data => {
        this.itemData = data
        this.flag = true;
      },
        (error: Error) => { alert(error.message) });
  }

  search(data){
   
    this.router.navigate(['/search/'+data.category+"/"+data.name]);
    
  }
  change(data){
   // console.log(this.name+ " ");
  }

  inValidateSession() {
   // console.log("hye");
   
    let myheaders = new Headers({ 'Content-Type': 'application/json' });    //x-www-form-urlencoded
    myheaders.append('token', sessionStorage.getItem("token"));

    this.http.get(environment.serverUrl + "logout").
      map(response => response.json()).
      subscribe(data => {
    //   console.log(data);
        if (data == true) {
          sessionStorage.removeItem("token");
         // window.location.reload();
        {
            this.token = false;
         this.tokenValue = true;
         this.name ="";
         this.user = "";
        }alert("Sucessfully Logout");
          sessionStorage.clear();
          this.router.navigate(['/']);
        }
        if (data == false) {
        
        //  window.location.reload();
        {
          this.token = false;
       this.tokenValue = true;
       this.name ="";
       this.user = "";
      }
          sessionStorage.clear();
          this.router.navigate(['/']);
        }

      },
        (error: Error) => { 
          sessionStorage.clear();
          this.router.navigate(['/']);
          alert(error.message) });

  }



  clicked(event) {
    if (this.status == true)
      this.status = false;
    else
      this.status = true;
  }
  hide(event) {
    this.status = false;
  }
  navigate(category: string) {
    this.router.navigate(['/viewAll', category]);
  //  console.log(category);
  }
  ngAfterViewChecked() {
    this.cdRef.detectChanges();
  }
}
