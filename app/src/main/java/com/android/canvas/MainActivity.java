package com.android.canvas;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    float nX, nY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       ZoomTextView   ed_myEdittext = findViewById(R.id.ed_myEdittext);
        ed_myEdittext.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                int width = v.getLayoutParams().width;
                int height = v.getLayoutParams().height;


                if ((x - width <= 20 && x - width > 0) || (width - x <= 20 && width - x > 0)) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            break;
                        case MotionEvent.ACTION_MOVE:
                            Log.e(">>", "width:" + width + " height:" + height + " x:" + x + " y:" + y);
                            v.getLayoutParams().width = x;
                            v.getLayoutParams().height = y;
                            v.requestLayout();
                            break;
                        case MotionEvent.ACTION_UP:
                            break;
                    }
                }
                return false;
            }
        });
    }
    }


//    protected View.OnTouchListener etTouchListener = new View.OnTouchListener() {
//        @Override
//        public boolean onTouch(View view, MotionEvent event) {
//            float x = event.getX();
//            float y = event.getY();
//            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
//            int top = params.topMargin;
//            int bottom = params.bottomMargin;
//            int left = params.leftMargin;
//            int right = params.rightMargin;
//            if (x == left) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        resize_start(x, y);
//                        Log.e("left edge", "clicked");
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        resize_move(x, y);
//                        params.leftMargin = (int) nX;
//                        params.width = (int) (editNoteViewParent.getWidth() - nX - params.rightMargin);
//                        view.setLayoutParams(params);
//                        Log.e("left edge", "dragged");
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        break;
//                }
//            }
//        }
//
//        private void resize_start(float x, float y) {
//            nX = x;
//            nY = y;
//        }
//
//        private void resize_move(float x, float y) {
//            float dx = Math.abs(x - nX);
//            float dy = Math.abs(y - nY);
//            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
//                nX = x;
//                nY = y;
//            }
//        }
//
//        private void addEditText(float x, float y) {
//            RelativeLayout.LayoutParams editTextParams = new RelativeLayout.LayoutParams(200, 200);
//            editTextParams.leftMargin = (int) x - (editTextParams.width / 2);
//            editTextParams.topMargin = (int) y - (editTextParams.height / 2);
//
//            mEditText = new EditText(this);
//            mEditText.setHint("Enter note");
//            mEditText.setBackgroundResource(R.drawable.edittext_shape);
//            editNoteViewParent.addView(mEditText, editTextParams);
//            mEditText.setOnTouchListener(etTouchListener);
//            editNoteViewParent.setOnDragListener(etDragListener);
//            editTextCreated = true;
//        }
//    }