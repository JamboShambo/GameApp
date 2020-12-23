package jamie.itsligo.gameapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // declaring my variables
    private final int BLUE = 1;
    private final int RED = 2;
    private final int YELLOW = 3;
    private final int GREEN = 4;

    TextView tvScore,tvRound;
    Button bRed, bBlue, bYellow, bGreen, fb;
    public int n = 0, score;
    int[] gameSequence = new int[120];
    int arrayIndex = 0;

    //timer used for each of my rounds timing the pause between flash
    CountDownTimer ctRound1 = new CountDownTimer(6000,  1500) {

        public void onTick(long millisUntilFinished) {
            flashrandomBtn();
        }

        public void onFinish() {
            for (int i = 0; i< arrayIndex; i++)
            {
                Log.d("game sequence", String.valueOf(gameSequence[i]));
            }

            Intent gameSequenceIntent = new Intent(MainActivity.this, PlayScreen.class);

            gameSequenceIntent.putExtra("gameSequence", gameSequence);
            gameSequenceIntent.putExtra("arrayIndex", arrayIndex);
            gameSequenceIntent.putExtra("scoreNowToPickAgain", score);
            Log.d("score now sent to back to play", String.valueOf(score));


            startActivity(gameSequenceIntent);
        }
    };
    CountDownTimer ctRound2 = new CountDownTimer(9000,  1500) {

        public void onTick(long millisUntilFinished) {
            flashrandomBtn();
        }

        public void onFinish() {

            for (int i = 0; i< arrayIndex; i++)
            {
                Log.d("game sequence", String.valueOf(gameSequence[i]));
            }

            Intent gameSequenceIntent = new Intent(MainActivity.this, PlayScreen.class);

            gameSequenceIntent.putExtra("gameSequence", gameSequence);
            gameSequenceIntent.putExtra("arrayIndex", arrayIndex);
            gameSequenceIntent.putExtra("scoreNowToPickAgain", score);
            Log.d("score now sent to back to play", String.valueOf(score));

            startActivity(gameSequenceIntent);
        }
    };
    CountDownTimer ctRound3 = new CountDownTimer(12000,  1500) {

        public void onTick(long millisUntilFinished) {
            flashrandomBtn();
        }

        public void onFinish() {
            for (int i = 0; i< arrayIndex; i++)
            {
                Log.d("game sequence", String.valueOf(gameSequence[i]));
            }

            Intent gameSequenceIntent = new Intent(MainActivity.this, PlayScreen.class);

            gameSequenceIntent.putExtra("gameSequence", gameSequence);
            gameSequenceIntent.putExtra("arrayIndex", arrayIndex);
            gameSequenceIntent.putExtra("scoreNowToPickAgain", score);
            Log.d("score now sent to back to play", String.valueOf(score));

            startActivity(gameSequenceIntent);
        }
    };
    CountDownTimer ctRound4 = new CountDownTimer(15000,  1500) {

        public void onTick(long millisUntilFinished) {
            flashrandomBtn();
        }

        public void onFinish() {

            for (int i = 0; i< arrayIndex; i++)
            {
                Log.d("game sequence", String.valueOf(gameSequence[i]));
            }

            Intent gameSequenceIntent = new Intent(MainActivity.this, PlayScreen.class);

            gameSequenceIntent.putExtra("gameSequence", gameSequence);
            gameSequenceIntent.putExtra("arrayIndex", arrayIndex);
            gameSequenceIntent.putExtra("scoreNowToPickAgain", score);
            Log.d("score now sent to back to play", String.valueOf(score));

            startActivity(gameSequenceIntent);
        }
    };
    CountDownTimer ctRound5 = new CountDownTimer(18000,  1500) {

        public void onTick(long millisUntilFinished) {
            flashrandomBtn();
        }

        public void onFinish() {

            for (int i = 0; i< arrayIndex; i++)
            {
                Log.d("game sequence", String.valueOf(gameSequence[i]));
            }

            Intent gameSequenceIntent = new Intent(MainActivity.this, PlayScreen.class);

            gameSequenceIntent.putExtra("gameSequence", gameSequence);
            gameSequenceIntent.putExtra("arrayIndex", arrayIndex);
            gameSequenceIntent.putExtra("scoreNowToPickAgain", score);
            Log.d("score now sent to back to play", String.valueOf(score));

            startActivity(gameSequenceIntent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //finding buttons and textViews
        bRed = findViewById(R.id.btnEast);
        bBlue = findViewById(R.id.btnNorth);
        bYellow = findViewById(R.id.btnSouth);
        bGreen = findViewById(R.id.btnWest);
        tvScore = findViewById(R.id.tvScore);
        tvRound = findViewById(R.id.tvRound);


        // Intents code
        score = getIntent().getIntExtra("restartScore",score);
        score = getIntent().getIntExtra("scoreToNewRound",score);
        Log.d("score now sent to main", String.valueOf(score));

        //displaying data to the user
        String scoreToDisplay = String.valueOf(score);
        String wordScore = "Score:"+scoreToDisplay;
        String wordRound;
        tvScore.setText(wordScore);

        //if statement to decide what round to show the user
        if (score == 0)
        {
            wordRound = "Round:1";
            tvRound.setText(wordRound);
        }
        else if (score == 4)
        {
            wordRound = "Round:2";
            tvRound.setText(wordRound);
        }
        else if (score == 10)
        {
            wordRound = "Round:3";
            tvRound.setText(wordRound);
        }
        else if (score == 18)
        {
            wordRound = "Round:4";
            tvRound.setText(wordRound);
        }
        else if (score == 28)
        {
            wordRound = "Round:5";
            tvRound.setText(wordRound);
        }
    }

    //when pressed play it will start timer and the sequence
    public void doPlay(View view) {
        if (score == 0)
        {
            ctRound1.start();
        }
        else if (score == 4)
        {
            ctRound2.start();
        }
        else if (score == 10)
        {
            ctRound3.start();
        }
        else if (score == 18)
        {
            ctRound4.start();
        }
        else if (score == 28)
        {
            ctRound5.start();
        }
    }

    //called each time there is a tick of a countdowntimer and picks a random colour
    public void flashrandomBtn() {
        n = getRandom();
        switch (n) {
            case 1:
                flashButton(bBlue);
                gameSequence[arrayIndex++] = BLUE;
                break;
            case 2:
                flashButton(bRed);
                gameSequence[arrayIndex++] = RED;
                break;
            case 3:
                flashButton(bYellow);
                gameSequence[arrayIndex++] = YELLOW;
                break;
            case 4:
                flashButton(bGreen);
                gameSequence[arrayIndex++] = GREEN;
                break;
            default:
                break;
        }   // end switch
    }

    // return a number between 1 and 4
    private int getRandom() {
        return ((int) ((Math.random() * 4) + 1));
    }

    //flashes the button passed to it
    private void flashButton(Button button) {
        fb = button;
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {

                fb.setPressed(true);
                fb.invalidate();
                fb.performClick();
                Handler handler1 = new Handler();
                Runnable r1 = new Runnable() {
                    public void run() {
                        fb.setPressed(false);
                        fb.invalidate();
                    }
                };
                handler1.postDelayed(r1, 600);

            } // end runnable
        };
        handler.postDelayed(r, 600);
    }

}
