package com.knowre.android.codilitytest.di.perRetainedActivity

import com.knowre.android.codilitytest.base.BaseStateModel
import com.knowre.android.codilitytest.widget.pictureList.PictureListStateModel
import com.knowre.android.codilitytest.widget.pictureList.dto.PictureListAction
import com.knowre.android.codilitytest.widget.pictureList.dto.PictureListState
import com.knowre.android.codilitytest.widget.pictureList.view.state.PictureListViewState
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent


@InstallIn(ActivityRetainedComponent::class)
@Module
internal interface PerRetainedActivityStateModelModule {
    @Binds
    fun providePictureListStateModel(stateModel: PictureListStateModel): BaseStateModel<PictureListViewState, PictureListState, PictureListAction>
}