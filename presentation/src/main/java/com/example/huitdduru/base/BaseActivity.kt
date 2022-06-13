package com.example.huitdduru.base

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.awesomedialog.AwesomeDialog
import com.example.awesomedialog.body
import com.example.awesomedialog.icon
import com.example.awesomedialog.title
import com.example.huitdduru.R
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

    fun showDialog(title: String, body: String){
        AwesomeDialog.build(this)
            .title(title)
            .body(body)
            .icon(R.drawable.ic_warning)
            .show()
    }
}