package com.example.redbook.ui

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView

import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.redbook.R
import com.example.redbook.ui.animal.AnimalFragment
import com.example.redbook.ui.favorite.FavoriteFragment

class MainActivity : AppCompatActivity() {

    companion object{
        const val TYPE_ID="typeId"
        const val INVERTEBRATES=1
        const val FISHES=2
        const val REPTILES=3
        const val BIRDS=4
        const val MAMMALS=5
        const val FAVORITES=6

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toogle =ActionBarDrawerToggle(this,drawerLayout,toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()
        val fragment =AnimalFragment()
        val bundle=Bundle()
        bundle.putInt(TYPE_ID, INVERTEBRATES)
        fragment.arguments=bundle
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,fragment).commit()
        navView.setNavigationItemSelectedListener {
            var mFragment=Fragment()

            val mBundle=Bundle()
            when(it.itemId)
            {
                R.id.nav_invertebrates ->{
                     mFragment=AnimalFragment()
                    mBundle.putInt(TYPE_ID, INVERTEBRATES)
                    mFragment.arguments=mBundle

                }
                R.id.nav_fishes ->{
                     mFragment=AnimalFragment()
                    mBundle.putInt(TYPE_ID, FISHES)
                    mFragment.arguments=mBundle

                }
                R.id.nav_reptiles ->{
                     mFragment=AnimalFragment()
                    mBundle.putInt(TYPE_ID, REPTILES)
                    mFragment.arguments=mBundle

                }
                R.id.nav_birds ->{
                     mFragment=AnimalFragment()
                    mBundle.putInt(TYPE_ID, BIRDS)
                    mFragment.arguments=mBundle

                }
                R.id.nav_mammals ->{
                     mFragment=AnimalFragment()
                    mFragment=AnimalFragment()
                    mBundle.putInt(TYPE_ID, MAMMALS)
                    mFragment.arguments=mBundle

                }
                R.id.nav_favorite ->{
                    mFragment=FavoriteFragment()
                }
                else -> return@setNavigationItemSelectedListener false

            }
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,mFragment).commit()
            drawerLayout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }


}
