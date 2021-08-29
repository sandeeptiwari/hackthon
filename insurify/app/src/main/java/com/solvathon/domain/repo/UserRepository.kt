package com.solvathon.domain.repo

import com.solvathon.core.Session
import com.solvathon.domain.pojo.ApiRequest
import com.solvathon.domain.pojo.DataWrapper
import com.solvathon.domain.pojo.User
import io.reactivex.Single

interface UserRepository {

    fun logIn(apiRequest: ApiRequest, session: Session): Single<DataWrapper<User>>
}
