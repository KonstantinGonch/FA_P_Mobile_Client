package com.raxsoft.univer_project_client_side;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class StateRevealerActivity extends AppCompatActivity {

    private Activity thisActivity;
    private EditText fcodeEdit;
    private EditText scodeEdit;
    private EditText tcodeEdit;
    private Button confButton;
    private ProgressBar pbar;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_revealer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        handler = new Handler(){
            @Override
            public void handleMessage(Message message)
            {
                pbar.setVisibility(View.GONE);
                if (message.what==1) {
                    if (DataContainer.cDateReg.equals(DataContainer.cFullNum)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(thisActivity);
                        builder.setTitle(R.string.error);
                        builder.setMessage(DataContainer.cDateReg);
                        builder.setPositiveButton("OK", null);
                        builder.show();
                    } else {
                        Intent intent = new Intent(thisActivity, MiniRevealerActivity.class);
                        startActivity(intent);
                    }
                }
                else if (message.what==2)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(thisActivity);
                    builder.setTitle(R.string.error);
                    builder.setTitle(R.string.errorexpl);
                    builder.setPositiveButton("OK", null);
                    builder.show();
                }
            }
        };

        thisActivity = this;
        fcodeEdit = (EditText) findViewById(R.id.fcode_et);
        scodeEdit = (EditText) findViewById(R.id.scode_et);
        tcodeEdit = (EditText) findViewById(R.id.tcode_et);
        confButton = (Button) findViewById(R.id.confirm_button);
        pbar = (ProgressBar)findViewById(R.id.progrbar);
        pbar.setVisibility(View.GONE);

        this.setTitle(getResources().getString(R.string.data_insert));

        confButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               final String fcode = fcodeEdit.getText().toString();
                final String scode = scodeEdit.getText().toString();
                final String tcode = tcodeEdit.getText().toString();
                boolean normal = true;
                if (fcode.equals("")||fcode.length()!=6){
                    Toast.makeText(thisActivity, getResources().getString(R.string.fcode_err), Toast.LENGTH_SHORT).show();
                    normal = false;}
                if (scode.equals("")||scode.length()!=2){
                    Toast.makeText(thisActivity, getResources().getString(R.string.scode_err), Toast.LENGTH_SHORT).show();
                    normal = false;}
                if (tcode.equals("")||tcode.length()!=5){
                    Toast.makeText(thisActivity, getResources().getString(R.string.tcode_err), Toast.LENGTH_SHORT).show();
                    normal = false;}
                if(normal)
                {
                    pbar.setVisibility(View.VISIBLE);
                    DataContainer.cFnum=fcode;
                    DataContainer.cSnum=scode;
                    DataContainer.cTnum=tcode;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try
                            {
                            DataContainer.cFullNum = Processor.GetParameter(DataContainer.cFnum, DataContainer.cSnum, DataContainer.cTnum, "00");
                            DataContainer.cDateReg = Processor.GetParameter(DataContainer.cFnum, DataContainer.cSnum, DataContainer.cTnum, "01");
                            if (Processor.GetParameter(DataContainer.cFnum, DataContainer.cSnum, DataContainer.cTnum, "04").equals("-1"))
                            {
                                if (Processor.GetParameter(DataContainer.cFnum, DataContainer.cSnum, DataContainer.cTnum, "10").equals("-1"))
                                {
                                    if (Processor.GetParameter(DataContainer.cFnum, DataContainer.cSnum, DataContainer.cTnum, "11").equals("-1"))
                                    {
                                        if (Processor.GetParameter(DataContainer.cFnum, DataContainer.cSnum, DataContainer.cTnum, "12").equals("-1"))
                                        {
                                            DataContainer.cQuery = "В очереди: ";
                                            DataContainer.cPos = "Нет";
                                        }
                                        else
                                        {
                                            DataContainer.cQuery = "В очереди на получение марок: ";
                                            DataContainer.cPos = Processor.GetParameter(DataContainer.cFnum, DataContainer.cSnum, DataContainer.cTnum, "12");
                                        }
                                    }
                                    else
                                    {
                                        DataContainer.cPos = Processor.GetParameter(DataContainer.cFnum, DataContainer.cSnum, DataContainer.cTnum, "11");
                                        DataContainer.cQuery = "В очереди на принятие обеспечения: ";
                                    }
                                }
                                else
                                {
                                    DataContainer.cPos = Processor.GetParameter(DataContainer.cFnum, DataContainer.cSnum, DataContainer.cTnum, "10");
                                    DataContainer.cQuery = "В очереди на принятие обязательства: ";
                                }
                            }
                            else
                            {
                                DataContainer.cQuery = "В очереди на изготовление марок: ";
                                DataContainer.cPos = Processor.GetParameter(DataContainer.cFnum, DataContainer.cSnum, DataContainer.cTnum, "04");
                            }
                            DataContainer.cCurState = Processor.GetParameter(DataContainer.cFnum, DataContainer.cSnum, DataContainer.cTnum, "09");
                                handler.sendEmptyMessage(1);}
                            catch (Exception ex)
                            {
                                handler.sendEmptyMessage(2);
                            }
                        }
                    }).start();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_avail_action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about_app) {
            Intent intent = new Intent(thisActivity, InfoActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
