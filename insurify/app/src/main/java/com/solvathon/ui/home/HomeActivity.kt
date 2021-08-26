package com.solvathon.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.solvathon.R
import com.solvathon.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun findContentView(): Int {
        return R.layout.activity_home
    }

    override fun bindViewWithViewBinding(view: View) {
    }
}