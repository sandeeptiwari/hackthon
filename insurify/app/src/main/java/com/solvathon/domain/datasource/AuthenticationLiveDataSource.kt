package com.solvathon.domain.datasource

import com.solvathon.core.Session
import com.solvathon.domain.pojo.ApiRequest
import com.solvathon.domain.pojo.DataWrapper
import com.solvathon.domain.pojo.User
import com.solvathon.domain.repo.UserRepository
import com.solvathon.domain.service.AuthenticationService
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationLiveDataSource @Inject
constructor(private val authenticationService: AuthenticationService) :
    BaseDataSource(), UserRepository {

    override fun logIn(apiRequest: ApiRequest, session: Session): Single<DataWrapper<User>> {
        return execute(authenticationService.logIn(apiRequest)).doOnSuccess {
            if (it.responseBody?.responseCode == 1 || it.responseBody?.responseCode == 4 || it.responseBody?.responseCode == 9 || it.responseBody?.responseCode == 10 /*|| it.responseBody?.responseCode == 11*/) {
                session.user = it.responseBody.data
                session.userId = it.responseBody.data!!.id.toString()
                session.userSession = it.responseBody.data!!.username.toString()
            }
        }
    }
}