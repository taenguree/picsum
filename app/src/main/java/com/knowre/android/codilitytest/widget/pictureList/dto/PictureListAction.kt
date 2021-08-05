package com.knowre.android.codilitytest.widget.pictureList.dto

import com.knowre.android.codilitytest.knowRedux.Action
import com.knowre.android.codilitytest.knowRedux.ViewCallbackAction
import com.knowre.android.codilitytest.knowRedux.ViewRenderAction
import com.knowre.android.codilitytest.widget.pictureList.view.action.PictureListCallbackAction
import com.knowre.android.codilitytest.widget.pictureList.view.action.PictureListRenderAction


internal sealed class PictureListAction : Action {
    class Render(override val action: PictureListRenderAction) : PictureListAction(), ViewRenderAction<PictureListRenderAction>

    class Callback(override val action: PictureListCallbackAction ) : PictureListAction(), ViewCallbackAction<PictureListCallbackAction>
}