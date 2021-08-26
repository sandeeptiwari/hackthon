package com.solvathon.ui.manager

import com.solvathon.ui.base.BaseActivity

interface Navigator {
    fun loadActivity(aClass: Class<out BaseActivity>): ActivityBuilder
}