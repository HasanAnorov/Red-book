package com.example.redbook.ui.animal

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.redbook.R
import com.example.redbook.data.RedBookDatabase
import com.example.redbook.data.dao.AnimalDao
import kotlinx.android.synthetic.main.fragment_animal.*

class AnimalFrament:Fragment(R.layout.fragment_animal) {

    private val   myAdapter : AnimalListAdapter = AnimalListAdapter()
    private lateinit var  dao:AnimalDao
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter=myAdapter
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        dao=RedBookDatabase.getInstance(requireContext()).dao()

    setData()
    }

    fun setData(){
    myAdapter.models=dao.getAllAnimals()
    }

}