package com.example.redbook.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.redbook.data.model.Animal

@Dao

interface AnimalDao {
@Query("SELECT*FROM book WHERE type =:type")
fun getAllAnimals(type:Int):List<Animal>

    @Query("SELECT*FROM book WHERE id=:id")
    fun getAnimalById(id:Int):Animal

    @Update
    fun updateAnimal(animal:Animal)

    @Query("SELECT*FROM book where type=:type and nameEng like:word")
fun searchAnimalName(type:Int,word:String):List<Animal>

    @Query("SELECT*FROM book where isFavorite=1")
    fun getFavorites():List<Animal>
}