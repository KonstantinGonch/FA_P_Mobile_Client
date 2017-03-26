package com.raxsoft.univer_project_client_side;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MiniRevealerActivity extends AppCompatActivity {

    private TextView regnumTv, regDateTv, posTv, statTv, queryTv;
    private Activity thisActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_revealer);

        thisActivity = this;

        regnumTv = (TextView) findViewById(R.id.regnum_tv);
        regDateTv = (TextView) findViewById(R.id.regdate_tv);
        posTv = (TextView) findViewById(R.id.pos_tv);
        statTv = (TextView) findViewById(R.id.state_tv);
        queryTv = (TextView) findViewById(R.id.query_tv);


        regnumTv.setText(DataContainer.cFullNum);
        regDateTv.setText(DataContainer.cDateReg);
        queryTv.setText(DataContainer.cQuery);
        posTv.setText (DataContainer.cPos);
        statTv.setText(DataContainer.cCurState);

    }
}
