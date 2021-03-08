// -*- Mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
//
package com.zq.base.utils

import android.content.SharedPreferences
import android.os.Build

class EditorUtils {
    companion object {
        @JvmStatic
        fun fastCommit(editor: SharedPreferences.Editor) {
            // edit.apply could not commit your preferences changes in time on
            // Android 4.3
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                editor.apply()
            } else {
                // FIXME: there's no fast commit below GINGERBREAD.
                editor.commit()
            }
        }

    }

}