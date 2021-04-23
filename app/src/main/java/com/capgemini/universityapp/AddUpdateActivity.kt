package com.capgemini.universityapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.capgemini.universityapp.model.Repository
import com.capgemini.universityapp.model.RepositoryVM
import com.capgemini.universityapp.model.Student
import com.capgemini.universityapp.viewModels.StudentViewModel
import kotlinx.android.synthetic.main.activity_add_update.*

class AddUpdateActivity : AppCompatActivity() {

    //lateinit var repository: Repository
    lateinit var model : StudentViewModel
    var i: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_update)

        val vmProvider = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(application))
        model = vmProvider.get(StudentViewModel::class.java)

        //repository = Repository(this)
        val b = intent.extras
        val isChecked = b?.getBoolean("isUpdate")
        val fname  = b?.getString("fname")
        val lname = b?.getString("lname")
        val marks = b?.getInt("marks")
        i = b?.getInt("id")

        if(isChecked!=null && fname!=null && lname!=null && marks!=null){
            firstT.setText(fname)
            lastT.setText(lname)
        }
    }

    fun buttonClick(view: View) {
        val firstN = firstT.text.toString()
        val lastN = lastT.text.toString()
        val marks = marksT.text.toString()
        when(view.id){
            R.id.addB -> {
                model.addStudent(Student(firstN,lastN,marks.toInt()))
            }
            R.id.updateB -> {
                //repository.updateStudent(Student(firstN,lastN,marks.toInt()))
                model.updateStudent(Student(firstN,lastN,marks.toInt(),i!!))
            }
        }
    }
}