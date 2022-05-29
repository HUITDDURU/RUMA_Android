package com.example.huitdduru.base

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.huitdduru.R
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when(layoutResId){
            R.layout.fragment_home -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    val window = requireActivity().window
                    WindowInsetsControllerCompat(window, view).isAppearanceLightStatusBars = false
                    window.statusBarColor = requireContext().getColor(R.color.primary)
                }
            }
            else -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    val window = requireActivity().window
                    WindowInsetsControllerCompat(window, view).isAppearanceLightStatusBars = true
                    window.statusBarColor = requireContext().getColor(R.color.white)
                }
            }
        }
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