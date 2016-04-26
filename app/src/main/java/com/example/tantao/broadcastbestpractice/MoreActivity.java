package com.example.tantao.broadcastbestpractice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tantao.broadcastbestpractice.base.BaseActivity;
import com.example.tantao.broadcastbestpractice.intent.Peson;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MoreActivity extends BaseActivity {

    @InjectView(R.id.xmlBtn)
    Button xmlBtn;
    @InjectView(R.id.josnBtn)
    Button josnBtn;
    @InjectView(R.id.pid)
    TextView pid;
    @InjectView(R.id.pname)
    TextView pname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        ButterKnife.inject(this);


    }


    @OnClick({R.id.xmlBtn, R.id.josnBtn,R.id.lbsBtn,R.id.SerializableBtn,R.id.ParcelableBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.xmlBtn:
                startActivity(new Intent(this,XmlpullActivity.class));
                break;
            case R.id.josnBtn:
                startActivity(new Intent(this,JosngosnActivity.class));
                break;
            case R.id.lbsBtn:
                startActivity(new Intent(this,LoactionMapActivity.class));
                break;
            case R.id.SerializableBtn:
                Peson peson=new Peson();
                peson.setId(pid.getText().toString());
                peson.setName(pname.getText().toString());
                Intent intent=new Intent(this, SerializableActivity.class);
                intent.putExtra("Peson_data",peson);
                startActivity(intent);
                break;
            case R.id.ParcelableBtn:
                break;
        }
    }
}
