package com.cleanarch.common.presentation.ui;

import androidx.annotation.IntDef;
import kotlin.annotation.Retention;

@IntDef({MessageViewType.TOAST, MessageViewType.DIALOG})
@Retention()
public @interface MessageViewType {
    int TOAST = 0;
    int DIALOG = 1;
}
