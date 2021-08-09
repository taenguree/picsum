package com.knowre.android.codilitytest.glide

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.module.AppGlideModule


@GlideModule
internal class PicsumGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val diskCacheSizeBytes = 1024L * 1024 * 1024 * 4 //4G
        builder.setDiskCache(InternalCacheDiskCacheFactory(context, diskCacheSizeBytes))
    }

}