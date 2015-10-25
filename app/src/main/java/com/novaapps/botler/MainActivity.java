package com.novaapps.botler;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText searchEdit;
    LinearLayout demo_card;
    RelativeLayout includedLayout;

    TextView search_tv;
    TextView welcome_tv;

    RelativeLayout relativeLayout;

    FloatingActionButton submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        includedLayout = (RelativeLayout) findViewById(R.id.included_layout);
        searchEdit = (EditText) includedLayout.findViewById(R.id.editText);
        searchEdit.setGravity(Gravity.CENTER);

        submitButton = (FloatingActionButton) findViewById(R.id.fab);
        search_tv = (TextView) includedLayout.findViewById(R.id.search_tv);
        welcome_tv = (TextView) includedLayout.findViewById(R.id.welcome_text);

        relativeLayout = (RelativeLayout) includedLayout.findViewById(R.id.rl);
        demo_card = (LinearLayout) includedLayout.findViewById(R.id.demo_card);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Animation animate_right = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_to_right);
                Animation animate_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_to_top);
                submitButton.startAnimation(animate_right);
                relativeLayout.startAnimation(animate_up);
                demo_card.startAnimation(animate_up);
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (searchEdit.isFocused()) {
                Rect outRect = new Rect();
                searchEdit.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    searchEdit.clearFocus();
                    //
                    // Hide keyboard
                    //
                    InputMethodManager imm = (InputMethodManager) findViewById(R.id.mainActivity).getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(findViewById(R.id.mainActivity).getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }

}

