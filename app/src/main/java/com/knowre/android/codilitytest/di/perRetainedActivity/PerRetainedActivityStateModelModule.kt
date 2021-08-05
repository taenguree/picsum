package com.knowre.android.codilitytest.di.perRetainedActivity

import android.util.Log
import com.knowre.android.codilitytest.base.BaseStateModel
import com.knowre.android.codilitytest.di.qualifier.PictureListIo
import com.knowre.android.codilitytest.http.callState.CallStateListenerApi
import com.knowre.android.codilitytest.widget.io.IoStateModel
import com.knowre.android.codilitytest.widget.io.dto.IoAction
import com.knowre.android.codilitytest.widget.io.dto.IoState
import com.knowre.android.codilitytest.widget.io.view.dto.IoViewState
import com.knowre.android.codilitytest.widget.pictureList.PictureListStateModel
import com.knowre.android.codilitytest.widget.pictureList.dto.PictureListAction
import com.knowre.android.codilitytest.widget.pictureList.dto.PictureListState
import com.knowre.android.codilitytest.widget.pictureList.view.dto.PictureListViewState
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import retrofit2.Call


@InstallIn(ActivityRetainedComponent::class)
@Module(includes = [PerRetainedActivityStateModelModule.ProvideModule::class])
internal interface PerRetainedActivityStateModelModule {
    @InstallIn(ActivityComponent::class)
    @Module
    object ProvideModule {
        @Provides
        fun provideCallStateListener(@PictureListIo ioStateModel: BaseStateModel<IoViewState, IoState, IoAction>): CallStateListenerApi<Call<*>> {
            Log.d("MY_LOG", "provideCallStateListener $ioStateModel")
            return ioStateModel as IoStateModel
        }
    }

    @Binds
    fun providePictureListStateModel(stateModel: PictureListStateModel): BaseStateModel<PictureListViewState, PictureListState, PictureListAction>

    @Binds
    @PictureListIo
    fun providePictureListIoStateModel(stateModel: IoStateModel): BaseStateModel<IoViewState, IoState, IoAction>
}