package com.example.demo.controller

import com.example.demo.model.Employee
import com.example.demo.model.Sumsal
import com.example.demo.service.HelloService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@RestController
class HelloController {


    @Autowired
    lateinit var helloService: HelloService

    val regex = "-?[0-9]+(\\.[0-9]+)?".toRegex()

    //WorkShop2
    @GetMapping("employee")
    fun getEmployees(): List<Employee> {
        return helloService.getEmployees()
    }

    @PostMapping("createdEmp")
    fun createdEmp(@RequestBody employee: Employee): String {
        return helloService.createEmp(employee)
    }

    @PatchMapping("updateEmp/{empNo}")
    fun updateEmp(@PathVariable("empNo") empNo: String, @RequestBody employee: Employee): String {
        return helloService.updateEmployeesId(empNo, employee)
    }

    @DeleteMapping("employee/{empNo}")
    fun deletedEmployees(@PathVariable("empNo") empNo: String): String {
        return helloService.deleteEmployeesId(empNo)
    }

    @GetMapping("employee/{empNo}")
    fun getEmployeesByEmpNo(@PathVariable("empNo") empNo: String): Employee {
        return helloService.getEmployeesId(empNo)
    }

    @GetMapping("seachBy/{field}/{value}")
    fun getEmployeesBySeach(@PathVariable("field") field: String,@PathVariable("value") value: String): List<Employee> {
        return helloService.getEmployeesSeach(field,value)
    }

//    @GetMapping("employeeByEname/{ename}")
//    fun getEmployeesByEname(@PathVariable("ename") ename: String): List<Employee> {
//        return helloService.getEmployeesEname(ename)
//    }
//
//    @GetMapping("employeeByJob/{job}")
//    fun getEmployeesByJob(@PathVariable("job") job: String): List<Employee> {
//        return helloService.getEmployeesJob(job.uppercase())
//    }
//
//    @GetMapping("employeeByMgr/{mgr}")
//    fun getEmployeesByMgr(@PathVariable("mgr") mgr: String): List<Employee> {
//        return helloService.getEmployeesMgr(mgr)
//    }
//
//    @GetMapping("employeeByHiredate/{hiredate}")
//    fun getEmployeesByHiredate(@PathVariable("hiredate") hiredate: String): List<Employee> {
//        val date = LocalDate.parse(hiredate, DateTimeFormatter.ISO_DATE)
//        return helloService.getEmployeesHiredate(date)
//    }
//
//    @GetMapping("employeeBySal/{sal}")
//    fun getEmployeesBySal(@PathVariable("sal") sal: Double): List<Employee> {
//        return helloService.getEmployeesSal(sal)
//    }
//
//    @GetMapping("employeeByCommissionPct/{commissionPct}")
//    fun getEmployeesByCommissionPct(@PathVariable("commissionPct") commissionPct: Double): List<Employee> {
//        return helloService.getEmployeesCommissionPct(commissionPct)
//    }
//
//    @GetMapping("employeeByDeptno/{deptno}")
//    fun getEmployeesByDeptno(@PathVariable("deptno") commissionPct: Int): List<Employee> {
//        return helloService.getEmployeesDeptno(commissionPct)
//    }

    @GetMapping("employeeBySumSalAndCountEmployee/{mgr}")
    fun getEmployeesBySumSalAndCountEmployee(@PathVariable("mgr") mgr: String): Optional<Sumsal> {
        return helloService.getEmployeesSumSalAndCountEmployee(mgr)
    }

    //WorkShop 1
    @GetMapping("calculateGrade/{score}")
    fun hello(@PathVariable("score") score: String)
            : String {
        if (score.matches(regex)) {
            return helloService.calGrade(score.trim().toInt())
        } else {
            return "Input is invalid"
        }
    }

    @PostMapping("calculateByUser")
    fun calculateByUser(@RequestBody formula : String): String {
        return helloService.calByUser(formula)
    }
}