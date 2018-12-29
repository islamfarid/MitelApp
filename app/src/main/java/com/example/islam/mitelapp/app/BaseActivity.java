package com.example.islam.mitelapp.app;


import android.os.Bundle;

import com.example.islam.mitelapp.R;

import androidx.appcompat.app.AppCompatActivity;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by "Islam Farid" on 10/18/2018.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
    }
    @Override
    protected void onDestroy() {
        ((MitelAPP) getApplication()).mustDie(this);
        super.onDestroy();
    }
}
