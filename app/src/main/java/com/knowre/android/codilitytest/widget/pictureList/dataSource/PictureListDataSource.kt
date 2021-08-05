package com.knowre.android.codilitytest.widget.pictureList.dataSource

import android.graphics.Bitmap
import android.graphics.Canvas
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.knowre.android.codilitytest.R
import com.knowre.android.codilitytest.entity.ImageEntity
import com.knowre.android.codilitytest.helper.Base64Encoder
import com.knowre.android.codilitytest.http.api.ImageApi
import com.knowre.android.codilitytest.http.coroutine.CoroutineRemoteApi
import com.knowre.android.codilitytest.persistence.PreferenceApi
import com.knowre.android.codilitytest.widget.singlePicture.state.ImageBinder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


internal class PictureListDataSource @Inject constructor(
    private val api: ImageApi,
    private val coroutineRemote: CoroutineRemoteApi,
    private val imageCachePreference: PreferenceApi<String>,
    private val base64Encoder: Base64Encoder

) : PictureListDataSourceApi {

    companion object {
        private const val LIMIT = 100
    }

    private var currentPage = 1

    override suspend fun fetchImageList(): List<ImageEntity> {
        return coroutineRemote.execute(api.getImageList(currentPage, LIMIT))
    }

    override suspend fun fetchNextPageImageList(): List<ImageEntity> {
        currentPage++
        return fetchImageList()
    }

    override fun getBitmapFetcher(scope: CoroutineScope): ImageBinder {
        return { view: ImageView, id: Int, url: String, requestedWidth: Int, requestedHeight: Int, onStart: () -> Unit, onComplete:() -> Unit ->
            onStart()

            val glideRequest = Glide.with(view.context)

            tryLoadImageFromLocalCache(scope, view, id, url, requestedWidth, requestedHeight) {
                /** 로컬 캐쉬에서 bitmap 을 만들어 낸 것이 glide 로 부터 bitmap 만들어 낸 것보다 빨랐으므로 해당 이미지 뷰에 대한 모든 glide 요청 취소 */
                glideRequest.clear(view)

                onComplete()
            }

            tryLoadImageFromGlide(scope, glideRequest, view, id, url, requestedWidth, requestedHeight) {
                onComplete()
            }
        }
    }

    private fun tryLoadImageFromLocalCache(scope: CoroutineScope, view: ImageView, id: Int, url: String, requestedWidth: Int, requestedHeight: Int, onComplete:() -> Unit) {
        scope.launch(Dispatchers.IO) {
            val encoded = imageCachePreference.get(id.toString())

            if (encoded.isNotEmpty()) {
                val bitmap = base64Encoder.decode(encoded)

                withContext(Dispatchers.Main) {
                    onComplete()

                    view.setImageBitmap(bitmap)
                }
            }
        }
    }

    private fun tryLoadImageFromGlide(scope: CoroutineScope, glideRequest: RequestManager, view: ImageView, id: Int, url: String, requestedWidth: Int, requestedHeight: Int, onComplete:() -> Unit) {
        /** place holder 이미지를 원본 이미지의 사이즈 비율과 같데 만듦 */
        val drawable = ContextCompat.getDrawable(view.context, R.drawable.image_loading_thumbnail)!!
        val canvas = Canvas()
        val bitmap = Bitmap.createBitmap(requestedWidth, requestedHeight, Bitmap.Config.ARGB_8888)
        canvas.setBitmap(bitmap)
        drawable.setBounds(0, 0, requestedWidth, requestedHeight)
        drawable.draw(canvas)

        glideRequest
            .asBitmap()
            .load(url)
            .placeholder(drawable)
            .override(requestedWidth, requestedHeight)
            .listener(object : RequestListener<Bitmap?> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap?>?, isFirstResource: Boolean): Boolean {
                    onComplete()
                    return false
                }

                override fun onResourceReady(resource: Bitmap?, model: Any?, target: Target<Bitmap?>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    resource?.let {
                        scope.launch(Dispatchers.IO) {
                            val encoded = base64Encoder.encode(resource)

                            imageCachePreference.put(key = id.toString(), encoded)
                        }
                    }

                    onComplete()
                    return false
                }
            })
            .thumbnail(
                Glide.with(view.context)
                    .asBitmap()
                    .load(R.drawable.image_loading_thumbnail)
                    .centerCrop()
                    .override(requestedWidth, requestedHeight)
            )
            .into(view)
    }

}