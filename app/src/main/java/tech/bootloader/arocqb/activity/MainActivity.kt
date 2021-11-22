package tech.bootloader.arocqb.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import org.litepal.LitePal
import tech.bootloader.androidbasic.abstrakt.PortraitActivity
import tech.bootloader.arocqb.database.ExamQuestionBank
import tech.bootloader.arocqb.databinding.ActivityMainBinding

class MainActivity : PortraitActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initPresenter()
        initView()
        Thread {
            initData()
        }.start()

    }

    override fun initPresenter() {
        val sp = getSharedPreferences("first", Context.MODE_PRIVATE)
        //获取偏好设置
        val editor = sp.edit()
        //获取偏好设置的编辑者
        editor.putBoolean("first", false)
        //编辑偏好设置
        editor.commit()

    }

    override fun initView() {
        binding.btnSelectA.setOnClickListener {
            resetButton()
            binding.btnSelectA.alpha=1f
            binding.ivA.visibility=View.VISIBLE

        }
        binding.btnSelectB.setOnClickListener {
            resetButton()
            binding.btnSelectB.alpha=1f
            binding.ivB.visibility=View.VISIBLE
        }
        binding.btnSelectC.setOnClickListener {
            resetButton()
            binding.btnSelectC.alpha=1f
            binding.ivC.visibility=View.VISIBLE
        }
        binding.btnSelectE.setOnClickListener {
            resetButton()
            binding.btnSelectE.alpha=1f
            binding.ivE.visibility=View.VISIBLE
        }


    }

    private fun resetButton() {
        binding.btnSelectA.alpha=0.6f
        binding.btnSelectB.alpha=0.6f
        binding.btnSelectC.alpha=0.6f
        binding.btnSelectE.alpha=0.6f

        binding.ivA.visibility=View.INVISIBLE
        binding.ivB.visibility=View.INVISIBLE
        binding.ivC.visibility=View.INVISIBLE
        binding.ivE.visibility=View.INVISIBLE

    }

    override fun initData() {
        val la = LitePal.where("classA=?", "1").find(ExamQuestionBank::class.java)
        Log.d("tag", "a Size: ${la.size}")
        val lb = LitePal.where("classB=?", "1").find(ExamQuestionBank::class.java)
        Log.d("tag", "b Size: ${lb.size}")
        val lc = LitePal.where("classC=?", "1").find(ExamQuestionBank::class.java)
        Log.d("tag", "c Size: ${lc.size}")

    }
}