package com.example.bai1lt

// Phần 5: Data Class đóng gói dữ liệu sinh viên
data class Student(
    val id: String,
    val name: String,
    val className: String,
    val email: String,
    val gpa: Double = 0.0
)