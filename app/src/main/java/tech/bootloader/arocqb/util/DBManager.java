package tech.bootloader.arocqb.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import tech.bootloader.arocqb.R;

public class DBManager {
    private final int BUFFER_SIZE = 400000;
    public static final String DB_NAME = "ExamQuestionBank.db"; //保存的数据库文件名
    public static String PACKAGE_NAME = "tech.bootloader.arocqb"  ;
    public static final String DB_PATH = "/data"
            + Environment.getDataDirectory().getAbsolutePath() + "/"
            + PACKAGE_NAME;  //在手机里存放数据库的位置
    private SQLiteDatabase database;
    private Context context;

    public DBManager(Context context) {
        this.context = context;
        PACKAGE_NAME=context.getPackageName();
    }

    public void openDatabase() {
        this.database = this.openDatabase(DB_PATH + "/databases/" + DB_NAME);
    }

    private SQLiteDatabase openDatabase(String dbfile) {
        try {
            if (!(new File(dbfile).exists())) {//判断数据库文件是否存在，若不存在则执行导入，否则直接打开数据库
                InputStream is = this.context.getResources().openRawResource(
                        R.raw.exam_question_bank); //欲导入的数据库
                FileOutputStream fos = new FileOutputStream(dbfile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            }
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile,
                    null);
            return db;
        } catch (FileNotFoundException e) {
            Log.e("Database", "File not found");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Database", "IO exception");
            e.printStackTrace();
        }
        return null;
    }

//do something else here<br>

    public void closeDatabase() {
        this.database.close();
    }
}