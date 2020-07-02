package com.example.redbook.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.redbook.R
import com.example.redbook.data.RedBookDatabase
import com.example.redbook.data.dao.AnimalDao
import com.example.redbook.data.model.Animal
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(),DetailView {
    companion object{
        const val ANIMAL_ID="animalId"
    }

    private var animalId:Int=0
    private lateinit var currentAnimal: Animal
    private lateinit var dao: AnimalDao
    private  var menuItem :MenuItem? =null
    private lateinit var presenter:DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Details")


        dao=RedBookDatabase.getInstance(this).dao()
        presenter= DetailPresenter(dao,this)
        animalId=intent.getIntExtra(ANIMAL_ID,0)
        presenter.getAnimalById(animalId)

    }

    override fun setDetailInfo(animal: Animal) {
        currentAnimal=animal
        tvStatusContent.text=animal.status
        tvHabitatContent.text=animal.habitat
        tvPropagationContent.text=animal.propagation
        tvBreedingContent.text=animal.breeding
        tvLimitingFactorsContent.text=animal.limitingFactors
        tvLifestyleContent.text=animal.lifestyle
        tvQuantityContent.text=animal.quantity
        tvSecurityContent.text=animal.security

        Glide
            .with(this)
            .load(resources.getIdentifier("picture$animalId","drawable",packageName))
            .into(ivDetail)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->finish()

            R.id.item_bookmark ->setFavorite()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_detail,menu)
        menuItem=menu?.findItem(R.id.item_bookmark)
        setFavoriteIcon()
        return true
    }
  private  fun setFavorite(){
      if(currentAnimal.isFavorite==null){
          currentAnimal.isFavorite=1

      }

      else currentAnimal.isFavorite=1-currentAnimal.isFavorite!!
            currentAnimal.isFavorite=currentAnimal.isFavorite
            setFavoriteIcon()
        presenter.updateAnimal(currentAnimal)
    }

    private fun setFavoriteIcon(){
        if(currentAnimal.isFavorite==1 ){
            menuItem?.setIcon(R.drawable.ic_baseline_bookmark_24)
        }
        else {
            menuItem?.setIcon(R.drawable.ic_baseline_bookmark_border_24)
        }
    }
}