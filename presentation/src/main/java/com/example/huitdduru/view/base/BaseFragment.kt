package com.example.huitdduru.view.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.huitdduru.util.ToastType
import es.dmoral.toasty.Toasty

abstract class BaseFragment<T: ViewDataBinding>(@LayoutRes private val layoutResId: Int) : Fragment(){
    private var _binding: T? = null
    val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("CheckResult")
    fun showToast(msg: String, type: ToastType){
        when(type){
            ToastType.ERROR -> Toasty.error(requireContext(), msg, Toast.LENGTH_SHORT, true).show()
            ToastType.SUCCESS -> Toasty.success(requireContext(), msg, Toast.LENGTH_SHORT, true).show()
            ToastType.INFO -> Toasty.info(requireContext(), msg, Toast.LENGTH_SHORT, true).show()
            ToastType.WARNING -> Toasty.warning(requireContext(), msg, Toast.LENGTH_SHORT, true).show()
        }
    }
}