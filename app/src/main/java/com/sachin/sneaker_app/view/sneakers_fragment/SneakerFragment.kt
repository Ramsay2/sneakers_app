package com.sachin.sneaker_app.view.sneakers_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sachin.sneaker_app.R
import com.sachin.sneaker_app.adapter.sneakerAdapter.SneakersAdapter
import com.sachin.sneaker_app.databinding.DialogSneakerDetailsBinding
import com.sachin.sneaker_app.databinding.FragmentSneakersBinding
import com.sachin.sneaker_app.model.SneakerResponse
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SneakerFragment : Fragment(), Navigator {
    val viewModel: SneakerViewModel by viewModels()
    lateinit var binding: FragmentSneakersBinding
    var _sneakerData = mutableListOf<SneakerResponse>()
    lateinit var sneakerAdapter: SneakersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSneakersBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        viewModel.navigator = this
        viewModel.addProductToDB()
        viewModel.getAllSneakers().observe(viewLifecycleOwner) {
            it?.let {
                _sneakerData.clear()
                _sneakerData.addAll(it)
            }
        }


    }

    override fun onResume() {
        super.onResume()
        setAdapter()
    }

    fun setAdapter() {
        val productList = mutableListOf<SneakerResponse>()
        sneakerAdapter = SneakersAdapter(viewModel._sneakerData).apply {
            callBack = viewModel
        }
        productList.clear()
        productList.addAll(viewModel._sneakerData)
        binding.rvSneakers.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = sneakerAdapter

        }
    }

    override fun openCartPage() {
        findNavController().navigate(R.id.action_sneakerFragment_to_cartFragment)
    }

    override fun showDetailsDialog() {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val binding = DataBindingUtil.inflate<DialogSneakerDetailsBinding>(
            layoutInflater,
            R.layout.dialog_sneaker_details,
            null,
            false
        )
        bottomSheetDialog.setContentView(binding.root)
        binding.viewModel = viewModel
        bottomSheetDialog.show()
    }


}