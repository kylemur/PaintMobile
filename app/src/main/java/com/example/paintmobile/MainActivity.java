package com.example.paintmobile;

import static com.example.paintmobile.R.*;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

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
        private float startX, startY;

        public DrawingView(MainActivity context) {
            super(context);
            setBackgroundColor(Color.WHITE);
        }

        @Override
        protected void onDraw(android.graphics.Canvas canvas) {
            super.onDraw(canvas);
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
                        invalidate();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (tool.equals("Rectangle")) {
                        invalidate();
                    } else if (tool.equals("Oval")) {
                        invalidate();
                    }
                    break;
            }
            return true;
        }
    }
}