package com.capgemini.universityapp.viewModels

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.capgemini.universityapp.model.RepositoryVM
import com.capgemini.universityapp.model.Student
import com.capgemini.universityapp.model.University
import kotlinx.coroutines.launch

class UniViewModel(app : Application) : AndroidViewModel(app) {

    val univ = University("VIT Vellore", "Vellore, Tamil Nadu","contact@vitv.com")
    private val repo = RepositoryVM(app)

    //var studentCount = 200
    //var studentCount = MutableLiveData<Int>()
    var studentCount = MutableLiveData<Int>()

    fun updateCount(){
        //get count from db and send it to student count
        /*var list : List<Student>
        var c = 0*/
        viewModelScope.launch {
            /*list = repo.allStudents()
            studentCount = list.size
            Toast.makeText(context, "$c", Toast.LENGTH_LONG).show()*/
            val list = repo.allStudents()
            studentCount.postValue(list.size)
        }
    }

}