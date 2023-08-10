package com.example.currencyconversionapp.ui.base


import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


abstract  class BaseActivity <DB : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {
    var binding: DB? = null
    var viewModel: VM? = null

    abstract fun getBindingVariable(): Int

    /**
     * @return layout resource id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract fun getActViewModel(): Class<VM>

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
    }


    protected open fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        viewModel = ViewModelProvider(this, defaultViewModelProviderFactory)[getActViewModel()]
        binding?.setVariable(getBindingVariable(), viewModel)
        binding?.lifecycleOwner = this
    }
}