package com.nitrous.gradienttest;

import android.content.Context;
import android.graphics.Bitmap;
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
    private int gradientStartColor;
    private int gradientEndColor;

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
        gradientStartColor = ctx.getResources().getColor(R.color.gradient_start);
        gradientEndColor = ctx.getResources().getColor(R.color.gradient_end);
        gradientFill = new Paint();
        gradientFill.setDither(true);
        gradientFill.setAntiAlias(true);
        gradientFill.setStyle(Paint.Style.FILL);
        rect = null;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (rect != null) {
            canvas.drawRect(rect, gradientFill);
        }
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // Refresh the gradient to match the view size and then repaint
        rect = new RectF(0.0F, 0.0F, w, h);
        gradientFill.setShader(new RadialGradient(
                rect.width() / 2.0F,
                rect.height() / 2.0f,
                rect.width() * 0.75f,
                gradientStartColor,
                gradientEndColor,
                Shader.TileMode.CLAMP));

        postInvalidate();
    }

}
