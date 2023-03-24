package com.ismokii.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView resultText;
    private RadioButton male;
    private RadioButton female;
    private EditText age;
    private EditText feet;
    private EditText inches;
    private EditText weight;
    private Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        extracted();
        setUpButtonClickListener();

    }

    private void extracted() {


        resultText = findViewById(R.id.text_view_result);
        male = findViewById(R.id.radio_button_male);
        female = findViewById(R.id.radio_button_female);
        age = findViewById(R.id.edit_text_age);
        feet = findViewById(R.id.edit_text_feet);
        inches = findViewById(R.id.edit_text_inches);
        weight = findViewById(R.id.edit_text_weight);
        calculate = findViewById(R.id.button_calculate);

    }

    private void setUpButtonClickListener() {
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double bmiResult = calculateBMI();

                String ageText = age.getText().toString();
                int ageNo = Integer.parseInt(ageText);

                if (ageNo >= 18)
                    displayresult(bmiResult);
                else
                    displayGuidance(bmiResult);

            }

        });
    }

    private double calculateBMI() {


        String feetText = feet.getText().toString();
        String inchesText = inches.getText().toString();
        String weightText = weight.getText().toString();

        //Convert the string arguments received by the user into integer for further calculations

        int feetNo = Integer.parseInt(feetText);
        int inchesNo = Integer.parseInt(inchesText);
        int weightNo = Integer.parseInt(weightText);

        int totalinch = (feetNo * 12) + inchesNo;

        double totalheight = totalinch * 0.0254;

        double bmi = weightNo / (totalheight * totalheight);
        return bmi;

    }

    private void displayresult(double bmi) {
        DecimalFormat df = new DecimalFormat("0.00");
        String bmiresult = df.format(bmi);

        String fullresult;
        if (bmi < 18.5) {
            fullresult = bmiresult + " - You are underweight";
        } else if (bmi > 25) {
            fullresult = bmiresult + " - You are Overweight";
        } else
            fullresult = bmiresult + " - You are Healthy";

        resultText.setText(fullresult);
    }

    private void displayGuidance(double bmi) {

        DecimalFormat df = new DecimalFormat("0.00");
        String bmiresult = df.format(bmi);

        String fullresult;
        if (male.isChecked())
            fullresult = bmiresult + " - As you are below 18 consult your doctor for healthy boy's range";
        else if (female.isChecked())
            fullresult = bmiresult + " - As you are below 18 consult your doctor for healthy girls's range";
        else
            fullresult = bmiresult + " - As you are below 18 consult your doctor for healthy range";

        resultText.setText(fullresult);

    }

}

