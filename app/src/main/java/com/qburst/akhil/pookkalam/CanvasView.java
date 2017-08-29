package com.qburst.akhil.pookkalam;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.FloatMath;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by guru on 29-08-17.
 */

public class CanvasView extends ImageView {
    Paint paint;
    Context context;
    Point center;
    int radius;
    int widthHeight[];
    private String TAG = "Custom Tag ";

    public CanvasView(Context context) {
        super(context);
        center = new Point();
        this.context = context;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        //paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.));
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //drawInnerCircle(canvas);
        drawOuterCircle(canvas);
    }

    private void drawInnerCircle(Canvas canvas) {
        paint.setColor(Color.GREEN);
        canvas.drawCircle(center.x, center.y, radius / 2, paint);
        drawCircumscribedTriangle(canvas, center.x, center.y, radius / 2, paint);
    }

    private void drawOuterCircle(Canvas canvas) {
        canvas.drawCircle(center.x, center.y, radius, paint);
        drawCircumscribedTriangle(canvas, center.x, center.y, radius, paint);
        drawInvertedTriangle(canvas, center.x, center.y, radius, paint);
    }

    private void drawInvertedTriangle(Canvas canvas, int x, int y, int radius, Paint paint) {
        float xOffsetFromCenter = (float) (Math.cos((float) Math.PI / 6) * radius);
        float yOffsetFromCenter = (float) (Math.sin((float) Math.PI / 6) * radius);
        paint.setColor(Color.RED);
        canvas.drawLine(x, y + radius, x - xOffsetFromCenter, y - yOffsetFromCenter, paint);
        paint.setColor(Color.BLUE);
        canvas.drawLine(x - xOffsetFromCenter, y - yOffsetFromCenter, x + xOffsetFromCenter,
                y - yOffsetFromCenter, paint);
        paint.setColor(Color.GREEN);
        canvas.drawLine(x + xOffsetFromCenter,y-yOffsetFromCenter, x, y + radius, paint);
    }

    public void setWidthHeight(int[] widthHeight) {
        this.widthHeight = widthHeight;
        center.set(widthHeight[0] / 2, widthHeight[1] / 2);
        radius = (widthHeight[0] - 40) / 2;
        Log.d(TAG, "setWidthHeight: " + radius);
    }

    private void drawCircumscribedTriangle(Canvas canvas, float circleCenterX, float circleCenterY, int radius, Paint paint) {
        float xOffsetFromCenter = (float) (Math.cos((float) Math.PI / 6) * radius);
        float yOffsetFromCenter = (float) (Math.sin((float) Math.PI / 6) * radius);
        paint.setColor(Color.RED);
        canvas.drawLine(circleCenterX, circleCenterY - radius, circleCenterX + xOffsetFromCenter, circleCenterY + yOffsetFromCenter, paint);
        paint.setColor(Color.BLUE);
        canvas.drawLine(circleCenterX + xOffsetFromCenter, circleCenterY + yOffsetFromCenter, circleCenterX - xOffsetFromCenter,
                circleCenterY + yOffsetFromCenter, paint);
        paint.setColor(Color.GREEN);
        canvas.drawLine(circleCenterX - xOffsetFromCenter, circleCenterY + yOffsetFromCenter, circleCenterX, circleCenterY - radius, paint);
    }
}
