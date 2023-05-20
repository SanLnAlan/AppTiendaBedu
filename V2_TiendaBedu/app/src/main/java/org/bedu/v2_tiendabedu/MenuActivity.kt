package org.bedu.v2_tiendabedu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.inputmethod.InputBinding
import android.widget.Button
import androidx.fragment.app.Fragment
import org.bedu.v2_tiendabedu.databinding.ActivityMenuBinding


class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    val catalogoFragment = CatalogoFragment()
    val inventarioFragment = InventarioFragment()
    val carritoFragment = CarritoFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setCurrentFragment(catalogoFragment)
        createFragments()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater =  menuInflater
        inflater.inflate(R.menu.user_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun createFragments() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.nav_catalgo -> {
                    setCurrentFragment(catalogoFragment)
                    it.actionView?.clearFocus()
                    true
                }
                R.id.nav_inventario -> {
                    setCurrentFragment(inventarioFragment)
                    it.actionView?.clearFocus()
                    true
                }
                R.id.nav_carrito -> {
                    setCurrentFragment(carritoFragment)
                    it.actionView?.clearFocus()
                    true
                }
                else -> false
            }
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.containerView, fragment)
            commit()
        }
    }


}