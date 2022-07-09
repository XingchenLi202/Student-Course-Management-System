import { Component, OnInit } from '@angular/core';
import { Student } from '../student'
import { StudentService } from '../student.service'
import { Router } from '@angular/router';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {

  students: Student[] = [];

  constructor(private studentService: StudentService,
    private router: Router) { }

  ngOnInit(): void {
    // this.students=[{
    //   "studentid":1,
    //   "firstName":"Xing",
    //   "lastName":"Li"
    // },
    // {
    //   "studentid":2,
    //   "firstName":"Jun",
    //   "lastName":"D"
    // }];

    this.getStudents();
  }

  private getStudents(){
    this.studentService.getStudentsList().subscribe(data => {
      this.students = data;
    });
  }
}
