import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Http } from '@angular/http';

@Injectable()
export class DataSharingService {

  public product:any;
  constructor() { 

  }
   setProduct(product): void{
    this.product=product;

  
  }

   getProduct(): any{

    return this.product;
  
  }
}
