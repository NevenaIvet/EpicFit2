package com.example.user.epicfitproject;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

public class ExerciseActivity extends AppCompatActivity {
    private TextView heading;
    private TextView info;
    private ImageView image;
    private Button add;
    private TextView moreText;
    private VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        heading = (TextView) findViewById(R.id.heading);
       // heading.setText("Deadlift");
       // heading.setEnabled(false);
      //  heading.setClickable(false);
        String information = "To Deadlift with proper form means with your lower back neutral. Rounding your lower spine during heavy Deadlifts is dangerous for your back. It squeezes your spinal discs and can cause injuries like herniated discs. Deadlift with your lower back neutral to avoid injury. Proper Deadlift form increases effectiveness. Moving the bar in a vertical line shortens the distance the weight travels from floor to top. This increases how much you Deadlift. You’ll get stronger and build more muscle if you Deadlift with proper form.\n" +
                "Read more: http://stronglifts.com/deadlift/";
        info = (TextView) findViewById(R.id.infoForTheExercise);
        info.setText(information);
      //  info.setEnabled(false);
      //  info.setClickable(false);
        moreText= (TextView) findViewById(R.id.additionText);
       // moreText.setEnabled(false);
        //moreText.setClickable(false);
        image= (ImageView) findViewById(R.id.image);
        add = (Button) findViewById(R.id.addButton);
        video = (VideoView) findViewById(R.id.video);
        String path = "https://www.youtube.com/watch?v=mF8xnWSA65k";
        Uri uri= Uri.parse(path);
        video.setVideoURI(uri);

        video.setClickable(true);
        video.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                video.start();
            }
        });


    }
}
