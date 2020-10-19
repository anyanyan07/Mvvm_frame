package com.ayy.base.loadsir

import com.ayy.base.R
import com.kingja.loadsir.callback.Callback

class EmptyCallback : Callback() {
    override fun onCreateView(): Int {
        return R.layout.callback_empty
    }
}