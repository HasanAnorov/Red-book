package com.example.redbook.ui.detail

import com.example.redbook.data.dao.AnimalDao
import com.example.redbook.data.model.Animal

class DetailPresenter(private val dao:AnimalDao,private var view: DetailView) {
    fun getAnimalById(id:Int){
        view.setDetailInfo(dao.getAnimalById(id))
    }
    fun updateAnimal(animal:Animal){
        dao.updateAnimal(animal)
    }
}