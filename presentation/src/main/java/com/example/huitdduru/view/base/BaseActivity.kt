package com.example.huitdduru.view.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.huitdduru.util.ToastType
import es.dmoral.toasty.Toasty

abstract class BaseActivity<T: ViewDataBinding>(@LayoutRes private val layoutResId: Int) : AppCompatActivity() {
    lateinit var binding : T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
        binding.lifecycleOwner = this
    }

    @SuppressLint("CheckResult")
    fun showToast(msg: String, type: ToastType){
        when(type){
            ToastType.ERROR -> Toasty.error(this, msg, Toast.LENGTH_SHORT, true).show()
            ToastType.SUCCESS -> Toasty.success(this, msg, Toast.LENGTH_SHORT, true).show()
            ToastType.INFO -> Toasty.info(this, msg, Toast.LENGTH_SHORT, true).show()
            ToastType.WARNING -> Toasty.warning(this, msg, Toast.LENGTH_SHORT, true).show()
        }
    }
}