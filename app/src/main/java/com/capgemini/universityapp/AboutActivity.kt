package com.capgemini.universityapp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.capgemini.universityapp.databinding.ActivityAboutBinding
import com.capgemini.universityapp.model.University
import com.capgemini.universityapp.viewModels.UniViewModel

class AboutActivity : AppCompatActivity() {
    lateinit var model : UniViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_about)

        val vmProvider = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
        model = vmProvider.get(UniViewModel::class.java)

        val binding = DataBindingUtil.setContentView<ActivityAboutBinding>(this, R.layout.activity_about)

        //binding.vModel= model.univ

        binding.vModel = model
        binding.lifecycleOwner = this

        model.updateCount()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add("Update count")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //val count = (Math.random() *1000).toInt()
        model.updateCount()
        //Toast.makeText(this,"${count}",Toast.LENGTH_LONG).show()
        //model.studentCount = count

        return super.onOptionsItemSelected(item)
    }
}