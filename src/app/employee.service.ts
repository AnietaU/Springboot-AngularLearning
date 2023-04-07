import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from './employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private  baseURL= "http://localhost:8080/api/v1/employees"
  constructor(private httpClient: HttpClient) { }

  getEmployeesList():Observable<Employee[]>{
    return this.httpClient.get<Employee[]>(`${this.baseURL}`);
  }

  saveEmployee(employee: Employee):Observable<Employee>{
   return this.httpClient.post<Employee>(this.baseURL, employee);
  }

  getEmployeeById(id:number):Observable<Employee>{
    console.log(`${this.baseURL}/${id}`);
    return this.httpClient.get<Employee>(`${this.baseURL}/${id}`);


  }

  updateEmployee(id:number, emp:Employee):Observable<Object>{
    return this.httpClient.put<Object>(`${this.baseURL}/update/${id}`, emp);
  }

  deleteEmployee(id:number):Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }






  
}
