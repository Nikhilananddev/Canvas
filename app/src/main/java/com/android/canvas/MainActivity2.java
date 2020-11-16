package com.android.canvas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.androidessence.pinchzoomtextview.PinchZoomTextView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    float dX;
    float dY;
    int lastAction;
    LinearLayout floatingLayout;
    PinchZoomTextView textView;
    Button button1,button2,button3, button4,button5;
//    TextView ed_myEdittext;
//    TextView textView;

    LinearLayout shareLinearLayout, uploadLinearLayout, copyLinearLayout;
    BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        ed_myEdittext = findViewById(R.id.ed_myEdittext);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       textView = findViewById(R.id.textView);
       button1=findViewById(R.id.Strong);
        button2=findViewById(R.id.bold);
        button3=findViewById(R.id.Italic);
        button4=findViewById(R.id.increase);
        button5=findViewById(R.id.decrease);

        NumberPicker  numberPickerPriority = findViewById(R.id.number_picker_priority);
        numberPickerPriority.setMinValue(0);
        numberPickerPriority.setMaxValue(2);
        numberPickerPriority.setDisplayedValues( new String[] { "sans-serif", "monospace", "cursive" } );
textView.setOnTouchListener(this);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(Html.fromHtml("<strong>"+textView.getText().toString()+"</strong>"));// set style and color                  break;

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(Html.fromHtml("<b>"+textView.getText().toString()+"</b>"));// set style and color                  break;

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(Html.fromHtml("<i>"+textView.getText().toString()+"</i>"));// set style and color                  break;

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setTextSize(0,    textView.getTextSize() + 2.0f);

            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setTextSize(0,  textView.getTextSize() - 2.0f);

            }
        });


        if(numberPickerPriority!=null)
        {
            createBottomSheetDialog();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.increase:
//                textView.setTextSize(textView.getTextSize()+1);
//                break;
//            case R.id.decrease:
//                textView.setTextSize(textView.getTextSize()-1);
//                break;
//            case R.id.normal:
//               textView.setText("bold");// set style and color                  break;
//               break;
//            case R.id.button2:
//                textView.setText(Html.fromHtml("<font color=red><b>"+textView.getText().toString()+"</b></font>"));// set style and color                  break;
//                break;
//            case R.id.button3:
//                textView.setText(Html.fromHtml("<font color=red><i>"+textView.getText().toString()+"</i></font>"));// set style and color                  break;
//                break;
//            case R.id.normal:
////                textView.setText("SHARE");
//                bottomSheetDialog.dismiss();
//                break;
//            case R.id.uploadLinearLayout:
////                textView.setText("UPLOAD");
//                bottomSheetDialog.dismiss();
//                break;

            case R.id.copyLinearLayout:
//                textView.
                ClipboardManager clipboard = (ClipboardManager) MainActivity2.this.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("text", textView.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(MainActivity2.this, "copied", Toast.LENGTH_SHORT).show();


                bottomSheetDialog.dismiss();
                break;

        }

    }

    private void createBottomSheetDialog() {
        if (bottomSheetDialog == null) {
         View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet, null);
//            shareLinearLayout = view.findViewById(R.id.shareLinearLayout);
//            uploadLinearLayout = view.findViewById(R.id.uploadLinearLayout);
            copyLinearLayout = view.findViewById(R.id.copyLinearLayout);
//
//            shareLinearLayout.setOnClickListener(this);
//            uploadLinearLayout.setOnClickListener(this);
            copyLinearLayout.setOnClickListener(this);

            bottomSheetDialog = new BottomSheetDialog(this);
            bottomSheetDialog.setContentView(view);
        }
    }

    public void showDialog(View view) {
        bottomSheetDialog.show();
    }

@Override
    public boolean onTouch(View view, MotionEvent event) {
        //        ed_myEdittext.setWidth((int) event.getX());
//        ed_myEdittext.setHeight((int) event.getY());
        switch (event.getActionMasked()) {
////            case MotionEvent.ACTION_DOWN:
//            case MotionEvent.ACTION_POINTER_DOWN:
//               textView .setTextSize(40);
//                break;
//
//            case MotionEvent.ACTION_CANCEL:
////            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_POINTER_UP:
//
//                textView .setTextSize(20);
//                break;
////                case  MotionEvent.
            case MotionEvent.ACTION_DOWN:
                dX = view.getX() - event.getRawX();
                dY = view.getY() - event.getRawY();
                lastAction = MotionEvent.ACTION_DOWN;
                break;

            case MotionEvent.ACTION_MOVE:
                view.setY(event.getRawY() + dY);
                view.setX(event.getRawX() + dX);
                lastAction = MotionEvent.ACTION_MOVE;
                break;

            case MotionEvent.ACTION_UP:

                if (lastAction == MotionEvent.ACTION_DOWN)
                    Toast.makeText(MainActivity2.this, "Clicked!", Toast.LENGTH_SHORT).show();
                break;

            default:
                return false;
        }
        return true;
    }


    //    public boolean onTouch(View view, MotionEvent event) {
//
////        ed_myEdittext.setWidth((int) event.getX());
////        ed_myEdittext.setHeight((int) event.getY());
//        switch (event.getActionMasked()) {
////            case MotionEvent.ACTION_DOWN:
//            case MotionEvent.ACTION_POINTER_DOWN:
//                ed_myEdittext .setTextSize(40);
//                break;
//
//            case MotionEvent.ACTION_CANCEL:
////            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_POINTER_UP:
//
//                ed_myEdittext.setTextSize(20);
//                break;
////                case  MotionEvent.
//            case MotionEvent.ACTION_DOWN:
//                dX = view.getX() - event.getRawX();
//                dY = view.getY() - event.getRawY();
//                lastAction = MotionEvent.ACTION_DOWN;
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//                view.setY(event.getRawY() + dY);
//                view.setX(event.getRawX() + dX);
//                lastAction = MotionEvent.ACTION_MOVE;
//                break;
//
//            case MotionEvent.ACTION_UP:
//
//                if (lastAction == MotionEvent.ACTION_DOWN)
//                    Toast.makeText(MainActivity2.this, "Clicked!", Toast.LENGTH_SHORT).show();
//                break;
//
//            default:
//                return false;
//        }
//        return true;
//    }


//    @Override
//    public boolean onTouchEvent (View view, MotionEvent event){
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                dX = view.getX() - event.getRawX();
//                dY = view.getY() - event.getRawY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                view.animate()
//                        .x(event.getRawX() + dX - (view.getWidth() / 2))
//                        .y(event.getRawY() + dY - (view.getHeight() / 2))
//                        .setDuration(0)
//                        .start();
//                break;
//            default:
//                return false;
//        }
//        return true;
//    }
}
