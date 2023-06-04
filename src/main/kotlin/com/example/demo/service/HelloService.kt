package com.example.demo.service

import com.example.demo.model.Employee
import com.example.demo.repository.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class HelloService {
    @Autowired
    lateinit var employeeRepository: EmployeeRepository

    //WorkShop 1
    fun calGrade(score: Int): String {
        var grade: String = "";
        try {
            if (score > 100) {
                grade = "Your score gather than 100 , Can't calculate."
            } else if (score <= 0) {
                return "Should input number gather than 0."
            } else if (score >= 80) {
                grade = "A"
            } else if (score >= 75) {
                grade = "B+"
            } else if (score >= 70) {
                grade = "B"
            } else if (score >= 65) {
                grade = "C+"
            } else if (score >= 60) {
                grade = "C"
            } else if (score >= 55) {
                grade = "D+"
            } else if (score >= 50) {
                grade = "D"
            } else if (score < 50) {
                grade = "F"
            } else {
                grade = "Invalid Score!"
            }
            return grade
        } catch (err: Exception) {
            return "Errorrrrrrr"
        }
    }

    fun calByUser(formula: String): String {
        return formula;
    }

    //WorkShop2

    fun getEmployees(): List<Employee> {
        var results: List<Employee> = employeeRepository.findAll()
        return results
    }

    fun createEmp(employee: Employee): String {
        if (employeeRepository.existsById(employee.empNo)) {
            return "Created employee not success."
        } else {
            employeeRepository.save(employee)
            return "Created employee success."
        }
    }

    fun updateEmployeesId(empno: String, employee: Employee): String {
        val employFind = employeeRepository.findById(empno)
        if (employFind.isEmpty) {
            return "Update not success."
        }

        val empOne = employeeRepository.findByEmpNo(empno)

//        empOne.empNo = if(employee.empNo.isEmpty()) empOne.empNo else employee.empNo
        empOne.ename = if(employee.ename.toString().isEmpty()) empOne.ename else employee.ename
        empOne.job = if(employee.job.toString().isEmpty()) empOne.job else employee.job
        empOne.mgr = if(employee.mgr.toString().isEmpty()) empOne.mgr else employee.mgr
        empOne.hiredate = if(employee.hiredate.toString().isEmpty()) empOne.hiredate else employee.hiredate
        empOne.sal = if(employee.sal == null) empOne.sal else employee.sal
        empOne.commission_pct = if(employee.commission_pct == null) empOne.commission_pct else employee.commission_pct
        empOne.deptno = if(employee.deptno == null) empOne.deptno else employee.deptno
        employeeRepository.save(empOne)

        return "Update success."

    }

    fun deleteEmployeesId(empno: String): String {
        if (!employeeRepository.existsById(empno)) {
            return "Deleted employee not success."
        } else {
            employeeRepository.deleteById(empno)
            return "Deleted employee success."
        }
    }

    fun getEmployeesId(empno: String): Employee {
        var results = employeeRepository.findByEmpNoEquals(empno)
        return results
    }


}