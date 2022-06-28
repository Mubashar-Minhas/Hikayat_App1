package com.nebulark.hikayatapp

import android.content.Context
import android.widget.Toast

class ShowToast {
    fun toast(context: Context?, msg: String?) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    } // makeToast closed

}