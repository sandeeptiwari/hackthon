package com.solvathon.ui.claims

import android.os.Bundle
import android.view.View
import com.solvathon.R
import com.solvathon.ui.base.BaseActivity

class ClaimActivity :BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun findContentView(): Int {
        return R.layout.my_claims
    }

    override fun bindViewWithViewBinding(view: View) {
        TODO("Not yet implemented")
    }

}