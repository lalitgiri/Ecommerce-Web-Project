import { Component, OnInit } from '@angular/core';
import { AddHeaderInHttpService } from '../../../service/add-header-in-http.service';
import { environment } from '../../../../environments/environment';
import { FormGroup, FormControl } from '@angular/forms';
import { debug } from 'util';

@Component({
  selector: 'app-modify-vendor',
  templateUrl: './modify-vendor.component.html',
  styleUrls: ['./modify-vendor.component.css']
})
export class ModifyVendorComponent implements OnInit {

  employeeData:any;;
  updateEmployeeForm;
  selectedFile:any;
  imgFlag;

  employeeId;
  employeeName;
  dob;
  employeeRole;
  contactNumber;
  password;
  empImage;
  address;
  constructor(private http: AddHeaderInHttpService) { }

  ngOnInit() {

    this.setTable();

    this.updateEmployeeForm = new FormGroup({
      employeeId: new FormControl({ value: "", disabled: true }),
      employeeName: new FormControl(""),
      dob: new FormControl(""),
      employeeRole: new FormControl(""),
      contactNumber: new FormControl(""),
      password:new FormControl(""),
      empImage: new FormControl(""),
      address: new FormControl("")
    });


  }

  setTable(){

    this.http.get(environment.serverUrl + '/getallemployee').map(res => res.json()).subscribe(data => {
      this.employeeData = data;
    });
  }

  
  onSelect(data) {
    this.employeeId=data.employeeId;
    this.employeeName=data.employeeName;
    this.dob=data.dob;
    this.employeeRole=data.employeeRole;
    this.contactNumber=data.contactNumber;
    this.password=data.password;
    this.empImage=data.empImage;
    this.address=data.address;
}

onImageSelected(event) {

  this.selectedFile = <File>event.target.files[0];
  let fd = new FormData();
  fd.append('file', this.selectedFile, this.selectedFile.name);
  this.http.post(environment.serverUrl + 'upload/employee',
   fd, ).subscribe((res) =>this.empImage = res,
                                           (error:Error)=>{ alert(error.message)});
}
imageViewFunction(path){
  this.empImage=environment.serverUrl+'images/'+path;
  this.imgFlag=true;
}

onUpdate(data) {
  
  let employeeData =
    {
      "employeeId":this.employeeId,
      "employeeName":data.employeeName,
      "dob":data.dob,
      "employeeRole":data.employeeRole,
      "contactNumber":data.contactNumber,
      "password":data.password,
      "empImage":this.empImage,
      "address":data.address
      
    };
    
    console.log(employeeData)

this.http.post(environment.serverUrl+"updateemployee/"+ this.employeeId,employeeData)
    .subscribe(res => {
      console.log(res);
        alert(res);
      this.setTable();
      this.updateEmployeeForm.reset();
    });

}


}
