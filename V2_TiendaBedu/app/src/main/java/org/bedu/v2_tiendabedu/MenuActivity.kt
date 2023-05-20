package org.bedu.v2_tiendabedu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputBinding
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.bedu.v2_tiendabedu.databinding.ActivityMenuBinding
import org.bedu.v2_tiendabedu.models.inventario.Inventario
import org.bedu.v2_tiendabedu.models.inventario.listaDeInventario
import kotlin.math.log


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

        Inventario.agregarProductoInventario(
            "Zapato Blanco", "Zapato casual",
            "calzado", "B-22", 350f, 20, 22.5f
        )
        Inventario.agregarProductoInventario(
            "Tenis Blanco", "Zapato casual",
            "calzado", "B-23", 550f, 10, 23f
        )
        Inventario.agregarProductoInventario(
            "Refrigerador Mabe", "Refrigerador 10 pies cubicos",
            "Hogar", "Mabe-12", 100000f, 10, numeroSerie = "MB-'1112"
        )

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater =  menuInflater
        inflater.inflate(R.menu.user_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.userClose -> {
                finish()
                true
            }
            R.id.userDetails -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
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