package com.solvathon.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.solvathon.R
import com.solvathon.core.AppPreferences
import com.solvathon.ui.manager.ActivityBuilder
import com.solvathon.ui.manager.ActivityStarter
import com.solvathon.ui.manager.Navigator
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), Navigator {

    @Inject
    lateinit var activityStarter: ActivityStarter

    @Inject
    lateinit var appPreference: AppPreferences

    internal var progressDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(findContentView())
        bindViewWithViewBinding((findViewById<ViewGroup>(android.R.id.content)).getChildAt(0))
        setUpDialog()
    }

    override fun loadActivity(aClass: Class<out BaseActivity>): ActivityBuilder {
        return activityStarter.make(aClass)
    }

    @LayoutRes
    abstract fun findContentView(): Int

    abstract fun bindViewWithViewBinding(view: View)

    fun showLoader(show: Boolean) {
        toggleLoader(show)
    }

    fun setUpDialog() {
        val dialogBuilder = AlertDialog.Builder(this, R.style.alertDialog)
        val dialogView = LayoutInflater.from(this).inflate(
            R.layout.progress_bar_layout,
            null,
            false
        )
        dialogBuilder.setView(dialogView)
        dialogBuilder.setCancelable(false)
        progressDialog = dialogBuilder.create()
    }

    protected fun showSnackBar(view: View, message: Int) {
        if (view != null) {

            val snackbar = Snackbar.make(
                view!!,
                message,
                Snackbar.LENGTH_SHORT
            )
            snackbar.setActionTextColor(this.resources.getColor(R.color.white))
            //  snackbar.setAction("Ok") { snackbar.dismiss() }
            val snackView = snackbar.view
            val textView =
                snackView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            textView.maxLines = 4
            textView.setTextColor(this.resources.getColor(R.color.white))
            snackView.setBackgroundResource(R.drawable.snackbar_gradirent)
            snackbar.show()
        }
    }

    fun toggleLoader(show: Boolean) {

        if (show) {
            if (progressDialog != null) {
                progressDialog!!.show()
            } else {
                setUpDialog()
                progressDialog!!.show()
            }
        } else {
            if (progressDialog != null && progressDialog!!.isShowing) {
                progressDialog!!.dismiss()
            }
        }
    }

    fun onError(throwable: Throwable) {
        showLoader(false)
        Log.e(javaClass.simpleName, "Error:::" + throwable.message)
        Log.e(javaClass.simpleName, "Error:::throwable" + throwable)
    }
}