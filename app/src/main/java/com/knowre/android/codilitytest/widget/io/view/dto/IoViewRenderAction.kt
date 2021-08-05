package com.knowre.android.codilitytest.widget.io.view.dto

import com.knowre.android.codilitytest.widget.base.ViewAction


internal sealed class IoViewRenderAction : ViewAction {
    class ShowLoading() : IoViewRenderAction()
    class HideLoading() : IoViewRenderAction()
}