import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  constructor(private router:Router) { }
  status:String="Welcome";

  user=sessionStorage.getItem("user");

  ngOnInit() {
  console.log(this.user);
  }
  operation(link) {
    this.router.navigate(['/admin', {outlets: {admin: link}}]);
  }
}
