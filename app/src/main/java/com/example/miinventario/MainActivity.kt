package com.example.miinventario

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.miinventario.databinding.ActivityMainBinding
import com.example.miinventario.models.Adaptador
import com.example.miinventario.models.Fila
import com.example.miinventario.models.Inventario
import com.example.miinventario.models.ShowProducts
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var adapter = Adaptador(ShowProducts.getProducts())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_main)


        binding.floatingActionButton.setOnClickListener{
            val scan=IntentIntegrator(this)
            scan.initiateScan()
        }



        val home=Home()

        val fragmentManager=supportFragmentManager

        val fragmentTransaction=fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.rlayout,home)


        val navController = this.findNavController(R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)


    }



    override fun onActivityResult(p0: Int,p1: Int,p2: Intent?) {
        if(p1==Activity.RESULT_OK   )  {
            val res = IntentIntegrator.parseActivityResult(p0,p1,p2)
            if(     res!=null){


                if(res.contents==null){
                    Toast.makeText(this,"Ha ocurrido un error",Toast.LENGTH_SHORT).show()
                }

                else{


                    var adding    = false
                    for(filaI: Fila in Inventario.getProducts()){
                        val code = filaI.producto.codigo
                        if(code==res.contents){
                            adding=true
                            ShowProducts.myProductos.add(filaI)
                           // adapter.add(filaI)
                        }
                    }
                    if(adding){
                        Toast.makeText(this,"Se ha escaneado correctamente",Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(this,"Ingrese el codigo antes de escanear",Toast.LENGTH_SHORT).show()
                    }
                }
            } else{
                super.onActivityResult(p0,p1,p2)
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.fragment)
        return navController.navigateUp()
    }
}
