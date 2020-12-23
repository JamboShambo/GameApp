package jamie.itsligo.gameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HiScoreScreen extends AppCompatActivity {

    //declring my varibles
    int score;
    String stringetName, name, wordRound;
    ListView listView;
    TextView tvScore,tvRound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hi_score_screen_land);

        //intents getting data from first page
        score = getIntent().getIntExtra("scoreToHighScore", score );
        name = getIntent().getStringExtra("nameToHighScore");

        //getting my elements
        listView = findViewById(R.id.lv);
        tvRound = findViewById(R.id.tvRound);
        tvScore = findViewById(R.id.tvScore);

        //displaying data to user
        tvScore.setText("Score:" + score);


        DatabaseHandler db = new DatabaseHandler(this);

        db.emptyHiScores();     // empty table if required

        // Inserting hi scores
        Log.i("Insert: ", "Inserting ..");
        db.addHiScore(new HiScore("20 OCT 2020", "Frodo", 12));
        db.addHiScore(new HiScore("28 OCT 2020", "Dobby", 16));
        db.addHiScore(new HiScore("20 NOV 2020", "DarthV", 20));
        db.addHiScore(new HiScore("20 NOV 2020", "Bob", 18));
        db.addHiScore(new HiScore("22 NOV 2020", "Gemma", 22));
        db.addHiScore(new HiScore("30 NOV 2020", "Joe", 30));
        db.addHiScore(new HiScore("01 DEC 2020", "DarthV", 22));
        db.addHiScore(new HiScore("02 DEC 2020", "Gandalf", 132));
        db.addHiScore(new HiScore("02 DEC 2020", name, score));



        // Reading all scores
        Log.i("Reading: ", "Reading all scores..");
        List<HiScore> hiScores = db.getAllHiScores();


        for (HiScore hs : hiScores) {
            String log =
                    "Id: " + hs.getScore_id() +
                            ", Date: " + hs.getGame_date() +
                            " , Player: " + hs.getPlayer_name() +
                            " , Score: " + hs.getScore();

            // Writing HiScore to log
            Log.i("Score: ", log);

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

        //getting data to display our top 5 scores on the page
        Log.i("divider", "====================");

        HiScore singleScore = db.getHiScore(5);
        Log.i("High Score 5 is by ", singleScore.getPlayer_name() + " with a score of " +
                singleScore.getScore());

        Log.i("divider", "====================");

        // Calling SQL statement
        List<HiScore> top5HiScores = db.getTopFiveScores();

        for (HiScore hs : top5HiScores) {
            String log =
                    "Id: " + hs.getScore_id() +
                            ", Date: " + hs.getGame_date() +
                            " , Player: " + hs.getPlayer_name() +
                            " , Score: " + hs.getScore();

            // Writing HiScore to log
            Log.i("Score: ", log);
        }
        Log.i("divider", "====================");

        HiScore hiScore = top5HiScores.get(top5HiScores.size() - 1);
        // hiScore contains the 5th highest score
        Log.i("fifth Highest score: ", String.valueOf(hiScore.getScore()) );


        Log.i("divider", "====================");

        // Calling SQL statement
        top5HiScores = db.getTopFiveScores();
        List<String> scoresStr;
        scoresStr = new ArrayList<>();

        int j = 1;
        for (HiScore hs : top5HiScores) {

            String log =
                    "Id: " + hs.getScore_id() +
                            ", Date: " + hs.getGame_date() +
                            " , Player: " + hs.getPlayer_name() +
                            " , Score: " + hs.getScore();

            // store score in string array
            scoresStr.add(j++ + " : "  +
                    hs.getPlayer_name() + "\t" +
                    hs.getScore());
            // Writing HiScore to log
            Log.i("Score: ", log);
        }

        Log.i("divider", "====================");
        Log.i("divider", "Scores in list <>>");
        for (String ss : scoresStr) {
            Log.i("Score: ", ss);
        }

        // Reading all scores
        Log.i("Reading: ", "Reading all scores..");

        for (HiScore hs : hiScores) {
            String log =
                    "Id: " + hs.getScore_id() +
                            ", Date: " + hs.getGame_date() +
                            " , Player: " + hs.getPlayer_name() +
                            " , Score: " + hs.getScore();

            // Writing HiScore to log
            Log.i("Score: ", log);
        }

        Log.i("divider", "====================");

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scoresStr);
        listView.setAdapter(itemsAdapter);
    }


    public void doFinalAgain(View view) {
        Intent doAgainMainIntent = new Intent(view.getContext(), MainActivity.class);
        score = 0;
        doAgainMainIntent.putExtra("restartScore", score);

        startActivity(doAgainMainIntent);
    }
}