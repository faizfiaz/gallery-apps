package com.gallery.apps.data.remote

import com.gallery.apps.domain.entities.requests.LoginRequest
import com.gallery.apps.domain.entities.requests.RegistrationRequest
import com.gallery.apps.domain.entities.response.LoginResponse
import com.gallery.apps.domain.entities.response.RegistrationResponse
import io.reactivex.Single

class UserRepository private constructor() : BaseRepository<LoginResponse?>() {

    fun login(request: LoginRequest?): Single<LoginResponse?>? {
        return remoteAPI.login(request)
    }

    fun registration(request: RegistrationRequest?): Single<RegistrationResponse?>? {
        return remoteAPI.registration(request)
    }

    companion object {
        @kotlin.jvm.JvmStatic
        var instance: UserRepository? = null
            get() {
                if (field == null) {
                    field = UserRepository()
                }
                return field
            }
            private set
    }

    override fun get(): Single<List<LoginResponse?>?>? {
        return null
    }

    override fun getById(id: Int): Single<List<LoginResponse?>?>? {
        return null
    }

}