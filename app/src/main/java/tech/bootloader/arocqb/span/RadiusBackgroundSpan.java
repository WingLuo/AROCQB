package tech.bootloader.arocqb.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.text.style.ReplacementSpan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 背景带圆角，可设置颜色，角度
 * Created by g on 2018/1/23.
 */
public class RadiusBackgroundSpan extends ReplacementSpan {
    private int fontSize = -1;
    private boolean isSp = false;
    private int margin;
    private int radius;
    private int textColor;
    private int bgColor;

    public RadiusBackgroundSpan(int bgColor, int textColor, int radius, int margin, int fontSize) {
        this.fontSize = fontSize;
        this.margin = margin;
        this.radius = radius;
        this.textColor = textColor;
        this.bgColor = bgColor;
    }

    public RadiusBackgroundSpan(int bgColor, int textColor, int radius) {
        this.margin = 5;
        this.radius = radius;
        this.textColor = textColor;
        this.bgColor = bgColor;
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, @Nullable Paint.FontMetricsInt fm) {
        Paint newPaint = getCustomTextPaint(paint);
        return (int) newPaint.measureText(text, start, end) + margin * 2;
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int
            bottom, @NonNull Paint paint) {
        Paint newPaint = getCustomTextPaint(paint);

        int textWidth = (int) newPaint.measureText(text, start, end);

        RectF rect = new RectF();
        rect.top = top + margin;
        rect.bottom = bottom - margin;
        rect.left = (int) (x + margin);
        rect.right = rect.left + textWidth + margin;
        paint.setColor(bgColor);
        canvas.drawRoundRect(rect, radius, radius, paint);

        newPaint.setColor(textColor);
        Paint.FontMetrics fontMetrics = newPaint.getFontMetrics();
        int offsetX = (int) ((rect.right - rect.left - textWidth) / 2) + margin;
        int offsetY = (int) ((y + fontMetrics.ascent + y + fontMetrics.descent) / 2 - (top + bottom) / 2);
        canvas.drawText(text, start, end, x + offsetX, y - offsetY, newPaint);

    }

    private TextPaint getCustomTextPaint(Paint srcPaint) {
        TextPaint textPaint = new TextPaint(srcPaint);
        if (fontSize != -1) {
            textPaint.setTextSize(isSp ? fontSize * textPaint.density : fontSize);
        }
        return textPaint;
    }
}