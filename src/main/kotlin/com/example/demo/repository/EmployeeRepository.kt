package com.example.demo.repository

import com.example.demo.model.Employee
import com.example.demo.model.Sumsal
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.Date
import java.util.Optional

@Repository
interface EmployeeRepository : JpaRepository<Employee, String> {
    fun findByEmpNoEquals(@Param("empNo") empNo: String): Employee
    fun findByEmpNo(@Param("empNo") empNo: String): Employee
    fun findByEname(@Param("ename") ename: String): List<Employee>
    fun findByJob(@Param("job") job: String): List<Employee>
    fun findByMgr(@Param("mgr") mgr: String): List<Employee>
    fun findByHiredate(@Param("hiredate") hiredate: LocalDate): List<Employee>
    fun findBySal(@Param("sal") sal: Double): List<Employee>
    fun findByCommissionPct(@Param("commissionPct") commissionPct: Double): List<Employee>
    fun findByDeptno(@Param("deptno") deptno: Int): List<Employee>

    @Query(
        value = "SELECT SUM(e.Sal) sumSal, COUNT(e.Mgr) countEmp FROM Emp e WHERE e.Mgr = :mgr GROUP BY e.Mgr",
        nativeQuery = true
    )
    fun findSumSalAndCountEmployee(@Param("mgr") mgr: String): Optional<Sumsal>
}