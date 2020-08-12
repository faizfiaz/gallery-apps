package com.gallery.apps.domain.usecases.base

import com.gallery.apps.data.remote.BaseRepository
import com.gallery.apps.domain.mappers.BaseMapper

abstract class BaseUsecase<M : BaseMapper<*, *>?, R : BaseRepository<*>?>(protected var mapper: M, protected var repository: R)