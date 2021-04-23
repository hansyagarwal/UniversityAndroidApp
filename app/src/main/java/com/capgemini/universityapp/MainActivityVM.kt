package com.capgemini.universityapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capgemini.universityapp.model.RepositoryVM
import com.capgemini.universityapp.model.Student
import com.capgemini.universityapp.viewModels.StudentViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityVM : AppCompatActivity() {



    lateinit var model: StudentViewModel
    //lateinit var rView: RecyclerView
    var adapter: MyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vmProvider = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(application))

        model = vmProvider.get(StudentViewModel::class.java)

        //dont do this
        //model = StudentViewModel(application)

        //updateList()
        rView.layoutManager = LinearLayoutManager(this)
        /*CoroutineScope(Dispatchers.Default).launch {

            adapter = MyAdapter(model.getStudents())
            runOnUiThread(){
                Toast.makeText(this@MainActivityVM,"Coroutine test Toast",Toast.LENGTH_LONG).show()
                rView.adapter = adapter
                rView.adapter?.notifyDataSetChanged()
            }
        }*/
        model.studentList.observe(this, Observer {
            val stdList = it
            Log.d("MainActivity","Observer: $stdList")
            //setup adapter

            adapter = MyAdapter(model.studentList.value!!)
            rView.adapter = adapter
            /*if(adapter == null){

            } else {
                adapter?.notifyDataSetChanged()
            }*/
        })
    }

    override fun onResume() {
        super.onResume()
        model.getStudents()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add("Add student")
        menu?.add("Update")
        menu?.add("Delete")
        menu?.add("Delete All")
        menu?.add("About us")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.title){
            "Add student" -> {
                //repository.addStudent(Student("John", "Smith", 90))
                val i = Intent(this,AddUpdateActivity::class.java)
                startActivity(i)
            }
            "Update" -> {
                val i = Intent(this,AddUpdateActivity::class.java)
                i.putExtra("isUpdate", true)
                i.putExtra("id",adapter?.selectedStd?.id)
                i.putExtra("fname",adapter?.selectedStd?.firstName)
                i.putExtra("lname",adapter?.selectedStd?.lastName)
                i.putExtra("marks",adapter?.selectedStd?.marks)
                startActivity(i)
            }
            "Delete" -> {
                /*CoroutineScope(Dispatchers.Default).launch {
                    adapter = MyAdapter(repository.allStudents())
                    repository.allStudents()
                    runOnUiThread {
                        rView.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                }*/


            }
            "Delete All" -> {
                model.deleteAll()
            }
            "About us" -> {
                /*rView.adapter = adapter
                rView.adapter?.notifyDataSetChanged()*/
                //updateList()
                val i = Intent(this,AboutActivity::class.java)
                startActivity(i)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /*fun updateList(){
        *//*CoroutineScope(Dispatchers.Default).launch {
            studentList = repository.allStudents()
            //adapter.notifyDatasetChanged()
            Log.d("MainActivity","updateList: $studentList")
        }*//*
        val stdList = model.studentList

        if(adapter == null){
            adapter = MyAdapter(model.studentList)
            rView.adapter = adapter
        } else {
            adapter?.notifyDataSetChanged()
        }
    }*/
}