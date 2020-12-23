package jamie.itsligo.gameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class GameOverScreen extends AppCompatActivity {

    //declaring my varibles
    int score;
    String stringetName, wordRound;
    EditText etName;

    TextView tvUserScore, tvRound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_screen_land);

        //intent getting data
        score = getIntent().getIntExtra("score", score );

        //finding my eleemnts
        tvUserScore = findViewById(R.id.tvUserScore);
        tvRound = findViewById(R.id.tvRound);
        etName = findViewById(R.id.etName);

        //displying data to the user
        String wordScore = "Score:"+score;
        tvUserScore.setText(wordScore);
        if (score > 22)
        Toast.makeText(this, "Your have hit a new high score: "+score+". Enter your name join the hall of fame!", Toast.LENGTH_LONG).show();


        // if deciding what data to dispaly to the user
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
        else if (score < 4)
        {
            wordRound = "Round:1";
            tvRound.setText(wordRound);
        }

    }

    //btn when clicked finding the top 5 scores and if users score was good enough to be a high score
    public void doHighScore(View view) {

        DatabaseHandler db = new DatabaseHandler(this);

        // Calling SQL statement
        List<HiScore> top5HiScores = db.getTopFiveScores();

        Log.d("divider", "====================");
        HiScore hiScore = top5HiScores.get(top5HiScores.size() - 1);
        // hiScore contains the 5th highest score
        Log.d("fifth Highest score: ", String.valueOf(hiScore.getScore()) );

        stringetName = etName.getText().toString();

        // simple test to add a hi score
        // if 5th highest score < score, then insert new score
        if (hiScore.getScore() < score && stringetName != null) {
            Log.d("fifth Highest score: ", String.valueOf(hiScore.getScore()) );
            Log.d("Users score: ", String.valueOf(score) );

            Intent doHighScoreIntent = new Intent(view.getContext(),HiScoreScreen.class);

            doHighScoreIntent.putExtra("scoreToHighScore", score);
            doHighScoreIntent.putExtra("nameToHighScore", stringetName);


            Log.i("Reading: ", "Score before intent...."+score);
            Log.i("Reading: ", "Name before intent...."+stringetName);

            startActivity(doHighScoreIntent);
        }
        else
        {
            if (stringetName != null)
            {
                Toast.makeText(this, "You weren't good enough!", Toast.LENGTH_SHORT).show();
            }

            Intent doHighScoreIntent = new Intent(view.getContext(),HiScoreScreen.class);

            startActivity(doHighScoreIntent);
        }
    }

    //btn when clicked brings back to sequence page
    public void doAgain(View view) {

        Intent doAgainIntent = new Intent(view.getContext(), MainActivity.class);
        score = 0;
        doAgainIntent.putExtra("score", score);

        startActivity(doAgainIntent);

    }
}