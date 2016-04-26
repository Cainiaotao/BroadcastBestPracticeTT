package com.example.tantao.broadcastbestpractice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.tantao.broadcastbestpractice.intent.Peson;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SerializableActivity extends AppCompatActivity {

    @InjectView(R.id.serResult)
    TextView serResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serializable);
        ButterKnife.inject(this);

        Peson peson = (Peson) getIntent().getSerializableExtra("Peson_data");
        String id=peson.getId();
        String name=peson.getName();
        String result="result:"+id+name;
        serResult.setText(result);
    }
}
