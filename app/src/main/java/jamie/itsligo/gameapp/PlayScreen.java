package jamie.itsligo.gameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PlayScreen extends AppCompatActivity implements SensorEventListener {
    ////////////////////// game varibles///////////////////
    int[] gameSequence = new int[120];
    public int arrayIndex, btnPickedByUser, score, userSequence, flashPickedByUser;
    public int round1Max = 4,round2Max = 10, round3Max = 18,round4Max = 28, round5Max = 40;
    Handler handlerUI = new Handler();

    Button btnNorth,btnEast,btnSouth,btnWest,fb;
    TextView tvScoreForUser, tvRound;
    String wordRound;

    ////////////////////////// accelormater varibles/////////////////////////////////////
    // experimental values for hi and lo magnitude limits
    private final double NORTH_MOVE_FORWARD = 9.0;     // upper mag limit
    private final double NORTH_MOVE_BACKWARD = 7.0;      // lower mag limit

    private final double SOUTH_MOVE_FORWARD = 7.0;     // upper mag limit
    private final double SOUTH_MOVE_BACKWARD = 9.0; // lower mag limit

    private final double WEST_MOVE_FORWARD = -1;     // upper mag limit
    private final double WEST_MOVE_BACKWARD = 0; // lower mag limit

    private final double EAST_MOVE_FORWARD = 1;     // upper mag limit
    private final double EAST_MOVE_BACKWARD = 0; // lower mag limit

    boolean highLimitNorth = false, highLimitEast = false,highLimitSouth = false,highLimitWest = false; // detect high limit
    private SensorManager mSensorManager;
    private Sensor mSensor;


        @Override
    protected void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen_land);


        //finding my btns/textviews
        btnNorth = findViewById(R.id.btnNorth);
        btnEast = findViewById(R.id.btnEast);
        btnSouth = findViewById(R.id.btnSouth);
        btnWest = findViewById(R.id.btnWest);
        tvScoreForUser = findViewById(R.id.tvScoreForUser);
        tvRound = findViewById(R.id.tvRound);


        // Intents code
        gameSequence = getIntent().getIntArrayExtra("gameSequence");
        arrayIndex = getIntent().getIntExtra("arrayIndex",arrayIndex);

        score = getIntent().getIntExtra("scoreNowToPickAgain",score);
        Log.d("Score in play screen ", String.valueOf(score));
        tvScoreForUser.setText("Score:" + score);

        // sensor code
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        // LOG code
        for (int i = 0; i< arrayIndex; i++)
        {
            Log.d("game sequence in play screen", String.valueOf(gameSequence[i]));

        }

        // if statement displaying to the user the round
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

    //all btns set to this, when clcked it will check whihc was clicked and give the btn pressed an associated value
    public void doClickAllBtn(View view) {

        switch (view.getId()) {
                    case R.id.btnNorth:
                        Log.d("clicked button 1", String.valueOf(gameSequence[userSequence]));
                        btnPickedByUser = 1;
                        break;

                    case R.id.btnEast:
                        Log.d("clicked button 2", String.valueOf(gameSequence[userSequence]));
                        btnPickedByUser = 2;
                        break;

                    case R.id.btnSouth:
                        Log.d("clicked button 3", String.valueOf(gameSequence[userSequence]));
                        btnPickedByUser = 3;
                        break;

                    case R.id.btnWest:
                        Log.d("clicked button 4", String.valueOf(gameSequence[userSequence]));
                        btnPickedByUser = 4;
                        break;
                }
                btnPickedByUserChecker(view);
                userSequence++;
    }

    // when this is called it will check that the button pressed by the user is the correct button pressed from the sequence
    //IF not will bring to a gameover
    public void btnPickedByUserChecker(View view) {

        if (btnPickedByUser == gameSequence[userSequence]) {
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            score++;
            tvScoreForUser.setText("Score " + score);
        } else {

            Toast.makeText(this,"Incorrect",Toast.LENGTH_SHORT).show();

            Intent gameOverScreenIntent = new Intent(view.getContext(), GameOverScreen.class);

            gameOverScreenIntent.putExtra("score", score);

            startActivity(gameOverScreenIntent);

            score = 0;
            //finish();
        }

        //series of ifs to decide what page to bring the user to
        if (score == round1Max)
        {
            Intent mainActivityIntent = new Intent(view.getContext(), MainActivity.class);

            Log.d("score before being sent back", String.valueOf(score));

            mainActivityIntent.putExtra("scoreToNewRound", score);

            startActivity(mainActivityIntent);
        }
        else if (score == round2Max)
        {
            Intent mainActivityIntent = new Intent(view.getContext(), MainActivity.class);

            Log.d("score before being sent back", String.valueOf(score));

            mainActivityIntent.putExtra("scoreToNewRound", score);

            startActivity(mainActivityIntent);

            /*Intent highScoreIntent = new Intent(view.getContext(), HiScoreScreen.class);

            Log.d("score before being sent back", String.valueOf(score));

            highScoreIntent.putExtra("scoreBeatenGame", score);

            startActivity(highScoreIntent);*/
        }
        else if (score == round3Max)
        {
            Intent mainActivityIntent = new Intent(view.getContext(), MainActivity.class);

            Log.d("score before being sent back", String.valueOf(score));

            mainActivityIntent.putExtra("scoreToNewRound", score);

            startActivity(mainActivityIntent);
        }
        else if (score == round4Max)
        {
            Intent mainActivityIntent = new Intent(view.getContext(), MainActivity.class);

            Log.d("score before being sent back", String.valueOf(score));

            mainActivityIntent.putExtra("scoreToNewRound", score);

            startActivity(mainActivityIntent);
        }
        else if (score == round5Max)
        {
            Intent highScoreIntent = new Intent(view.getContext(), HiScoreScreen.class);

            Log.d("score before being sent back", String.valueOf(score));

            highScoreIntent.putExtra("scoreBeatenGame", score);

            startActivity(highScoreIntent);

        }
    }

    // this method checks that the user got with the accelorometer is the correct one in the sequence
    public void flashbtnPickedByUserChecker(View view) {

        if (btnPickedByUser == gameSequence[userSequence]) {
            Log.d("flashed button "+btnPickedByUser, String.valueOf(gameSequence[userSequence]));
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            score++;
            tvScoreForUser.setText("Score " + score);
        } else {

            Toast.makeText(this,"Incorrect",Toast.LENGTH_SHORT).show();
            Intent gameOverScreenIntent = new Intent(view.getContext(), GameOverScreen.class);
            gameOverScreenIntent.putExtra("score", score);
            startActivity(gameOverScreenIntent);

            score = 0;
            //finish();
        }

        //series of ifs to decide what page to bring the user to
        if (score == round1Max)
        {
            Intent mainActivityIntent = new Intent(view.getContext(), MainActivity.class);

            Log.d("score before being sent back", String.valueOf(score));

            mainActivityIntent.putExtra("scoreToNewRound", score);

            startActivity(mainActivityIntent);
        }
        else if (score == round2Max)
        {
            Intent mainActivityIntent = new Intent(view.getContext(), MainActivity.class);

            Log.d("score before being sent back", String.valueOf(score));

            mainActivityIntent.putExtra("scoreToNewRound", score);

            startActivity(mainActivityIntent);

            /*Intent highScoreIntent = new Intent(view.getContext(), HiScoreScreen.class);

            Log.d("score before being sent back", String.valueOf(score));

            highScoreIntent.putExtra("scoreBeatenGame", score);

            startActivity(highScoreIntent);*/
        }
        else if (score == round3Max)
        {
            Intent mainActivityIntent = new Intent(view.getContext(), MainActivity.class);

            Log.d("score before being sent back", String.valueOf(score));

            mainActivityIntent.putExtra("scoreToNewRound", score);

            startActivity(mainActivityIntent);
        }
        else if (score == round4Max)
        {
            Intent mainActivityIntent = new Intent(view.getContext(), MainActivity.class);

            Log.d("score before being sent back", String.valueOf(score));

            mainActivityIntent.putExtra("scoreToNewRound", score);

            startActivity(mainActivityIntent);
        }
        else if (score == round5Max)
        {
            Intent highScoreIntent = new Intent(view.getContext(), HiScoreScreen.class);

            Log.d("score before being sent back", String.valueOf(score));

            highScoreIntent.putExtra("scoreBeatenGame", score);

            startActivity(highScoreIntent);

        }
    }


    ///////////////////////////////////////////
    // Accelormetter methods

    @Override
    public void onSensorChanged(SensorEvent event) {

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        //checks to see if the user has moved the device to the north
        if ((x > NORTH_MOVE_FORWARD && z > 0) && (highLimitNorth == false)) {
            highLimitNorth = true;
        }
        if ((x < NORTH_MOVE_BACKWARD && z < 0) && (highLimitNorth == true)) {
            highLimitNorth = false;
            btnPickedByUser = 1;
            btnNorth.setPressed(true);
            handlerUI.postDelayed(new Runnable() {
                @Override
                public void run() {
                    btnNorth.setPressed(false);
                }
            }, 1000);
            flashbtnPickedByUserChecker(btnNorth);
        }

        //checks to see if the user has moved the device to the south
        if ((x < SOUTH_MOVE_FORWARD && z < 0) && (highLimitSouth == false)) {
            highLimitSouth = true;
        }
        if ((x > SOUTH_MOVE_BACKWARD && z > 0) && (highLimitSouth == true)) {
            highLimitSouth = false;
            btnPickedByUser = 3;
            btnSouth.setPressed(true);
            handlerUI.postDelayed(new Runnable() {
                @Override
                public void run() {
                    btnSouth.setPressed(false);
                }
            }, 1000);
            flashbtnPickedByUserChecker(btnSouth);
        }

        //checks to see if the user has moved the device to the east
        if ((y > EAST_MOVE_FORWARD) && (highLimitEast == false)) {
            highLimitEast = true;
        }
        if ((y < EAST_MOVE_BACKWARD) && (highLimitEast == true)) {
            highLimitEast = false;
            btnPickedByUser = 2;
            btnEast.setPressed(true);
            handlerUI.postDelayed(new Runnable() {
                @Override
                public void run() {
                    btnEast.setPressed(false);
                }
            }, 1000);
            flashbtnPickedByUserChecker(btnEast);
        }

        //checks to see if the user has moved the device to the West
        if ((y < WEST_MOVE_FORWARD) && (highLimitWest == false)) {
            highLimitWest = true;
        }
        if ((y > WEST_MOVE_BACKWARD) && (highLimitWest == true)) {
            highLimitWest = false;
            btnPickedByUser = 4;
            btnWest.setPressed(true);
            handlerUI.postDelayed(new Runnable() {
                @Override
                public void run() {
                    btnWest.setPressed(false);
                }
            }, 1000);
            flashbtnPickedByUserChecker(btnWest);
        }

    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
    protected void onResume() {
        super.onResume();
        // turn on the sensor
        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // not used
    }
}