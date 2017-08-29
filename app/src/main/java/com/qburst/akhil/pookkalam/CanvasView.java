package com.qburst.akhil.pookkalam;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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
    Canvas canvas;
    Context context;
    Point center;
    Path path;
    int radius;
    int widthHeight[];
    private String TAG = "Custom Tag ";

    public CanvasView(Context context) {
        super(context);
        path = new Path();
        center = new Point();
        this.context = context;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        //paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.));
        paint.setStrokeWidth(5);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        drawOuterCircle();
        drawInnerCircle();
        drawInnermostCircle();
    }

    private void drawInnermostCircle() {
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(center.x, center.y, radius / 4, paint);
    }

    private void drawInnerCircle() {
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(center.x, center.y, radius / 2, paint);
        drawCircumscribedTriangle(center.x, center.y, radius / 2, paint, Color.BLUE);
        drawInvertedTriangle(center.x, center.y, radius/2, paint, Color.GREEN);
    }

    private void drawOuterCircle() {
        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(center.x, center.y, radius, paint);
        drawCircumscribedTriangle(center.x, center.y, radius, paint, Color.GREEN);
        drawInvertedTriangle(center.x, center.y, radius, paint, Color.BLUE);
    }

    private void drawInvertedTriangle(int x, int y, int radius, Paint paint, int color) {
        float xOffsetFromCenter = (float) (Math.cos((float) Math.PI / 6) * radius);
        float yOffsetFromCenter = (float) (Math.sin((float) Math.PI / 6) * radius);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        path.reset();
        path.moveTo(x, y + radius);
        path.lineTo(x - xOffsetFromCenter, y - yOffsetFromCenter);
        path.lineTo(x + xOffsetFromCenter, y - yOffsetFromCenter);
        path.moveTo(x + xOffsetFromCenter, y - yOffsetFromCenter);
        path.lineTo(x, y + radius);
        path.close();
        canvas.drawPath(path, paint);
        /*canvas.drawLine(x, y + radius, x - xOffsetFromCenter, y - yOffsetFromCenter, paint);
        canvas.drawLine(x - xOffsetFromCenter, y - yOffsetFromCenter, x + xOffsetFromCenter,
                y - yOffsetFromCenter, paint);
        canvas.drawLine(x + xOffsetFromCenter, y - yOffsetFromCenter, x, y + radius, paint);*/
    }

    public void setWidthHeight(int[] widthHeight) {
        this.widthHeight = widthHeight;
        center.set(widthHeight[0] / 2, widthHeight[1] / 2);
        radius = (widthHeight[0] - 40) / 2;
        Log.d(TAG, "setWidthHeight: " + radius);
    }

    private void drawCircumscribedTriangle(float x, float y, int radius, Paint paint, int color) {
        float xOffsetFromCenter = (float) (Math.cos((float) Math.PI / 6) * radius);
        float yOffsetFromCenter = (float) (Math.sin((float) Math.PI / 6) * radius);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        path.reset();
        path.moveTo(x, y - radius);
        path.lineTo(x - xOffsetFromCenter, y + yOffsetFromCenter);
        path.lineTo(x + xOffsetFromCenter, y + yOffsetFromCenter);
        path.moveTo(x + xOffsetFromCenter, y + yOffsetFromCenter);
        path.lineTo(x, y - radius);
        path.close();
        canvas.drawPath(path, paint);

        /*canvas.drawLine(circleCenterX, circleCenterY - radius, circleCenterX + xOffsetFromCenter, circleCenterY + yOffsetFromCenter, paint);
        canvas.drawLine(circleCenterX + xOffsetFromCenter, circleCenterY + yOffsetFromCenter, circleCenterX - xOffsetFromCenter,
                circleCenterY + yOffsetFromCenter, paint);
        canvas.drawLine(circleCenterX - xOffsetFromCenter, circleCenterY + yOffsetFromCenter, circleCenterX, circleCenterY - radius, paint);*/
    }
}
