package com.capgemini.universityapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.capgemini.universityapp.model.Student

class MyAdapter(val data: List<Student>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {
//class MyAdapter(val data: List<Student>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    var selectPos = -1
    var selectedStd : Student? = null


    class ViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val firstTV : TextView
        val lastTV : TextView
        val marksTV : TextView
        val studentCheck : CheckBox
        val cardView : CardView
        init {
            firstTV = view.findViewById(R.id.firstTV)
            lastTV = view.findViewById(R.id.lastTV)
            marksTV = view.findViewById(R.id.marksTV)
            studentCheck = view.findViewById(R.id.studentCheck)
            cardView = view.findViewById(R.id.cardView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.student_layout_item_list,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {
        val student = data[position]

        holder.firstTV.text = "First Name: ${student.firstName}"
        holder.lastTV.text = "Last Name: ${student.lastName}"
        holder.marksTV.text = "Marks: ${student.marks.toString()}"

        holder.studentCheck.isChecked = (selectPos == position)
        holder.studentCheck.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                selectPos = holder.adapterPosition
                selectedStd = student
                notifyDataSetChanged()
            }
        }


    }

    override fun getItemCount() = data.size
}