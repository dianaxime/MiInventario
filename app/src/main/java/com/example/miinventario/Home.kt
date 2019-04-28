package com.example.miinventario


import android.databinding.DataBindingUtil
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.*
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.miinventario.databinding.FragmentHomeBinding
import com.example.miinventario.models.Adaptador
import com.example.miinventario.models.Inventario
import com.example.miinventario.models.ShowProducts
import com.example.miinventario.models.ShowProducts.Companion.myProductos
import com.example.miinventario.models.ShowProducts.Companion.showProducts


class Home : Fragment() {
    private lateinit var colorchanged: ColorDrawable
    val adapter = Adaptador(ShowProducts.myProductos)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false)
        //productsrecycler!!.layoutManager= LinearLayoutManager(this)
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false)

        val productsrecycler = binding.productsrecycler
        productsrecycler.adapter=adapter
        productsrecycler.layoutManager = LinearLayoutManager(activity)
        colorchanged=ColorDrawable(Color.parseColor("#f00000"))
        /* adapter = AdaptadorInv(InventariodeProductos.getProducts(), this) { contact ->
            run {
                productview.text=contact.producto.producto
                cantview.text=contact.cantidad.toString()
            }
        }
        productsrecycler.adapter=adapter*/
        val itemTouchHelperCallback=object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean { return false }


            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.eliminar(viewHolder)
            }



            override fun onChildDraw(
                c: Canvas,rView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                posX: Float,posY: Float,estado: Int,active: Boolean){
                val vView=viewHolder.itemView
                if (posX>0) {
                    colorchanged.setBounds(vView.left,vView.top,posX.toInt(),vView.bottom) }
                colorchanged.draw(c)
                c.save()
                if (posX>0)    c.clipRect(vView.left,vView.top,posX.toInt(),vView.bottom)
                c.restore()
                super.onChildDraw(c,rView,viewHolder,posX,posY,estado,active)

            }
        }
        val ith=ItemTouchHelper(itemTouchHelperCallback)
        ith.attachToRecyclerView(productsrecycler)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu,inflater)
        inflater.inflate(R.menu.menuelemnets,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        val nombre= item.itemId
        if (nombre==R.id.mostrar){ Snackbar.make(view!!,Inventario.showProducts(),Snackbar.LENGTH_SHORT).show()
            return super.onOptionsItemSelected(item)
        } else if(id==R.id.action_home2_self2) {
            adapter.eliminartodos()
            ShowProducts.limpiar()
            ShowProducts.myProductos.clear()
            return NavigationUI.onNavDestinationSelected(
                item, view!!.findNavController()
            )
                    || super.onOptionsItemSelected(item)
        } else {
            return NavigationUI.onNavDestinationSelected(
                item, view!!.findNavController()
            )
                    || super.onOptionsItemSelected(item)
        }
    }
}

