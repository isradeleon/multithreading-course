package com.techyourchance.multithreading.exercises.exercise2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.techyourchance.multithreading.R;
import com.techyourchance.multithreading.common.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.concurrent.atomic.AtomicBoolean;

public class Exercise2Fragment extends BaseFragment {

    public static Fragment newInstance() {
        return new Exercise2Fragment();
    }

    private AtomicBoolean continueExecution = new AtomicBoolean(true);

    private byte[] mDummyData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDummyData = new byte[50 * 1000 * 1000];
        return inflater.inflate(R.layout.fragment_exercise_2, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        countScreenTime();
    }

    @Override
    public void onStop() {
        super.onStop();
        continueExecution.set(false);
    }

    @Override
    protected String getScreenTitle() {
        return "Exercise 2";
    }

    private void countScreenTime() {
        new Thread(() -> {
            int screenTimeSeconds = 0;
            while (continueExecution.get()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
                screenTimeSeconds++;
                Log.d("Exercise 2", "screen time: " + screenTimeSeconds + "s");
            }
        }).start();
    }
}
