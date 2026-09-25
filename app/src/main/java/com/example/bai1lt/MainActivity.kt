package com.example.bai1lt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bai1lt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Phần 6: Quản lý biến với lateinit var
    private lateinit var binding: ActivityMainBinding

    // Dữ liệu ban đầu
    private var currentStudent = Student(
        id = "2415141122112",
        name = "Nguyen Vu Hoang Ngan",
        className = "24SK1",
        email = "anv@ute.udn.vn",
        gpa = 3.8
    )

    // Phần 7: Activity Lifecycle - Khởi tạo UI tại onCreate()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Phần 7: Khôi phục trạng thái khi xoay màn hình
        if (savedInstanceState != null) {
            val savedGpa = savedInstanceState.getDouble("KEY_GPA", 3.8)
            currentStudent = currentStudent.copy(gpa = savedGpa)
        }

        // Phần 5: Gán dữ liệu lên Views
        bindStudentData(currentStudent)

        // Phần 4: Xử lý sự kiện với Lambda
        binding.btnUpdateGpa.setOnClickListener {
            val inputStr = binding.edtNewGpa.text.toString().trim()
            val newGpa = inputStr.toDoubleOrNull()

            // Validate dữ liệu
            if (newGpa == null || newGpa !in 0.0..4.0) {
                binding.edtNewGpa.error = "Vui lòng nhập GPA hợp lệ (0.0 - 4.0)"
                toast("Điểm GPA không hợp lệ!")
                return@setOnClickListener
            }

            // Phần 5: Tạo bản sao mới với copy()
            currentStudent = currentStudent.copy(gpa = newGpa)

            bindStudentData(currentStudent)
            toast("Cập nhật điểm thành công!")
        }
    }

    // Phần 7: Lưu trạng thái dữ liệu trước khi Activity bị hủy (xoay màn hình)
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putDouble("KEY_GPA", currentStudent.gpa)
    }

    // Phần 1 & 5: Dùng scope function 'with' để gán dữ liệu sạch vẽ
    private fun bindStudentData(student: Student) {
        with(binding) {

            imgAvatar.setImageResource(R.drawable.baby)

            tvName.text = student.name
            tvStudentId.text = "MSSV: ${student.id} | Lớp: ${student.className}"
            tvGpaBadge.text = "${student.gpa} GPA (${student.gpa.toAcademicRanking()})"
            edtNewGpa.setText(student.gpa.toString())
        }
    }
}