package com.lingkj.basic.extensions

import android.os.Build
import android.text.Html
import android.text.Spanned
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.recyclerview.widget.RecyclerView


fun View.setViewLayoutParams(nWidth: Int, nHeight: Int) {
    val lp = this.layoutParams
    if (nWidth >= 0) {
        lp.width = nWidth
    }
    if (nHeight >= 0) {
        lp.height = nHeight
    }
    this.layoutParams = lp
}

fun View.setMargins(l: Int, t: Int, r: Int, b: Int) {
    if (this.layoutParams is ViewGroup.MarginLayoutParams) {
        val p: ViewGroup.MarginLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
        p.setMargins(l, t, r, b)
        this.requestLayout()
    }
}

fun View.dip2px(dpValue: Float): Int {
    val scale = this.context.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

fun View.removeSelfFromParent() {
        val parent = this.parent as ViewGroup
        if (parent != null && parent is ViewGroup) {
            parent.removeView(this)
        }

}

 fun RecyclerView.isSlideToBottom(): Boolean {
    return computeVerticalScrollExtent() + computeVerticalScrollOffset() >= computeVerticalScrollRange()
}

fun WebView.loadDataWithBaseURL(htmlData: String?) {

    if (!TextUtils.isEmpty(htmlData)) {
        val header = "<header><meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no'><style>img{max-width:100%}</style></header>"
        val  sb=StringBuilder()
        sb.append(header)
        sb.append(htmlData)
        var spanned: Spanned? = null
        spanned = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //使用HTML的方法转义，api24以上方法
            Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_COMPACT)
        } else {
            //使用HTML的方法转义，api24以下方法
            Html.fromHtml(sb.toString())
        }
//        MCLog.e("打印HTML源码", spanned.toString())
        loadDataWithBaseURL(null, spanned.toString(), "text/html", "utf-8", null)
        //数据加载后隐藏缩放按钮
        settings.displayZoomControls = false
    }

}



