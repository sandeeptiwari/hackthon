package com.solvathon.ui.base

import androidx.lifecycle.MutableLiveData
import com.solvathon.domain.pojo.DataWrapper
import com.solvathon.domain.pojo.ResponseBody
import androidx.lifecycle.Observer

class APILiveData<T> : MutableLiveData<DataWrapper<T>>() {

    /**
     *  @param owner : Life Cycle Owner
     *  @param onChange : live data
     *  @param onError : Server and App error -> return true to handle error by base else return false to handle error by your self
     *
     */
    public fun observe(owner: BaseFragment, onChange: (ResponseBody<T>) -> Unit, onError: (Throwable, ResponseBody<T>?) -> Boolean = { _, _ -> true }) {
        super.observe(owner, Observer<DataWrapper<T>> {
            if (it?.throwable != null) {
                if (onError(it.throwable, it.responseBody)) owner.onError(it.throwable)
            } else if (it?.responseBody != null) {
                onChange(it.responseBody)
            }
        })
    }


    fun observe(owner: BaseFragment, onChange: (ResponseBody<T>) -> Unit, onError: (Throwable) -> Boolean = { true }) {
        super.observe(owner, Observer<DataWrapper<T>> {
            if (it?.throwable != null) {
                if (onError(it.throwable))
                    owner.onError(it.throwable)
            } else if (it?.responseBody != null) {
                onChange(it.responseBody)
            }
        })
    }
    /**
     *  @param owner : Life Cycle Owner
     *  @param onChange : live data
     *  @param onError : Server and App error -> return true to handle error by base else return false to handle error by your self
     *
     */
    public fun observeOwner(owner: BaseFragment, onChange: (ResponseBody<T>) -> Unit, onError: (Throwable, ResponseBody<T>?) -> Boolean = { _, _ -> true }) {
        super.observe(owner.getViewLifecycleOwner(), Observer<DataWrapper<T>> {
            if (it?.throwable != null) {
                if (onError(it.throwable, it.responseBody)) owner.onError(it.throwable)
            } else if (it?.responseBody != null) {
                onChange(it.responseBody)
            }
        })
    }

    /**
     *  @param owner : Life Cycle Owner
     *  @param onChange : live data
     *  @param onError : Server and App error -> return true to handle error by base else return false to handle error by your self
     *
     */
    public fun observe(owner: BaseActivity, onChange: (ResponseBody<T>) -> Unit, onError: (Throwable, ResponseBody<T>?) -> Unit = { _, _ -> }) {
        super.observe(owner, Observer {
            if (it?.throwable != null) {
                owner.toggleLoader(false)
                onError(it.throwable, it.responseBody)
            } else if (it?.responseBody != null) {
                onChange(it.responseBody)
            }
        })
    }
}