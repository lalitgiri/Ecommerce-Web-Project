import { Component, OnInit } from '@angular/core';
import { Response } from "@angular/http";
import { HttpClient } from '@angular/common/http';
import 'rxjs/add/operator/catch';
import { environment } from '../../../../environments/environment';
@Component({
  selector: 'app-add-vendor',
  templateUrl: './add-vendor.component.html',
  styleUrls: ['./add-vendor.component.css']
})
export class AddVendorComponent implements OnInit {

  constructor(private http: HttpClient) { }
  data: any;
  selectedFile: any;
  imgUrl: string = null;
  flag: boolean = false;
  url = environment.serverUrl;
  ngOnInit() {
   
  }


  onImageSelected(event) {

    this.selectedFile = <File>event.target.files[0];
    let fd = new FormData();
    fd.append('file', this.selectedFile, this.selectedFile.name);
    this.http.post(environment.serverUrl + 'upload/employee_images', fd, { responseType: 'text' })
      .subscribe((res) => {
        this.imgUrl = res
        this.flag = true;
      },
      (error:Error)=>{ alert(error.message)});

  }

  onSubmit = function (employee) {
    if (employee.employeeName != null && employee.contactNumber != null && employee.employeeRole != null
      && employee.address != null && employee.joiningDate != null && this.imgUrl != null) {
      this.data =
        {
          "employeeName": employee.employeeName,
          "employeeRole": employee.employeeRole,
          "empImageUrl": this.imgUrl,
          "dob": employee.dob,
          "password": employee.contactNumber,
          "contactNumber": employee.contactNumber,
          "address": employee.address,
          "status": true,
          "joiningDate": employee.joiningDate,
          "resigningDate": null
        };
      //const body = JSON.stringify(this.data);

      //const  headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded'});    //x-www-form-urlencoded
      // headers.append('Access-Control-Allow-Origin','*');
      // let options = new RequestOptions({ headers: headers });
      // this.http.post('http://localhost:8080/addemployee',this.data,headers).subscribe((response: Response) => { console.log(response) });
      this.http.post(environment.serverUrl + 'addemployee', this.data, { responseType: 'text' })
        .subscribe((response: Response) => { alert(response) },
        (error:Error)=>{ alert(error.message)});
       
    }
  }
}




