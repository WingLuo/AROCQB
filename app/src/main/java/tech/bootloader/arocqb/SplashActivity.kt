package tech.bootloader.arocqb

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.litepal.LitePal
import tech.bootloader.androidbasic.InterfaceInitializationView
import tech.bootloader.arocqb.database.ExamQuestionBank
import tech.bootloader.arocqb.database.Statistics
import tech.bootloader.arocqb.util.AssetsUtil
import tech.bootloader.arocqb.util.DBManager
import java.lang.Exception


class SplashActivity : AppCompatActivity(),InterfaceInitializationView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        findViewById<View>(R.id.progressBar).post{
            initPresenter()
        }

    }


    /**
     * 文件直读数据 写入数据库
     */
    override fun initData() {
        val string = AssetsUtil.getFromAssets(this, "总题库文件(v171031).txt")
        val classas = AssetsUtil.getFromAssets(this, "A_试卷涉及题号(v170717).txt")
        val classbs = AssetsUtil.getFromAssets(this, "B_试卷涉及题号(v170717).txt")
        val classcs = AssetsUtil.getFromAssets(this, "C_试卷涉及题号(v170717).txt")
//        Log.d("tag", string)
        //数据处理
        val split = string.split("[I]")
        split.forEach { s ->
            if (TextUtils.isEmpty(s)) return@forEach
//            LK0504[Q]附图中的电路元器件符号代表的是：[A]电池[B]二极管[C]线圈[D]电阻[P]LK0504.jpg
            val q = "[Q]"
            val a = "[A]"
            val b = "[B]"
            val c = "[C]"
            val d = "[D]"
            val p = "[P]"
            var re = ""
            re = s.replace(q, "[]")
                .replace(a, "[]")
                .replace(d, "[]")
                .replace(p, "[]")
                .replace(b, "[]")
                .replace(c, "[]")
            val si = re.split("[]")


            val qb = ExamQuestionBank()
            qb.i = si[0]
            qb.q = si[1]
            qb.a = si[2]
            qb.b = si[3]
            qb.c = si[4]
            qb.d = si[5]
            if (si.size > 6) qb.p = si[6]
            qb.save()

        }


        val splita = classas.split("， ")
        val splitb = classbs.split("， ")
        val splitc = classcs.split("， ")

        splita.forEach {
            val a = LitePal.where("i=?", it).findFirst(ExamQuestionBank::class.java)
            a.classA = 1
            a.saveOrUpdate("i=?", it)
        }
        splitb.forEach {
            val b = LitePal.where("i=?", it).findFirst(ExamQuestionBank::class.java)
            b.classB = 1
            b.saveOrUpdate("i=?", it)
        }
        splitc.forEach {
            val c = LitePal.where("i=?", it).findFirst(ExamQuestionBank::class.java)
            c.classC = 1
            c.saveOrUpdate("i=?", it)
        }

        val sa = Statistics("A", 30, 361)
        sa.save()
        val sb = Statistics("B", 50, 685)
        sb.save()
        val sc = Statistics("C", 80, 1061)
        sc.save()
        val la = LitePal.where("classA=?", "1").find(ExamQuestionBank::class.java)
        Log.d("tag", "a Size: ${la.size}")
        val lb = LitePal.where("classB=?", "1").find(ExamQuestionBank::class.java)
        Log.d("tag", "b Size: ${lb.size}")
        val lc = LitePal.where("classC=?", "1").find(ExamQuestionBank::class.java)
        Log.d("tag", "c Size: ${lc.size}")

        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun initView() {

    }

    override fun initPresenter() {
        initDataBase()

    }

    /**
     * 初始化
     * 将数据库复制到系统文件夹里面
     */
    private fun initDataBase() {
        var dbHelper = DBManager(this)
        dbHelper.openDatabase()
        try {
            dbHelper.closeDatabase()
            startActivity(Intent(this, MainActivity::class.java))
        }catch (e:Exception){
            e.printStackTrace()
            initData()

        }

    }
}