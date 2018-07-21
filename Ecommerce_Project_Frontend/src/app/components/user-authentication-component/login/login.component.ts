import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { Http } from '@angular/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  LoginForm;
  constructor(private router:Router,private http:Http) { }

  ngOnInit() {
    this.LoginForm=new FormGroup({
      "Username":new FormControl(""),
      "lpassword":new FormControl("")

    })
  }
  onLogin(data){
   // console.log(data);
    this.http.post(environment.serverUrl + 'getAuthentication',data)
    .subscribe(response => {
      if(response.text().length>1)
      { 
        sessionStorage.setItem("token",response.text());
        window.location.reload();
      }
      else 
        alert("Invalid Credentials"); 
     // console.log(sessionStorage.getItem("token"));
      this.LoginForm.reset();
    },
    (error:Error)=>alert("Error :"+error.message));
   
  }

}
