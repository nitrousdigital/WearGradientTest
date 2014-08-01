package com.nitrous.gradienttest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * A view that fills the background with a radial gradient.
 *
 * Created by nick on 8/1/14.
 */
public class GradientView extends View {
    private RectF rect;
    private Paint gradientFill;
    private int gradientStart;
    private int gradientEnd;

    public GradientView(Context context) {
        super(context);
        init(context);
    }

    public GradientView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GradientView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context ctx) {
        gradientStart = ctx.getResources().getColor(R.color.gradient_start);
        gradientEnd = ctx.getResources().getColor(R.color.gradient_end);
        gradientFill = new Paint();
        gradientFill.setDither(true);
        gradientFill.setAntiAlias(true);
        gradientFill.setStyle(Paint.Style.FILL);
        rect = new RectF(0.0F, 0.0F, 1f, 1f);
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawRect(rect, gradientFill);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rect = new RectF(0.0F, 0.0F, w, h);

        gradientFill.setShader(new RadialGradient(
                rect.width() / 2.0F,
                rect.height() / 2.0f,
                rect.width() * 0.75f,
                gradientStart,
                gradientEnd,
                Shader.TileMode.CLAMP));
        postInvalidate();
    }

}