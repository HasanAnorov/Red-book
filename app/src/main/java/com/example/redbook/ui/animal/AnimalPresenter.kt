package com.example.redbook.ui.animal

import com.example.redbook.data.dao.AnimalDao
import com.example.redbook.data.model.Animal

class AnimalPresenter(private val dao:AnimalDao) {

    private var setData:(models:List<Animal>)->Unit={
        println("setData does not realizate yet")
    }

    fun setFunctionBody(qalegan:(a:List<Animal>)->Unit){
        this.setData=qalegan
    }

    fun getAllAnimals(type:Int){
        setData.invoke(dao.getAllAnimals(type))
    }
}