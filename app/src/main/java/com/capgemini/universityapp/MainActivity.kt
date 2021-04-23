package com.capgemini.universityapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.capgemini.universityapp.model.Repository
import com.capgemini.universityapp.model.RepositoryVM
import com.capgemini.universityapp.model.Student
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var repository: Repository
    var studentList: List<Student> = mutableListOf<Student>()
    //lateinit var rView: RecyclerView
    lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repository = Repository(this)

        updateList()
        Log.d("MainActivity","OnCreate List: $studentList")

        rView.layoutManager = LinearLayoutManager(this)
        CoroutineScope(Dispatchers.Default).launch {

            /*adapter = MyAdapter(repository.allStudents())*//*{it,b ->

            }*/
            runOnUiThread(){
                Toast.makeText(this@MainActivity,"Coroutine test Toast",Toast.LENGTH_LONG).show()
                rView.adapter = adapter
                rView.adapter?.notifyDataSetChanged()
            }
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add("Add")
        menu?.add("Update")
        menu?.add("Delete")
        menu?.add("Delete All")
        menu?.add("Refresh")
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
                startActivity(i)
            }
            "Delete" -> {
                /*CoroutineScope(Dispatchers.Default).launch {
                    adapter = MyAdapter(repository.allStudents())*//*{it,b ->
                        if(b){
                            repository.deleteStudent(it)
                        }
                    }*//*
                    repository.allStudents()
                    runOnUiThread {
                        rView.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                }*/

            }
            "Delete All" -> {
                repository.deleteAllStudents()
            }
            "Refresh" -> {
                rView.adapter = adapter
                rView.adapter?.notifyDataSetChanged()
                updateList()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun updateList(){
        CoroutineScope(Dispatchers.Default).launch {
            studentList = repository.allStudents()
            //adapter.notifyDatasetChanged()
            Log.d("MainActivity","updateList: $studentList")
        }
    }
}