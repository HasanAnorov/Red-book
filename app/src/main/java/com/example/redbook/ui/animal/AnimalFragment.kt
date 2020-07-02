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

class AnimalFragment:Fragment(R.layout.fragment_animal) {

    private val   myAdapter : AnimalListAdapter = AnimalListAdapter()
    private lateinit var  dao:AnimalDao
    private lateinit var presenter:AnimalPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            myAdapter.setOnItemClickListener {id->
                val mIntent= Intent(requireActivity(),DetailActivity::class.java)
                mIntent.putExtra(DetailActivity.ANIMAL_ID,id)
                startActivity(mIntent)
            }

            recyclerView.adapter=myAdapter
            recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            val type =arguments?.getInt(MainActivity.TYPE_ID)?:1
            dao=RedBookDatabase.getInstance(requireContext()).dao()
            presenter= AnimalPresenter(dao )
            presenter.setFunctionBody {
            myAdapter.models=it
        }
            presenter.getAllAnimals(type)


            etSearch.addTextChangedListener {
        val result :List<Animal > = dao.searchAnimalName(type,"${it.toString()}%")
            myAdapter.models=result
        }
    }



}