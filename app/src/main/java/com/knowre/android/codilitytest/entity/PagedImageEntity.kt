package com.knowre.android.codilitytest.entity

import com.knowre.android.codilitytest.base.PageNumber


internal data class PagedImageEntity(val pageNumber: PageNumber, val entities: List<ImageEntity>)