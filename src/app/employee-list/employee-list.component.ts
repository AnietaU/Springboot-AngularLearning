import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {Employee} from '../employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  employees : Employee[] | undefined;

  constructor(private employeeService:EmployeeService, private _router:Router) { }

  ngOnInit(): void {
    this.getEmployees();
  }

 private getEmployees() {
   this.employeeService.getEmployeesList().subscribe(data => {
    this.employees= data;
   });
  }

  updateEmployee(id:number|undefined){
    this._router.navigate(['update-employee', id]);
  }

  deleteEmployee(id:number|any){
    this.employeeService.deleteEmployee(id).subscribe(data =>
       { this.getEmployees();
       })
    
  }

  viewEmployeeDetails(id:number | undefined){
    this._router.navigate(['employee-details', id]);
  }

}
