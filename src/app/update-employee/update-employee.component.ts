import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent implements OnInit {

  employee: Employee= new Employee();
  id: number | any ;

  constructor(private _employeeService:EmployeeService, private route:ActivatedRoute, private _router:Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this._employeeService.getEmployeeById(this.id).subscribe(data => {
        this.employee=data;
        console.log(this.id);
        console.log(this.employee);
    },
    error =>{console.log(error);}
      );
  }

  onSubmit(){
    this._employeeService.updateEmployee(this.id,this.employee).subscribe(data => 
       {this.goToEmployeeList();}, error => console.log(error));
      

  }

  goToEmployeeList(){
    this._router.navigate(['/employees']);
  }
  
}
