package com.example.redbook.ui.animal

import com.example.redbook.data.dao.AnimalDao

class AnimalPresenter(private val dao:AnimalDao, private val view:AnimalView) {

    fun getAllAnimals(type:Int){
        view.setData(dao.getAllAnimals(type))
    }
}