package com.solvathon.ui.base

import android.util.Log
import androidx.lifecycle.ViewModel
import com.solvathon.domain.pojo.DataWrapper
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        Log.v("this","disposable called");
        super.onCleared()
    }

    protected fun <T> withLiveData(liveData: APILiveData<T>): SingleObserver<DataWrapper<T>> {
        return object : SingleObserver<DataWrapper<T>> {
            override fun onError(e: Throwable) {}
            override fun onSuccess(t: DataWrapper<T>) = liveData.postValue(t)
            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }
        }
    }
}