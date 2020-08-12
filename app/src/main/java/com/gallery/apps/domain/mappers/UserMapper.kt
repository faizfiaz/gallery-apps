package com.gallery.apps.domain.mappers

import com.gallery.apps.domain.entities.UserEntity
import com.gallery.apps.domain.exceptions.MapperException
import com.gallery.apps.domain.models.User

class UserMapper : BaseMapper<UserEntity?, User?>() {
    override fun createObject(): User? {
        return null
    }

    override fun createEntity(): UserEntity? {
        return null
    }

    @Throws(MapperException::class)
    override fun defineObject(`object`: User?): User? {
        return null
    }

    @Throws(MapperException::class)
    override fun defineEntity(entity: UserEntity?): UserEntity? {
        return null
    }
}