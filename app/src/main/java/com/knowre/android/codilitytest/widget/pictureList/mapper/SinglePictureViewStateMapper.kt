package com.knowre.android.codilitytest.widget.pictureList.mapper

import com.knowre.android.codilitytest.base.ImageBinder
import com.knowre.android.codilitytest.entity.ImageEntity
import com.knowre.android.codilitytest.widget.singlePicture.state.SinglePictureViewState
import javax.inject.Inject


internal class SinglePictureViewStateMapper @Inject constructor() {

    fun transform(imageEntity: ImageEntity, width: Int, columnCount: Int, imageBinder: ImageBinder): SinglePictureViewState {
        val ratio = imageEntity.getRatio()

        val requestedWidth  = width/columnCount
        val requestedHeight = (requestedWidth/ratio).toInt()

        return SinglePictureViewState(
            id                  = imageEntity.id,
            imageOriginalWidth  = imageEntity.width,
            imageOriginalHeight = imageEntity.height,
            requestedWidth      = requestedWidth,
            requestedHeight     = requestedHeight,
            url                 = imageEntity.downloadUrl,
            author              = imageEntity.author,
            setImage            = imageBinder
        )
    }

}