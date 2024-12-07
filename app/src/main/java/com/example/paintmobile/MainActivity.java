package com.example.paintmobile;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends Activity {

    private DrawingView drawingView;
    private Paint paint;
    private String tool = "Brush";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawingView = new DrawingView(this);
        setContentView(drawingView);

        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE); // Set default paint style to stroke
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.brush_tool:
                tool = "Brush";
                paint.setColor(Color.BLACK); // Reset paint color if previously set to white (eraser)
                return true;
            case R.id.rectangle_tool:
                tool = "Rectangle";
                return true;
            case R.id.oval_tool:
                tool = "Oval";
                return true;
            case R.id.eraser_tool:
                tool = "Eraser";
                paint.setColor(Color.WHITE);
                return true;
            case R.id.red_color:
                paint.setColor(Color.RED);
                return true;
            case R.id.green_color:
                paint.setColor(Color.GREEN);
                return true;
            case R.id.blue_color:
                paint.setColor(Color.BLUE);
                return true;
            case R.id.black_color:
                paint.setColor(Color.BLACK);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class DrawingView extends View {
        private float startX, startY, endX, endY;
        private Canvas drawCanvas;
        private Paint canvasPaint;

        public DrawingView(MainActivity context) {
            super(context);
            setBackgroundColor(Color.WHITE);
            canvasPaint = new Paint(Paint.DITHER_FLAG); // Smooth canvas drawing
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            if (tool.equals("Brush")) {
                canvas.drawLine(startX, startY, endX, endY, paint);
                startX = endX; // Update start coordinates to create continuous drawing effect
                startY = endY;
            } else if (tool.equals("Rectangle")) {
                canvas.drawRect(startX, startY, endX, endY, paint);
            } else if (tool.equals("Oval")) {
                canvas.drawOval(startX, startY, endX, endY, paint);
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startX = x;
                    startY = y;
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (tool.equals("Brush")) {
                        endX = x;
                        endY = y;
                        invalidate();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    endX = x;
                    endY = y;
                    if (tool.equals("Rectangle") || tool.equals("Oval")) {
                        invalidate();
                    }
                    break;
            }
            return true;
        }
    }
}
