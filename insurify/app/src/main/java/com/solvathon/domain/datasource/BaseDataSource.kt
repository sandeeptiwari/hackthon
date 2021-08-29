package com.solvathon.domain.datasource

import com.solvathon.domain.pojo.DataWrapper
import com.solvathon.domain.pojo.ResponseBody
import com.visbiliti.exception.NoDataException
import com.visbiliti.exception.OtpVerificationException
import com.visbiliti.exception.ServerException
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

open class BaseDataSource {

    fun <T> execute(observable: Single<ResponseBody<T>>): Single<DataWrapper<T>> {
        return observable
            /*.subscribeOn(Schedulers.from(appExecutors.networkIO()))
            .observeOn(Schedulers.from(appExecutors.mainThread()))*/
            .subscribeOn(Schedulers.io())
            .map { t -> DataWrapper(t, null) }
            .onErrorReturn { t -> DataWrapper(null, t) }
            .map {
                if (it.responseBody != null) {
                    when (it.responseBody.responseCode) {
                        0, 3 -> return@map DataWrapper(
                            it.responseBody,
                            ServerException(it.responseBody.message, it.responseBody.responseCode)
                        )
                        2 -> return@map DataWrapper(
                            it.responseBody,
                            NoDataException(it.responseBody.message, it.responseBody.responseCode)
                        )
                        4 -> return@map DataWrapper(
                            it.responseBody,
                            OtpVerificationException(
                                it.responseBody.message,
                                it.responseBody.responseCode
                            )
                        )
                    }
                }
                return@map it
            }
    }

}