package tech.bootloader.arocqb.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import org.litepal.LitePal
import tech.bootloader.androidbasic.abstrakt.PortraitActivity
import tech.bootloader.androidbasic.utils.SharedPreferencesUtils
import tech.bootloader.arocqb.common.FIRST
import tech.bootloader.arocqb.common.GRADE
import tech.bootloader.arocqb.common.INDEX
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
        val sp = getSharedPreferences(FIRST, Context.MODE_PRIVATE)
        //获取偏好设置
        val editor = sp.edit()
        //获取偏好设置的编辑者
        editor.putBoolean(FIRST, false)
        //编辑偏好设置
        editor.commit()

    }

    override fun initView() {


        binding.btnSelectA.setOnClickListener {
            if (binding.ivA.visibility ==View.VISIBLE) return@setOnClickListener
            resetButton()
            binding.btnSelectA.alpha = 1f
            binding.ivA.visibility = View.VISIBLE
            SharedPreferencesUtils.saveStringSharedPreferences(GRADE, "A")

        }
        binding.btnSelectB.setOnClickListener {
            if (binding.ivA.visibility ==View.VISIBLE) return@setOnClickListener

            resetButton()

            binding.btnSelectB.alpha = 1f
            binding.ivB.visibility = View.VISIBLE
            SharedPreferencesUtils.saveStringSharedPreferences(GRADE, "B")

        }
        binding.btnSelectC.setOnClickListener {
            if (binding.ivA.visibility ==View.VISIBLE) return@setOnClickListener
            resetButton()
            binding.btnSelectC.alpha = 1f
            binding.ivC.visibility = View.VISIBLE
            SharedPreferencesUtils.saveStringSharedPreferences(GRADE, "C")

        }
        binding.btnSelectE.setOnClickListener {
            if (binding.ivA.visibility ==View.VISIBLE) return@setOnClickListener
            resetButton()
            binding.btnSelectE.alpha = 1f
            binding.ivE.visibility = View.VISIBLE
            SharedPreferencesUtils.saveStringSharedPreferences(GRADE, "E")

        }
        val grade = SharedPreferencesUtils.getStringSharedPreferences(GRADE)
        when (grade) {
            "B" ->{
                binding.btnSelectB.alpha = 1f
                binding.ivB.visibility = View.VISIBLE
            }
            "C" -> {
                binding.btnSelectC.alpha = 1f
                binding.ivC.visibility = View.VISIBLE
            }
            "E" -> {
                binding.btnSelectE.alpha = 1f
                binding.ivE.visibility = View.VISIBLE
            }
            else -> {
                binding.btnSelectA.alpha = 1f
                binding.ivA.visibility = View.VISIBLE
            }


        }
        binding.btnSequentialExercises.setOnClickListener {

        }
        binding.btnRandomExercises.setOnClickListener {

        }
        binding.btnMockExam.setOnClickListener {

        }

    }

    private fun resetButton() {
        binding.btnSelectA.alpha = 0.6f
        binding.btnSelectB.alpha = 0.6f
        binding.btnSelectC.alpha = 0.6f
        binding.btnSelectE.alpha = 0.6f

        binding.ivA.visibility = View.INVISIBLE
        binding.ivB.visibility = View.INVISIBLE
        binding.ivC.visibility = View.INVISIBLE
        binding.ivE.visibility = View.INVISIBLE

        SharedPreferencesUtils.saveIntSharedPreferences(INDEX,0)

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