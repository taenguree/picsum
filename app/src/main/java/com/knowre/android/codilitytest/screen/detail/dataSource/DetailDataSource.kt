package com.knowre.android.codilitytest.screen.detail.dataSource

import com.knowre.android.codilitytest.base.ImageBinder
import com.knowre.android.codilitytest.dataSource.ImageDataSourceApi
import com.knowre.android.codilitytest.entity.ImageEntity
import com.knowre.android.codilitytest.extensions.next
import com.knowre.android.codilitytest.extensions.previous
import com.knowre.android.codilitytest.glide.ImageBinderFactory
import com.knowre.android.codilitytest.http.api.ImageApi
import com.knowre.android.codilitytest.screen.detail.view.dto.DetailViewState
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject


internal class DetailDataSource @Inject constructor(
    private val imageDataSource: ImageDataSourceApi,
    private val imageBinderFactory: ImageBinderFactory

) : DetailDataSourceApi, ImageDataSourceApi by imageDataSource {

    override fun fetchViewState(id: Int, url: String, author: String, viewWidth: Int, imageOriginalWidth: Int, imageOriginalHeight :Int, isGrayScale: Boolean, isBlur: Boolean, scope: CoroutineScope): DetailViewState {
        val requestedSize = getRequestedSize(viewWidth, imageOriginalWidth, imageOriginalHeight)

        val realUrl = when {
            isGrayScale -> url + ImageApi.GRAY_SCALE_SUFFIX
            isBlur      -> url + ImageApi.BLUR_SUFFIX
            else        -> url
        }

        return DetailViewState(
            id                  = id,
            url                 = realUrl,
            imageOriginalWidth  = imageOriginalWidth,
            imageOriginalHeight = imageOriginalHeight,
            requestedWidth      = requestedSize.first,
            requestedHeight     = requestedSize.second,
            author              = author,
            isGrayScale         = isGrayScale,
            isBlur              = isBlur,
            setImage            = imageBinderFactory.getImageBinder(scope)
        )
    }

    override suspend fun fetchNextImageEntity(currentImageId: Int, viewWidth: Int, scope: CoroutineScope): DetailViewState? {
        val pagedImageEntity = imageDataSource.getAll().first { it.entities.find { entity -> entity.id == currentImageId } != null }

        val next = pagedImageEntity.entities.next(pagedImageEntity.entities.first { it.id == currentImageId })

        return if (next == null) {
            val imageEntities = imageDataSource.fetchImageList(pagedImageEntity.pageNumber + 1)

            if (imageEntities.isNotEmpty()) {
                imageEntities.first()
            } else {
                null
            }
        } else {
            next
        }
            ?.run { getDetailViewState(viewWidth, imageBinderFactory.getImageBinder(scope)) }
    }

    override suspend fun fetchPreviousImageEntity(currentImageId: Int, viewWidth: Int, scope: CoroutineScope): DetailViewState? {
        val pagedImageEntity = imageDataSource.getAll().first { it.entities.find { entity -> entity.id == currentImageId } != null }

        return pagedImageEntity.entities.previous(pagedImageEntity.entities.first { it.id == currentImageId })?.run { getDetailViewState(viewWidth, imageBinderFactory.getImageBinder(scope)) }
    }

    private fun ImageEntity.getDetailViewState(viewWidth: Int, imageBinder: ImageBinder): DetailViewState {
        val requestedSize = getRequestedSize(viewWidth, width, height)

        return DetailViewState(
            id                  = id,
            url                 = downloadUrl,
            author              = author,
            imageOriginalWidth  = width,
            imageOriginalHeight = height,
            requestedWidth      = requestedSize.first,
            requestedHeight     = requestedSize.second,
            isGrayScale         = false,
            isBlur              = false,
            setImage            = imageBinder
        )
    }

    private fun getRequestedSize(viewWidth: Int, originalWidth: Int, originalHeight: Int): Pair<Int, Int> {
        val ratio           = originalWidth.toFloat() / originalHeight.toFloat()
        val requestedHeight = (viewWidth/ratio).toInt()

        return Pair(viewWidth, requestedHeight)
    }

}