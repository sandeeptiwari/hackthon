package com.solvathon.ui.manager

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.util.Pair
import com.solvathon.ui.base.BaseActivity
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class ActivityStarter @Inject
internal constructor(@ActivityContext private val context: Context) {
    private var intent: Intent? = null
    private var activity: Class<out Activity>? = null
    private var shouldAnimate = true
    val baseActivity = context as BaseActivity
    fun make(activityClass: Class<out BaseActivity>): ActivityBuilder {
        activity = activityClass
        intent = Intent(context, activityClass)
        return Builder()
    }

    private inner class Builder : ActivityBuilder {
        private var bundle: Bundle? = null
        private var activityOptionsBundle: Bundle? = null
        private var isToFinishCurrent: Boolean = false
        private var requestCode: Int = 0

        override fun start() {
            if (bundle != null)
                intent!!.putExtras(bundle!!)

            if (!shouldAnimate)
                intent!!.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

            if (requestCode == 0) {

                if (activityOptionsBundle == null)
                    context.startActivity(intent)
                else
                    context.startActivity(intent, activityOptionsBundle)
            } else {
                baseActivity.startActivityForResult(intent, requestCode)
            }

            if (isToFinishCurrent)
                baseActivity.finish()
        }

        override fun addBundle(bundle: Bundle): ActivityBuilder {
            if (this.bundle != null)
                this.bundle!!.putAll(bundle)
            else
                this.bundle = bundle
            return this
        }

        override fun addSharedElements(pairs: List<Pair<View, String>>): ActivityBuilder {
            TODO("Not yet implemented")
        }

        override fun byFinishingCurrent(): ActivityBuilder {
            isToFinishCurrent = true
            return this
        }

        override fun byFinishingAll(): ActivityBuilder {
            intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            return this
        }

        override fun forResult(requestCode: Int): ActivityBuilder {
            this.requestCode = requestCode
            return this
        }

        override fun shouldAnimate(isAnimate: Boolean): ActivityBuilder {
            shouldAnimate = isAnimate
            return this
        }

    }
}