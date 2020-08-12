package com.gallery.apps.domain.usecases.user

import com.gallery.apps.domain.entities.response.RegistrationResponse
import com.gallery.apps.domain.exceptions.MapperException
import com.gallery.apps.domain.models.User
import io.reactivex.Single

abstract class IUserUsecases {
    @get:Throws(MapperException::class)
    abstract val user: Single<User?>?

    @Throws(MapperException::class)
    abstract fun login(username: String?, password: String?): Single<Boolean?>

    @Throws(MapperException::class)
    abstract fun registration(email: String?, username: String?, password: String?): Single<RegistrationResponse?>?
}