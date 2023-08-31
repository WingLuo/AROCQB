package com.lingkj.basic.extensions
import android.app.Service
import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * dp转换成px
 */
fun Context.dp2px(dpValue: Float): Float {
    var scale = resources.displayMetrics.density;
    return dpValue * scale + 0.5f
}


fun Context.sp2px( spValue: Float): Int {
    val fontScale = resources.displayMetrics.scaledDensity
    return (spValue * fontScale + 0.5f).toInt()
}

/**
 * 获取状态栏高度
 */
fun Context.getStatusBarHeight(): Int {
    var statusBarHeight = 0
    try {
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        statusBarHeight = resources.getDimensionPixelSize(resourceId)

//        var clz = Class.forName("com.android.internal.R\$dimen")
//        var obj = clz!!.newInstance()
//        var field = clz.getField("status_bar_height")
//        var status_bar_height = Integer.parseInt(field!!.get(obj).toString())
//        statusBarHeight = resources.getDimensionPixelSize(status_bar_height)


    } catch (e: Exception) {
        e.printStackTrace()
    }

    return statusBarHeight
}


fun Context.getScreenWidth(): Int {
    val displayMetrics = DisplayMetrics()
    val wm = getSystemService(Service.WINDOW_SERVICE) as WindowManager
    if (Build.VERSION.SDK_INT <= 17) {
        wm.defaultDisplay.getMetrics(displayMetrics)
    } else {
        wm.defaultDisplay.getRealMetrics(displayMetrics)
    }
    return displayMetrics.widthPixels
}

fun Context.getScreenHeight(): Int {
    val displayMetrics = DisplayMetrics()
    val wm = getSystemService(Service.WINDOW_SERVICE) as WindowManager
    if (Build.VERSION.SDK_INT <= 17) {
        wm.defaultDisplay.getMetrics(displayMetrics)
    } else {
        wm.defaultDisplay.getRealMetrics(displayMetrics)
    }
    return displayMetrics.heightPixels
}




