package com.solvathon.ui.login

import com.solvathon.core.Session
import com.solvathon.domain.pojo.ApiRequest
import com.solvathon.domain.pojo.User
import com.solvathon.domain.repo.UserRepository
import com.solvathon.ui.base.APILiveData
import com.solvathon.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: UserRepository) : BaseViewModel() {

    val logInLiveData = APILiveData<User>()

    fun logInWs(apiRequest: ApiRequest, session: Session) {
        repository.logIn(apiRequest, session).subscribe(withLiveData(logInLiveData))
    }
}