package com.example.redbook.ui.animal

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.redbook.R
import com.example.redbook.data.RedBookDatabase
import com.example.redbook.data.dao.AnimalDao
import com.example.redbook.data.model.Animal
import com.example.redbook.ui.MainActivity
import com.example.redbook.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_animal.*

class AnimalFragment:Fragment(R.layout.fragment_animal),AnimalItemClickListener {

    private val   myAdapter : AnimalListAdapter = AnimalListAdapter(this)

    private lateinit var  dao:AnimalDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter=myAdapter
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        val type =arguments?.getInt(MainActivity.TYPE_ID)?:1
        dao=RedBookDatabase.getInstance(requireContext()).dao()

//        etSearch.addTextChangedListener(object:TextWatcher{
//            override fun afterTextChanged(s: Editable?) {
//                TODO("Not yet implemented")
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                TODO("Not yet implemented")
//            }
//
//        })

        etSearch.addTextChangedListener {
    val result :List<Animal > = dao.searchAnimalName(type,"${it.toString()}%")
     myAdapter.models=result
        }

    setData(type)
    }

    fun setData(type:Int){
    myAdapter.models=dao.getAllAnimals(type)
    }

    override fun onAnimalItemClick(id:Int) {
       val mIntent= Intent(requireActivity(),DetailActivity::class.java)
        mIntent.putExtra(DetailActivity.ANIMAL_ID,id)
        startActivity(mIntent)
    }

}