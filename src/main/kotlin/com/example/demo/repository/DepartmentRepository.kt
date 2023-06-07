package com.example.demo.repository

import com.example.demo.model.Department
import org.springframework.data.jpa.repository.JpaRepository

interface DepartmentRepository : JpaRepository<Department, Int> {
}