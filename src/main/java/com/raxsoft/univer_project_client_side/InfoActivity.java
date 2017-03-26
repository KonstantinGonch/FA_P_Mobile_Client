package com.raxsoft.univer_project_client_side;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    private Activity thisActivity;
    private TextView titleTv;
    private TextView messageTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        thisActivity = this;

        this.setTitle(getResources().getString(R.string.info));

    }
}
