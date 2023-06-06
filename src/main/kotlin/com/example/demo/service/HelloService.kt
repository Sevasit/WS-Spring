package com.example.demo.service

import com.example.demo.model.Employee
import com.example.demo.model.Sumsal
import com.example.demo.repository.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import net.objecthunter.exp4j.ExpressionBuilder
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import java.util.*

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
        try {
            var result = ExpressionBuilder(formula).build()
            var res = result.evaluate()
            return res.toString();
        } catch (e: Exception) {
            throw IllegalStateException("Your formula is invalid.")
        }
    }

    //WorkShop2 *******************************************************************************************

    fun getEmployees(): List<Employee> {
        var results: List<Employee> = employeeRepository.findAll()
        return results
    }

    fun createEmp(employee: Employee): String {
        if (employeeRepository.existsById(employee.empNo)) {
            throw IllegalStateException("Created employee not success.")
        } else {
            employeeRepository.save(employee)
            return "Created employee success."
        }
    }

    fun updateEmployeesId(empno: String, employee: Employee): String {
        val employFind = employeeRepository.findById(empno)
        if (employFind.isEmpty) {
            throw IllegalStateException("Update not success.")
        }

        val empOne = employeeRepository.findByEmpNo(empno)

//        empOne.empNo = if(employee.empNo.isEmpty()) empOne.empNo else employee.empNo
        empOne.ename = if (employee.ename == "" || employee.ename == null) empOne.ename else employee.ename
        empOne.job = if (employee.job == "" || employee.job == null) empOne.job else employee.job
        empOne.mgr = if (employee.mgr == "" || employee.mgr == null) empOne.mgr else employee.mgr
        empOne.hiredate =
            if (employee.hiredate.toString() == "" || employee.hiredate == null) LocalDate.now() else employee.hiredate
        empOne.sal = if (employee.sal.toString() == "" || employee.sal == null) empOne.sal else employee.sal
        empOne.commissionPct =
            if (employee.commissionPct.toString() == "" || employee.commissionPct == null) empOne.commissionPct else employee.commissionPct
        empOne.deptno =
            if (employee.deptno.toString() == "" || employee.deptno == null) empOne.deptno else employee.deptno
        employeeRepository.save(empOne)

        return "Update success."

    }

    fun deleteEmployeesId(empno: String): String {
        if (!employeeRepository.existsById(empno)) {
            throw IllegalStateException("Deleted employee not success.")
        } else {
            employeeRepository.deleteById(empno)
            return "Deleted employee success."
        }
    }

    fun getEmployeesId(empno: String): Employee {
        if (!employeeRepository.existsById(empno)) {
            throw IllegalStateException("Not found employee.")
        }
        val results = employeeRepository.findByEmpNoEquals(empno)
        return results
    }

    fun getEmployeesSeach(field: String, value: String): List<Employee> {
        when (field) {
            "ename" -> {
                if(employeeRepository.findByEname(value).isEmpty()){
                    throw IllegalStateException("Not found.")
                }
                return employeeRepository.findByEname(value)
            }
            "job" -> {
                if(employeeRepository.findByJob(value).isEmpty()){
                    throw IllegalStateException("Not found.")
                }
                return employeeRepository.findByJob(value)
            }
            "mgr" -> {
                if(employeeRepository.findByMgr(value).isEmpty()){
                    throw IllegalStateException("Not found.")
                }
                return employeeRepository.findByMgr(value)
            }
            "hiredate" -> {
                val date = LocalDate.parse(value, DateTimeFormatter.ISO_DATE)
                if(employeeRepository.findByHiredate(date).isEmpty()){
                    throw IllegalStateException("Not found.")
                }
                return employeeRepository.findByHiredate(date)
            }
            "sal" -> {
                if(employeeRepository.findBySal(value.toDouble()).isEmpty()){
                    throw IllegalStateException("Not found.")
                }
                return employeeRepository.findBySal(value.toDouble())
            }
            "commissionPct" -> {
                if(employeeRepository.findByCommissionPct(value.toDouble()).isEmpty()){
                    throw IllegalStateException("Not found.")
                }
                return employeeRepository.findByCommissionPct(value.toDouble())
            }
            "deptno" -> {
                if(employeeRepository.findByDeptno(value.toInt()).isEmpty()){
                    throw IllegalStateException("Not found.")
                }
                return employeeRepository.findByDeptno(value.toInt())
            }
            else -> {
                throw IllegalStateException("Invalid field.")
            }
        }
    }

//    fun getEmployeesEname(ename: String): List<Employee> {
//        if (employeeRepository.findByEname(ename).isEmpty()) {
//            throw IllegalStateException("Not found employee.")
//        }
//        return employeeRepository.findByEname(ename)
//    }
//
//    fun getEmployeesJob(job: String): List<Employee> {
//        if (employeeRepository.findByJob(job).isEmpty()) {
//            throw IllegalStateException("Not found employee.")
//        }
//        return employeeRepository.findByJob(job)
//    }
//
//    fun getEmployeesMgr(mgr: String): List<Employee> {
//        if (employeeRepository.findByMgr(mgr).isEmpty()) {
//            throw IllegalStateException("Not found employee.")
//        }
//        return employeeRepository.findByMgr(mgr)
//    }
//
//    fun getEmployeesHiredate(hiredate: LocalDate): List<Employee> {
//        if (employeeRepository.findByHiredate(hiredate).isEmpty()) {
//            throw IllegalStateException("Not found employee.")
//        }
//        return employeeRepository.findByHiredate(hiredate)
//    }
//
//    fun getEmployeesSal(sal: Double): List<Employee> {
//        if (employeeRepository.findBySal(sal).isEmpty()) {
//            throw IllegalStateException("Not found employee.")
//        }
//        return employeeRepository.findBySal(sal)
//    }
//
//    fun getEmployeesCommissionPct(commissionPct: Double): List<Employee> {
//        if (employeeRepository.findByCommissionPct(commissionPct).isEmpty()) {
//            throw IllegalStateException("Not found employee.")
//        }
//        return employeeRepository.findByCommissionPct(commissionPct)
//    }
//
//    fun getEmployeesDeptno(deptno: Int): List<Employee> {
//        if (employeeRepository.findByDeptno(deptno).isEmpty()) {
//            throw IllegalStateException("Not found employee.")
//        }
//        return employeeRepository.findByDeptno(deptno)
//    }

    fun getEmployeesSumSalAndCountEmployee(mgr: String): Optional<Sumsal> {
        if (employeeRepository.findSumSalAndCountEmployee(mgr).isEmpty) {
            throw IllegalStateException("Not found employee.")
        }
        return employeeRepository.findSumSalAndCountEmployee(mgr)
    }
}