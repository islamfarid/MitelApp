package com.example.islam.mitelapp.app;


import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by "Islam Farid" on 10/18/2018.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onDestroy() {
        ((MitelAPP) getApplication()).mustDie(this);
        super.onDestroy();
    }
}
