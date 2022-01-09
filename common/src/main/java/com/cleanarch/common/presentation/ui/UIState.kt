package com.cleanarch.common.presentation.ui

import com.cleanarch.common.presentation.IViewData

sealed class UIState<out T>: IViewData {
    object DEFAULT : UIState<Nothing>()
    object LOADING : UIState<Nothing>()
    sealed class Loaded<out D> : UIState<D>() {
        data class Success<D>(val data: D) : Loaded<D>()
        data class Failure(@MessageViewType val messageViewType: Int, val errorMessage: String) : Loaded<Nothing>()
    }
}
