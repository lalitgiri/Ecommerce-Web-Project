import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Http } from '@angular/http';
import { Router } from '@angular/router';
import { environment } from '../../../../environments/environment.prod';

@Component({
  selector: 'app-employee-login',
  templateUrl: './employee-login.component.html',
  styleUrls: ['./employee-login.component.css']
})
export class EmployeeLoginComponent implements OnInit {

  LoginForm;
  constructor(private router:Router,private http:Http) { }

  ngOnInit() {
    this.LoginForm=new FormGroup({
      "Username":new FormControl(""),
      "lpassword":new FormControl("")

    })
  }

  
  onLogin(data){
    console.log(data);
    
    this.http.post(environment.serverUrl + 'getAdminAuthentication',data)
    .subscribe(response => {
      if(response.text().length>1)
      { 
        sessionStorage.setItem("user","admin");
        sessionStorage.setItem("token",response.text());
        console.log(response.text());
        window.location.reload();
      }
      else 
        alert("Invalid Credentials"); 
      console.log(sessionStorage.getItem("token"));
      this.LoginForm.reset();
    },
    (error:Error)=>alert("Error :"+error.message));
   

  }

}
