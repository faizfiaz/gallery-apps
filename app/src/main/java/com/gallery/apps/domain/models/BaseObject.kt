package com.gallery.apps.domain.models

import java.util.*

open class BaseObject {
    var id: String? = null
    var createdAt: Date? = null
    var updatedAt: Date? = null
}