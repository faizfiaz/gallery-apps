package com.gallery.apps.domain.entities.response

import com.gallery.apps.domain.entities.BaseObjectEntity
import com.google.gson.annotations.SerializedName

data class LoginResponse(
        @SerializedName("jwt")
        val jwt: String? = null
): BaseObjectEntity()