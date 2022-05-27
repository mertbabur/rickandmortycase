package com.example.rickandmortycase.ui.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

typealias Inflate<T> = (inflater: LayoutInflater) -> T

abstract class BaseFragment<VB : ViewBinding>(private val bindingInflater: Inflate<VB>) :
    Fragment() {

    private lateinit var _binding: VB
    val binding: VB get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        return binding.root
    }
}