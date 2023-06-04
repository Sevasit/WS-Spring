package com.example.demo.controller

import com.example.demo.model.Employee
import com.example.demo.service.HelloService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {


    @Autowired
    lateinit var helloService: HelloService

    val regex = "-?[0-9]+(\\.[0-9]+)?".toRegex()

    //WorkShop2
    @GetMapping("employee")
    fun getEmployees() : List<Employee>{
        return helloService.getEmployees()
    }

    @PostMapping("createdEmp")
    fun createdEmp(@RequestBody employee: Employee) : String {
        return helloService.createEmp(employee)
    }

    @PatchMapping("updateEmp/{empNo}")
    fun updateEmp(@PathVariable("empNo") empNo : String,@RequestBody employee: Employee) : String{
        return helloService.updateEmployeesId(empNo,employee)
    }

    @DeleteMapping("employee/{empNo}")
    fun deletedEmployees(@PathVariable("empNo") empNo : String) : String{
        return helloService.deleteEmployeesId(empNo)
    }

    @GetMapping("employee/{empNo}")
    fun getEmployees(@PathVariable("empNo") empNo : String) : Employee{
        return helloService.getEmployeesId(empNo)
    }

    //WorkShop 1
    @GetMapping("calculateGrade/{score}")
    fun hello (@PathVariable("score") score : String)
    : String {
        if(score.matches(regex)){
            return helloService.calGrade(score.trim().toInt())
        }else{
            return "Input is invalid"
        }
    }

    @GetMapping("calculateByUser/{formula}")
    fun calculateByUser (@PathVariable("formula") formula : String)
    : String{
        val input = "5+5"
        val regexPattern = Regex("[1234567890+\\-*/.()]")
        val matches = input.matches(regexPattern)
        return matches.toString()

//        if(formula.matches(regexPattern)){
//            return "math"
//        }else{
//            return "not math"
//        }
    }
}