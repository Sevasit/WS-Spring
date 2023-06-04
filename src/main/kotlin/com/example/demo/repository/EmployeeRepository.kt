package com.example.demo.repository

import com.example.demo.model.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : JpaRepository<Employee, String> {
    fun findByEmpNoEquals(@Param("empNo") empNo : String) : Employee
    fun findByEmpNo(@Param("empNo") empNo : String) : Employee
}