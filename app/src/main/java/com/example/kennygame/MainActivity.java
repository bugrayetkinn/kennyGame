package com.example.kennygame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textView1;
    TextView textView2;
    Runnable runnable;
    Handler handler;
    int sayac = 0;
    int score = 0;
    ImageView ımageView1, ımageView2, ımageView3, ımageView4, ımageView5, ımageView6, ımageView7, ımageView8, ımageView9;
    ImageView[] ımageViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ımageView1 = findViewById(R.id.imageView1);
        ımageView2 = findViewById(R.id.imageView2);
        ımageView3 = findViewById(R.id.imageView3);
        ımageView4 = findViewById(R.id.imageView4);
        ımageView5 = findViewById(R.id.imageView5);
        ımageView6 = findViewById(R.id.imageView6);
        ımageView7 = findViewById(R.id.imageView7);
        ımageView8 = findViewById(R.id.imageView8);
        ımageView9 = findViewById(R.id.imageView9);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

        ımageViews = new ImageView[]{ımageView1, ımageView2, ımageView3, ımageView4, ımageView5, ımageView6, ımageView7, ımageView8, ımageView9};


        new CountDownTimer(11000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) { // Belirtilen süre içinde yapılacak işlem..
                textView1.setText("Time : " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() { // Süre bittikten sonra yapılacak işlem..
                textView1.setText("Time Off");
                handler.removeCallbacks(runnable);// Handler kapanacakk.

                for (ImageView ımageView : ımageViews) { // Kennyler tekrar ınvısıble olacak..
                    ımageView.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);// Tekrar oynamak istediği sorulacak.
                alertDialog.setTitle("Restart");
                alertDialog.setMessage("Are you sure restart game? ");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { // Yes ise uygulama tekrar başlayacak

                        Intent ıntent = getIntent();
                        finish();
                        startActivity(ıntent);
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();

            }
        }.start();
        hideImages();
    }

    public void scoreplusplus(View view) {

        score++;
        textView2.setText("Score : " + score);
    }

    public void hideImages() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                for (ImageView ımageView : ımageViews) {
                    ımageView.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(8);
                ımageViews[i].setVisibility(View.VISIBLE);
                handler.postDelayed(runnable, 700);
            }
        };
        handler.post(runnable);


    }

}
