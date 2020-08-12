package com.gallery.apps.data.remote

import com.gallery.apps.domain.entities.requests.LoginRequest
import com.gallery.apps.domain.entities.requests.RegistrationRequest
import com.gallery.apps.domain.entities.response.LoginResponse
import com.gallery.apps.domain.entities.response.RegistrationResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Retrofit API
 */
interface RemoteAPI {
    /*******************************USER */
    @POST("/api/login")
    fun login(@Body loginRequest: LoginRequest?): Single<LoginResponse?>?

    @POST("/auth/local/register")
    fun registration(@Body registrationRequest: RegistrationRequest?): Single<RegistrationResponse?>?
}