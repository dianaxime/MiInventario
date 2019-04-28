package com.example.miinventario


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.miinventario.databinding.FragmentAddBinding
import com.example.miinventario.databinding.FragmentHomeBinding
import com.example.miinventario.models.Fila
import com.example.miinventario.models.Inventario
import com.example.miinventario.models.Producto
import kotlinx.android.synthetic.main.fragment_add.*


class Add : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_add, container, false)
        val binding = DataBindingUtil.inflate<FragmentAddBinding>(inflater, R.layout.fragment_add, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.save, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val producto=Producto(producttext.text.toString(), codetext.text.toString())
        Inventario.addProduct(Fila(producto))
        return NavigationUI.onNavDestinationSelected(item!!,
            view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }
}
