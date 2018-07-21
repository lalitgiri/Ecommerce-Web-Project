import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { FormGroup, FormControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

import { PatternValidator } from '@angular/forms';
import { environment } from '../../../../environments/environment';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private http:Http) { }
  
  SignUpForm;
  isd_code;
  ngOnInit() {
    this.http.get("../../assets/products/isd_code.json").
      map(response => response.json()).
      subscribe(data => this.isd_code = data);// console.log(this.isd_code)
    
      this.SignUpForm=new FormGroup({
        "name":new FormControl(""),
        "emailId": new FormControl(""),
        "phoneNumber": new FormControl(""),
        "password":new FormControl(""),

      })
  
    }
    

    onUpdate(data){
    //  console.log(data);
      this.http.post(environment.serverUrl + 'adduser',data)
      .subscribe(response => {
         if(response.text().length>1){
          sessionStorage.setItem("token",response.text());
           window.location.reload();
        }
        
       
      },
      (error:Error)=>{
        alert(error.message)
        this.SignUpForm.reset();
      });
     
    }


}
