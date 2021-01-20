package tech.bootloader.arocqb.database;

import org.litepal.crud.LitePalSupport;

public class ExamQuestionBank  extends LitePalSupport {
    /**
     * 编号
     */
    private String i;
    /**
     * 问题
     */
    private String q;
    /**
     * 答案A
     */
    private String a;
    /**
     * 答案B
     */
    private String b;
    /**
     * 答案C
     */
    private String c;
    /**
     * 答案D
     */
    private String d;
    /**
     * 图片id
     */
    private String p;
    /**
     * A类试题
     */
    private int classA;
    /**
     * A类试题
     */
    private int classB;
    /**
     * A类试题
     */
    private int classC;

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public int getClassA() {
        return classA;
    }

    public void setClassA(int classA) {
        this.classA = classA;
    }

    public int getClassB() {
        return classB;
    }

    public void setClassB(int classB) {
        this.classB = classB;
    }

    public int getClassC() {
        return classC;
    }

    public void setClassC(int classC) {
        this.classC = classC;
    }

    @Override
    public String toString() {
        return "ExamQuestionBank{" +
                "i='" + i + '\'' +
                ", q='" + q + '\'' +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d='" + d + '\'' +
                ", p='" + p + '\'' +
                ", classA=" + classA +
                ", classB=" + classB +
                ", classC=" + classC +
                "} " + super.toString();
    }
}
