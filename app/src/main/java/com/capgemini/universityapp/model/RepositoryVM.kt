package com.capgemini.universityapp.model

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RepositoryVM(context: Context) {
    private val studentDao = StudentDatabase.getInstance(context).studentDao()

    suspend fun addStudent(std:Student) = studentDao.insert(std)

    suspend fun updateStudent(std: Student) = studentDao.update(std)

    suspend fun deleteStudent(std: Student) = studentDao.delete(std)

    suspend fun deleteAllStudents() = studentDao.deleteAll()

    suspend fun allStudents() : List<Student>{
        var stdList: List<Student>? = null
        //async because we are returning
        val result = CoroutineScope(Dispatchers.Default).async {
            studentDao.getStudents()
        }
        stdList = result.await()

        return stdList
    }
}