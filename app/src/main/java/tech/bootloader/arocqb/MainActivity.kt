package tech.bootloader.arocqb

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.litepal.LitePal
import tech.bootloader.androidbasic.InterfaceInitializationView
import tech.bootloader.arocqb.database.ExamQuestionBank

class MainActivity : AppCompatActivity(),InterfaceInitializationView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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