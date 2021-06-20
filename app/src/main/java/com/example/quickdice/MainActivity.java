package com.example.quickdice;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/***************************************************************
 * Main Activity
 *
 * This orchestrates all that the app does.
 **************************************************************/

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    // two variables for the user's custom dice throw
    int customSides = 0;
    int customRoll = 0;

    // uses button press itself as a "parameter" to roll the dice
    public void rollDice(int sides, int rolls)
    {
        // creates a DiceButton object with the correct parameters and rolls it
        DiceButton dice = new DiceButton(sides, rolls);
        int total = dice.rollDice();

        // sends the total to the sum result
        final TextView sumText = (TextView) findViewById(R.id.sum_text);
        String sum = Integer.toString(total);
        sumText.setText(sum);

        // sends the dice and number of rolls to the results
        final TextView sidesAndRollsText = (TextView) findViewById(R.id.sides_and_rolls_text);
        String result = "D" + sides + " x " + rolls;
        sidesAndRollsText.setText(result);
    }

    // sets up the spinner for selecting a custom die
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    {
        // converts die selection to the raw number of sides
        String numSides = (String) parent.getItemAtPosition(pos);
        customSides = Integer.parseInt(numSides.substring(1));
    }

    public void onNothingSelected(AdapterView<?> parent) {} // does nothing for now

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // roll a D4
        Button buttonD4 = (Button) findViewById(R.id.d4);
        buttonD4.setOnClickListener(view -> rollDice(4, 1));

        // roll a D6
        Button buttonD6 = (Button) findViewById(R.id.d6);
        buttonD6.setOnClickListener(view -> rollDice(6, 1));

        // roll a D8
        Button buttonD8 = (Button) findViewById(R.id.d8);
        buttonD8.setOnClickListener(view -> rollDice(8, 1));

        // roll a D10
        Button buttonD10 = (Button) findViewById(R.id.d10);
        buttonD10.setOnClickListener(view -> rollDice(10, 1));

        // roll a D12
        Button buttonD12 = (Button) findViewById(R.id.d12);
        buttonD12.setOnClickListener(view -> rollDice(12, 1));

        // roll a D20
        Button buttonD20 = (Button) findViewById(R.id.d20);
        buttonD20.setOnClickListener(view -> rollDice(20, 1));

        // roll a D100
        Button buttonD100 = (Button) findViewById(R.id.d100);
        buttonD100.setOnClickListener(view -> rollDice(100, 1));

        // roll the custom choices
        Button buttonCustom = (Button) findViewById(R.id.dCustom);
        buttonCustom.setOnClickListener(view -> rollDice(customSides, customRoll));

        // allows user to choose dice to roll
        Spinner diceSpinner = (Spinner) findViewById(R.id.dice_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.dice_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diceSpinner.setAdapter(adapter);
        diceSpinner.setOnItemSelectedListener(this);

        // allows user to choose rolls for a custom roll
        EditText numRolls = (EditText) findViewById(R.id.num_rolls);
        numRolls.setOnEditorActionListener((v, actionId, event) -> {
            // translates EditText to String to Integer... whew!
            customRoll = Integer.parseInt(String.valueOf(numRolls.getText()));
            return false;
        });
    }
}