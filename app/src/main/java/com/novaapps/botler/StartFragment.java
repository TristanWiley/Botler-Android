package com.novaapps.botler;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StartFragment extends Fragment {
    EditText searchEdit;
    LinearLayout demo_card;

    TextView search_tv;
    TextView welcome_tv;

    RelativeLayout relativeLayout;

    FloatingActionButton submitButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle bundle) {
        super.onCreateView(inflater, null, bundle);
        setHasOptionsMenu(true);

        //Inflate the layout
        return inflater.inflate(R.layout.fragment_start, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        includedLayout = (RelativeLayout) getActivity().findViewById(R.id.included_layout);
        searchEdit = (EditText) getActivity().findViewById(R.id.editText);
//        searchEdit.setGravity(Gravity.CENTER);

        submitButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        search_tv = (TextView) getActivity().findViewById(R.id.search_tv);
        welcome_tv = (TextView) getActivity().findViewById(R.id.welcome_text);

        relativeLayout = (RelativeLayout) getActivity().findViewById(R.id.rl);
        demo_card = (LinearLayout) getActivity().findViewById(R.id.demo_card);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animate_up = AnimationUtils.loadAnimation(getActivity(), R.anim.move_to_top);
                submitButton.startAnimation(animate_up);
                relativeLayout.startAnimation(animate_up);
                demo_card.startAnimation(animate_up);

                animate_up.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Bundle bundle = new Bundle();
                        bundle.putString("project_name", searchEdit.getText().toString());

                        LoadingFragment loadingFragment = new LoadingFragment();
                        loadingFragment.setArguments(bundle);

                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                                .replace(R.id.container, loadingFragment)
                                .commit();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }
        });

        LinearLayout touchInterceptor = (LinearLayout) getActivity().findViewById(R.id.mainActivity);
        touchInterceptor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (searchEdit.isFocused()) {
                        Rect outRect = new Rect();
                        searchEdit.getGlobalVisibleRect(outRect);
                        if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                            searchEdit.clearFocus();
                            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        }
                    }
                }
                return false;
            }
        });


    }


}

