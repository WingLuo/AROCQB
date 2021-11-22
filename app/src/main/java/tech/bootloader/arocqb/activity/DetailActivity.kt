package tech.bootloader.arocqb.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tech.bootloader.androidbasic.abstrakt.PortraitActivity
import tech.bootloader.arocqb.R
import tech.bootloader.arocqb.common.GRADE
import tech.bootloader.arocqb.common.MAX
import tech.bootloader.arocqb.databinding.ActivityDetailBinding

class DetailActivity : PortraitActivity() {
    private lateinit var grade: String
    private var max: Int = -1
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding=  ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initPresenter()
        initView()
        initData()
    }

    override fun initPresenter() {
      max=  intent.getIntExtra(MAX,0)
      grade= intent.getStringExtra(GRADE)!!

    }

    override fun initView() {

    }

    override fun initData() {
    }

    companion object {
        fun actionStart(activity: Activity, grade: String,max:Int) {
            val intent = Intent(activity, DetailActivity::class.java)
            //等级
            intent.putExtra(GRADE, grade)
            //最大总条数
            intent.putExtra(MAX, max)
            activity.startActivity(intent)
        }
    }


}