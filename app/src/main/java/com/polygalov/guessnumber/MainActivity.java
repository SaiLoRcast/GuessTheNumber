package com.polygalov.guessnumber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button button;
    private Button restartButton;
    private TextView textRadius;
    private TextView textView;
    private EditText inputNumber;
    private int range = 10;

    private boolean isGameFinished;
    private int randomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        restartButton = findViewById(R.id.restart_button);
        restartButton.setOnClickListener(this);

        textRadius = (TextView) findViewById(R.id.number_radius);
        textRadius.setText(getString(R.string.range) + range);

        textView = findViewById(R.id.info_about_guess);

        inputNumber = (EditText) findViewById(R.id.input_number_byuser);
        randomNumber = (int) (Math.random() * range);

        isGameFinished = false;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(button)) {
            checkNumber();
        }
        if (v.equals(restartButton)) {
            gameRstart();
        }
    }

    private void checkNumber() {
        try {
            int input = Integer.parseInt(inputNumber.getText().toString());
            if (!isGameFinished) {

                if (input > randomNumber) {
                    textView.setText("Загаданное число меньше");
                    inputNumber.setText("");

                } else if (input < randomNumber) {
                    textView.setText("Загаданное число больше");
                    inputNumber.setText("");
                } else if (input == randomNumber) {
                    textView.setText("Вы угадали");
                    isGameFinished = true;
                    button.setVisibility(View.INVISIBLE);
                    
                    restartButton.setVisibility(View.VISIBLE);
                    gameStop();
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


    }

    private void gameStop() {
        isGameFinished = false;

    }

    private void gameRstart() {
        Intent i = new Intent(this, this.getClass());
        finish();
        this.startActivity(i);
        restartButton.setVisibility(View.INVISIBLE);
    }

}
