package com.solvathon.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.solvathon.core.AppPreferences
import com.solvathon.ui.manager.ActivityBuilder
import com.solvathon.ui.manager.ActivityStarter
import com.solvathon.ui.manager.Navigator
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), Navigator {

    @Inject
    lateinit var activityStarter: ActivityStarter

    @Inject
    lateinit var appPreference: AppPreferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(findContentView())
        bindViewWithViewBinding((findViewById<ViewGroup>(android.R.id.content)).getChildAt(0))
    }

    override fun loadActivity(aClass: Class<out BaseActivity>): ActivityBuilder {
        return activityStarter.make(aClass)
    }

    @LayoutRes
    abstract fun findContentView(): Int

    abstract fun bindViewWithViewBinding(view: View)
}