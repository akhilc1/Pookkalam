package com.qburst.akhil.pookkalam;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.widget.ImageView;

/**
 * Created by guru on 29-08-17.
 */

public class CanvasView extends ImageView {
    Paint paint;
    Context context;
    Point center;
    int widthHeight[];

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
        canvas.drawCircle(center.x, center.y, center.x - 20, paint);
    }

    public void setWidthHeight(int[] widthHeight) {
        this.widthHeight = widthHeight;
        center.set(widthHeight[0] / 2, widthHeight[1] / 2);
    }
}
