package com.sachin.sneaker_app.view.cart_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sachin.sneaker_app.adapter.cart_adapter.SneakersCartAdapter
import com.sachin.sneaker_app.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    lateinit var binding: FragmentCartBinding
    val viewModel: CartViewModel by viewModels()
    lateinit var sneakerAdapter: SneakersCartAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCartProducts().observe(viewLifecycleOwner) {
            viewModel.sneakerData.addAll(it)
            setAdapter()
        }

        binding.clearCart.setOnClickListener {
            viewModel.clearCart()
            viewModel.sneakerData.clear()
        }

    }

    fun setAdapter() {
        sneakerAdapter = SneakersCartAdapter(viewModel.sneakerData)
        binding.rvSneakers.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = sneakerAdapter

        }
    }


}