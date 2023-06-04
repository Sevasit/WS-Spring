package com.example.demo.repository

import com.example.demo.model.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.Date

@Repository
interface EmployeeRepository : JpaRepository<Employee, String> {
    fun findByEmpNoEquals(@Param("empNo") empNo : String) : Employee
    fun findByEmpNo(@Param("empNo") empNo : String) : Employee
    fun findByEname(@Param("ename") ename : String) : List<Employee>
    fun findByJob(@Param("job") job : String) : List<Employee>
    fun findByMgr(@Param("mgr") mgr : String) : List<Employee>
    fun findByHiredate(@Param("hiredate") hiredate : Date) : List<Employee>
    fun findBySal(@Param("sal") sal : Double) : List<Employee>
    fun findByCommissionPct(@Param("commissionPct") commissionPct : Double) : List<Employee>
    fun findByDeptno(@Param("deptno") deptno : Int) : List<Employee>
}