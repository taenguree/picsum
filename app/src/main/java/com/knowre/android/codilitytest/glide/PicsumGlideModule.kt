package com.knowre.android.codilitytest.glide

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.module.AppGlideModule


@GlideModule
internal class PicsumGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        /**
         * 현재 이미지를 base64 인코딩해 room 으로 저장하는 방식으로 로컬 캐쉬를 사용하고 있기 때문에
         * 글라이드 디스크 캐쉬 사이즈를 늘려도, 큰 효용은 없다. 다만, room 을 이용하지 않을 시 아래와 같이 사이지를 늘려 글라이드 캐쉬를 사용할 수 있음을 명시하기 위해 아래와 같은 코드를 삽입.
         * 추가적으로, base64 인코딩한 후 room 을 이미지를 캐쉬해 불러오는 것이 이미지가 어느정도 클 경우 글라이드 캐쉬를 이용해 가져오는 것보다 속도가 빠르다.
         */
        val diskCacheSizeBytes = 1024L * 1024 * 1024 * 4 //4G
        builder.setDiskCache(InternalCacheDiskCacheFactory(context, diskCacheSizeBytes))
    }

}