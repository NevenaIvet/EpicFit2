package com.example.user.epicfitproject.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.user.epicfitproject.R;

public class AddExerciseProperties extends AppCompatActivity {
    private NumberPicker repsPicker;
    private NumberPicker seriesPicker;
    private Button doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise_properties);
        repsPicker = (NumberPicker) findViewById(R.id.repPicker);
        seriesPicker= (NumberPicker) findViewById(R.id.seriesPicker);
        repsPicker.setMinValue(0);
        repsPicker.setMaxValue(300);
        repsPicker.setWrapSelectorWheel(false);
        repsPicker.setSaveEnabled(true);
        repsPicker.setHorizontalScrollBarEnabled(true);
        seriesPicker.setMinValue(1);
        seriesPicker.setMaxValue(50);
        seriesPicker.setWrapSelectorWheel(false);
        seriesPicker.setSaveEnabled(true);
        seriesPicker.setHorizontalScrollBarEnabled(true);
        doneButton = (Button) findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(repsPicker.getValue()>0&&seriesPicker.getValue()>1){

                    Toast.makeText(AddExerciseProperties.this,"Exercise added !",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
