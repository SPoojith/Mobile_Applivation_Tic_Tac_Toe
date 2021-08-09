package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class movingbanner extends AppCompatActivity {
    @Override
    public void setTurnScreenOn(boolean turnScreenOn) {
        super.setTurnScreenOn(false);
    }

    public void mstrt(View view){
        TextView t=(TextView)findViewById(R.id.tvscroll);
        t.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        t.setSelected(true);
    }

    public void mstop(View view){
        TextView t=(TextView)findViewById(R.id.tvscroll);
        t.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        t.setSelected(false);
    }

    public void bhome(View view){
        Intent intent =new Intent(movingbanner.this,MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movingbanner);


        TextView t=(TextView)findViewById(R.id.tvscroll);
        t.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        t.setSelected(true);
    }
}