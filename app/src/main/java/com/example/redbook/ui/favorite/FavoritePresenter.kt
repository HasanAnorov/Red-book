package com.example.redbook.ui.favorite

import com.example.redbook.data.dao.AnimalDao
import com.example.redbook.data.model.Animal

class FavoritePresenter(private val dao:AnimalDao,private val view:FavoriteView) {
    fun getFavorites(){
        view.setData(dao.getFavorites())
    }
}