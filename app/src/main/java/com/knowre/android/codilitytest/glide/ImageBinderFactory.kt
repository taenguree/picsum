package com.knowre.android.codilitytest.glide

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.knowre.android.codilitytest.R
import com.knowre.android.codilitytest.base.ImageBinder
import com.knowre.android.codilitytest.glide.GlideApp
import com.knowre.android.codilitytest.helper.Base64Encoder
import com.knowre.android.codilitytest.store.persistence.PersistenceStoreApi
import com.knowre.android.codilitytest.store.persistence.room.entity.LocalImageEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
internal class ImageBinderFactory @Inject constructor(
    private val base64Encoder: Base64Encoder,
    private val imagePersistenceCache: PersistenceStoreApi<@JvmSuppressWildcards Int, LocalImageEntity>

) {

    fun getImageBinder(scope: CoroutineScope): ImageBinder {
        return { view: ImageView, getIdFromView: () -> Int, id: Int, url: String, useCache: Boolean, requestedWidth: Int, requestedHeight: Int, onStart: () -> Unit, onComplete:() -> Unit ->
            view.setImageResource(R.drawable.image_loading_thumbnail)

            onStart()

            /** 이전 글라이드 요청이 있다면 취소 */
            Glide.with(view.context).clear(view)

            val glideRequest = GlideApp.with(view.context)

            if (useCache) {
                tryAsyncLoadImageFromLocalCache(scope, view, getIdFromView, id, decoding = { base64Encoder.decode(it) }) {
                    /** 로컬 캐쉬에서 bitmap 을 만들어 낸 것이 glide 로 부터 bitmap 만들어 낸 것보다 빨랐으므로 해당 이미지 뷰에 대한 모든 glide 요청 취소 */
                    glideRequest.clear(view)

                    onComplete()
                }
            }

            tryAsyncLoadImageFromGlide(glideRequest, view, getIdFromView, id, url, requestedWidth, requestedHeight) {
                if (useCache) {
                    it?.let {
                        scope.launch(Dispatchers.IO) {
                            val encoded = base64Encoder.encode(it)

                            imagePersistenceCache.put(key = id, LocalImageEntity(id, encoded))
                        }
                    }
                }

                onComplete()
            }
        }
    }

    private fun tryAsyncLoadImageFromLocalCache(scope: CoroutineScope, view: ImageView, getIdFromView: () -> Int, id: Int, decoding: (String) -> Bitmap, onComplete:() -> Unit) {
        scope.launch(Dispatchers.IO) {
            val localImageEntity = imagePersistenceCache.get(id)

            if (localImageEntity != null) {
                val bitmap = decoding(localImageEntity.encoded)

                withContext(Dispatchers.Main) {
                    if (getIdFromView() == id) {
                        onComplete()

                        view.setImageBitmap(bitmap)
                    }
                }
            }
        }
    }

    private fun tryAsyncLoadImageFromGlide(glideRequest: RequestManager, view: ImageView, getIdFromView: () -> Int, id: Int, url: String, requestedWidth: Int, requestedHeight: Int, onComplete:(Bitmap?) -> Unit) {
        /** place holder 이미지를 원본 이미지의 사이즈 비율과 같게 만듦 */
        val drawable = ContextCompat.getDrawable(view.context, R.drawable.image_loading_thumbnail)!!
        val canvas   = Canvas()
        val bitmap   = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        canvas.setBitmap(bitmap)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        drawable.draw(canvas)
        val scaled = Bitmap.createScaledBitmap(bitmap, requestedWidth, requestedHeight, false)

        glideRequest
            .asBitmap()
            .load(url)
            .placeholder(BitmapDrawable(view.context.resources, scaled))
            .override(requestedWidth, requestedHeight)
            .listener(object : RequestListener<Bitmap?> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap?>?, isFirstResource: Boolean): Boolean {
                    onComplete(null)
                    return false
                }

                override fun onResourceReady(resource: Bitmap?, model: Any?, target: Target<Bitmap?>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    return if (getIdFromView() == id) {
                        onComplete(resource)
                        false
                    } else {
                        true
                    }
                }
            })
            .into(view)
    }

}