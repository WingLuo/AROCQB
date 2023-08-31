package tech.bootloader.arocqb.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import androidx.core.content.ContextCompat
import com.lingkj.basic.extensions.sp2px
import tech.bootloader.androidbasic.abstrakt.PortraitActivity
import tech.bootloader.arocqb.R
import tech.bootloader.arocqb.common.GRADE
import tech.bootloader.arocqb.common.MAX
import tech.bootloader.arocqb.databinding.ActivityDetailBinding
import tech.bootloader.arocqb.span.RadiusBackgroundSpan

class DetailActivity : PortraitActivity() {
    private lateinit var grade: String
    private var max: Int = -1
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initPresenter()
        initView()
        initData()
    }

    override fun initPresenter() {
        max = intent.getIntExtra(MAX, 0)
        grade = intent.getStringExtra(GRADE)!!
        val ssb = SpannableStringBuilder()
        ssb.append("单选 ")
        ssb.setSpan(
            RadiusBackgroundSpan(
                ContextCompat.getColor(this, R.color.yellow_500),
                ContextCompat.getColor(this, R.color.white),
                15
                ,0,sp2px( 15f)
            ),
            0, 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
//        ssb.setSpan(
//            AbsoluteSizeSpan(sp2px( 100f)),
//            0,
//            2,
//            Spanned.SPAN_INCLUSIVE_INCLUSIVE
//        )
        ssb.append("我国现行法律体系中专门针对无线电管理的最高法律文件及其立法机关是：")
        binding.tvTopic.text = ssb

    }

    override fun initView() {

    }

    override fun initData() {
    }

    companion object {
        fun actionStart(activity: Activity, grade: String, max: Int) {
            val intent = Intent(activity, DetailActivity::class.java)
            //等级
            intent.putExtra(GRADE, grade)
            //最大总条数
            intent.putExtra(MAX, max)
            activity.startActivity(intent)
        }
    }


}