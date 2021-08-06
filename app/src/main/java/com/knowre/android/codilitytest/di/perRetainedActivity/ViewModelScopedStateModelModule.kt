package com.knowre.android.codilitytest.di.perRetainedActivity

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
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Call


@InstallIn(ViewModelComponent::class)
@Module(includes = [ViewModelScopedStateModelModule.ProvideModule::class])
internal interface ViewModelScopedStateModelModule {
    @InstallIn(ViewModelComponent::class)
    @Module
    object ProvideModule {
        @Provides
        @ViewModelScoped
        fun provideCallStateListener(@PictureListIo ioStateModel: BaseStateModel<IoViewState, IoState, IoAction>): Collection<CallStateListenerApi<Call<*>>> {
            return listOf(ioStateModel as IoStateModel)
        }
    }

    @Binds
    @ViewModelScoped
    fun providePictureListStateModel(stateModel: PictureListStateModel): BaseStateModel<PictureListViewState, PictureListState, PictureListAction>

    @Binds
    @ViewModelScoped
    @PictureListIo
    fun providePictureListIoStateModel(stateModel: IoStateModel): BaseStateModel<IoViewState, IoState, IoAction>
}