package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import java.io.InvalidClassException;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    int count = 0;
    int active = 0;
    boolean activegame = true;
    int[] position = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winpos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int time = 0;
    Button startBtn;
    TextView counterVal;

    private static final String TAG = "thread";

    Handler mainHandler = new Handler();

    void startThread(){
        NewThread nObj = new NewThread();
        nObj.start();
    }
    public void opentask(View view){
        Intent intent =new Intent(MainActivity.this,movingbanner.class);
        startActivity(intent);
    }
    public void click(View view) {
            ImageView iv = (ImageView) view;
            int tagp = Integer.parseInt(iv.getTag().toString());

            if (position[tagp] == 2 && activegame == true) {
                iv.animate().alpha(1).setDuration(500);
                Log.i("Tag", iv.getTag().toString());
                position[tagp] = active;
                if (active == 0) {
                    iv.setImageResource(R.drawable.ogmark);
                    active = 1;
                    if (activegame != false) {
                        Toast.makeText(this, "player X turn", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    iv.setImageResource(R.drawable.xgmark);
                    active = 0;
                    if (activegame != false) {
                        Toast.makeText(this, "player O turn", Toast.LENGTH_SHORT).show();
                    }
                }
                String winner = "";
                for (int[] winpos : winpos) {
                    if (position[winpos[0]] == position[winpos[1]] && position[winpos[1]] == position[winpos[2]] && position[winpos[0]] != 2) {

                        activegame = false;
                        if (active == 1) {
                            winner = "player O won in " + time + " seconds";
                            gamefinish(winner);
                        } else if (active == 0) {
                            winner = "player X won in " + time + " seconds";
                            gamefinish(winner);
                        }
                    }else if(istied()){
                        activegame = false;
                            winner = " match is draw";
                            gamefinish(winner);
                    }
                }
            }
}


    public void gamefinish(String winner){
        TextView Tvr=(TextView)findViewById(R.id.tvresult);

        Button btnpa = (Button) findViewById(R.id.btnpa);

        Tvr.setVisibility(View.VISIBLE);
        btnpa.setVisibility(View.VISIBLE);

        Tvr.setText(winner);

    }

   public void pa (View view){
        count++;
       Toast.makeText(this, "click only in the grid part", Toast.LENGTH_LONG).show();

        TextView Tvr=(TextView)findViewById(R.id.tvresult);
        Button btnpa = (Button) findViewById(R.id.btnpa);
        Tvr.setVisibility(View.INVISIBLE);
        btnpa.setVisibility(View.INVISIBLE);
    if(count==1) {
        ImageView iv3 = (ImageView) findViewById(R.id.imageView3);
        iv3.setVisibility(View.VISIBLE);

        ImageView iv1 = (ImageView) findViewById(R.id.imageView1);
        iv1.setVisibility(View.INVISIBLE);
    }else if(count==2){
        count=0;
        ImageView iv3 = (ImageView) findViewById(R.id.imageView3);
        iv3.setVisibility(View.INVISIBLE);

        ImageView iv1 = (ImageView) findViewById(R.id.imageView1);
        iv1.setVisibility(View.VISIBLE);
    }
       androidx.gridlayout.widget.GridLayout g = (androidx.gridlayout.widget.GridLayout) findViewById(R.id.gridlayout1);

        for(int i=0;i<g.getChildCount();i++)
        {
            ImageView iv = (ImageView) g.getChildAt(i);
            iv.animate().alpha(0).setDuration(500);
            iv.setImageDrawable(null);
        }
        activegame = true;
       time=0;
       startThread();
       for (int i=0;i<position.length;i++) {
            position[i] = 2;
        }
    }


    public boolean istied() {
        for(int i=0;i< position.length;i++)
        {
            if(position[i] == 2 || ((position[0]==position[4] && position[4]==position[8] &&position[0]==0) || (position[0]==position[4] && position[4]==position[8] &&position[0]==1)))
            {
                return false;
            }
        }
        return true;
    }
    class NewThread extends Thread{
        @Override
        public void run() {

            while(activegame){

                time++;
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        counterVal.setText(String.valueOf(time));
                    }
                });

                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counterVal = findViewById(R.id.timer);
        time = 0;
        startThread();
        Toast.makeText(this, "click only in the grid part", Toast.LENGTH_LONG).show();


    }


}