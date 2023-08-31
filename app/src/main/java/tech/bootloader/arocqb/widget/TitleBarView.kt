package tech.bootloader.arocqb.widget


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.IdRes
import tech.bootloader.arocqb.R

import tech.bootloader.arocqb.databinding.LayoutActionTitleBinding


class TitleBarView : FrameLayout {
    private var binding: LayoutActionTitleBinding =
        LayoutActionTitleBinding.inflate(LayoutInflater.from(context), this, true)
    private lateinit var clickBody: (view: View) -> Unit
    private lateinit var doNextBody: (view: View) -> Unit

    constructor(context: Context, attrs: AttributeSet? = null) : super(context, attrs) {

        val ta: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBarView)
        val text: String? = ta.getString(R.styleable.TitleBarView_android_text)
        binding.tvTitle.text = text
        val hide: Boolean = ta.getBoolean(R.styleable.TitleBarView_hide_back, false)
        if (hide) {
            hideBackButton()
        }

        var colors: ColorStateList? = ta.getColorStateList(R.styleable.TitleBarView_text_color)
        if (colors != null) {
            val defaultColor = colors.defaultColor
            binding.tvTitle.setTextColor(defaultColor)
            binding.tvNext.setTextColor(defaultColor)
        }


        val d: Drawable? = ta.getDrawable(R.styleable.TitleBarView_android_src)
        if (d != null) {
            binding.ivBack.setImageDrawable(d)
        }

        binding.btnBack.setOnClickListener {
            if (this::clickBody.isInitialized) {
                clickBody(it)
            } else {
                if (context is Activity) {
                    context.finish()
                }
            }

        }
        binding.btnNext.setOnClickListener {
            if (this::doNextBody.isInitialized) doNextBody(it)
        }

        ta.recycle()
    }

    /**
     * 设置标题
     */
    public fun setTitle(title: String) {
        binding.tvTitle.text = title
    }

    /**
     * 设置下一步文字
     */
    public fun setNextText(next: String) {
        binding.tvNext.text = next
    }

    public fun setTextColor(color: Int) {
        binding.tvTitle.setTextColor(color)
    }

    public fun setNextTextColor(color: Int) {
        binding.tvNext.setTextColor(color)
    }

    public fun setNextTextBackground(resId: Int) {
        binding.tvNext.setBackgroundResource(resId)
    }

    public fun setColorNext(context: Context, nextColor: Int) {
        binding.tvNext.setTextColor(context.resources.getColor(nextColor))
    }

    /**
     * 设置点击事件
     */
    public fun setDoNext(body: (view: View) -> Unit) {
        doNextBody = body

    }

    /**
     * 返回按钮点击事件
     */
    public fun setDoBack(body: (view: View) -> Unit) {
        clickBody = body
    }

    /**
     * 设置返回按钮图片
     */
    @SuppressLint("ResourceType")
    public fun setBackImage(@IdRes resId: Int) {
        binding.ivBack.setImageResource(resId)
    }

    /**
     * 设置背景图片
     */
    @SuppressLint("ResourceType")
    public fun setBackground(@IdRes resId: Int) {
        binding.root.setBackgroundResource(resId)
    }

    /**
     * 隐藏返回按钮
     */
     fun hideBackButton() {
        binding.btnBack.visibility = View.INVISIBLE
    }
    fun showBackButton() {
        binding.btnBack.visibility = View.VISIBLE
    }

    /**
     * 显示下一步图片按钮 默认搜索
     */
    public fun showNextImage() {
        binding.ivNext.visibility = View.VISIBLE
    }

    /**
     * 隐藏下一步按钮
     */
    public fun hintNextImage() {
        binding.ivNext.visibility = View.GONE
    }

    /**
     * 隐藏下一步文字
     */
    public fun hintNextTv() {
        binding.tvNext.visibility = View.GONE
    }

    public fun showNextTv() {
        binding.tvNext.visibility = View.VISIBLE
    }

    @SuppressLint("ResourceType")
    public fun setNextImage(@IdRes resId: Int) {
        binding.ivNext.setImageResource(resId)
        showNextImage()
    }

    public fun setNextImage(drawable: Drawable?) {
        binding.ivNext.setImageDrawable(drawable)
        showNextImage()
    }

    public fun getBadgePoint(): View {
        return binding.badge
    }


}