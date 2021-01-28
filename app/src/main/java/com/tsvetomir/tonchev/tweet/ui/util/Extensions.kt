package com.tsvetomir.tonchev.tweet.ui.util

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addItemDivider(context: Context, dividerDrawableId: Int) {
    val dividerItemDecoration =
        DividerItemDecoration(context, RecyclerView.VERTICAL)
    val dividerDrawable =
        ContextCompat.getDrawable(context, dividerDrawableId)
    if (dividerDrawable != null) {
        dividerItemDecoration.setDrawable(dividerDrawable)
        addItemDecoration(dividerItemDecoration)
    }
}