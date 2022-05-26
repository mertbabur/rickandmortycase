package com.example.rickandmortycase.ui.base


import android.os.Bundle
import android.view.*
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.example.rickandmortycase.R
import java.lang.reflect.ParameterizedType

typealias Inflate<T> = (inflater: LayoutInflater) -> T

abstract class BaseFragment<VB: ViewBinding, VM: ViewModel>(private val bindingInflater: Inflate<VB>) : Fragment() {

    private lateinit var _binding: VB
    val binding: VB get() = _binding

    private val type = (javaClass.genericSuperclass as ParameterizedType)
    private val classVM = type.actualTypeArguments[1] as Class<VM>
    private lateinit var _viewModel: VM
    val viewModel: VM get() = _viewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        _viewModel = createViewModelLazy(classVM.kotlin, { viewModelStore }).value
        changeStatusBarColor(R.color.bunker)
        return binding.root
    }

    private fun changeStatusBarColor(color:Int){
        val window: Window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = resources.getColor(color)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
    }

}