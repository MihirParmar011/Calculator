package com.example.calculator;

//import android.content.Context;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView solutionView, resultView;
    MaterialButton button_c,button_open_bracket,button_close_bracket;
    MaterialButton button_0,button_1,button_2,button_3,button_4,button_5,button_6,button_7,button_8,button_9;
    MaterialButton button_equal,button_dot,button_plus,button_minus,button_multi,button_divide,button_modulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        solutionView = findViewById(R.id.solution_v);
        resultView = findViewById(R.id.result_v);

        assignId(R.id.button_0).setOnClickListener(this);
        assignId(R.id.button_1).setOnClickListener(this);
        assignId(R.id.button_2).setOnClickListener(this);
        assignId(R.id.button_3).setOnClickListener(this);
        assignId(R.id.button_4).setOnClickListener(this);
        assignId(R.id.button_5).setOnClickListener(this);
        assignId(R.id.button_6).setOnClickListener(this);
        assignId(R.id.button_7).setOnClickListener(this);
        assignId(R.id.button_8).setOnClickListener(this);
        assignId(R.id.button_9).setOnClickListener(this);
        assignId(R.id.button_dot).setOnClickListener(this);
        assignId(R.id.button_c).setOnClickListener(this);
        assignId(R.id.button_ac).setOnClickListener(this);
        assignId(R.id.button_equal).setOnClickListener(this);
        assignId(R.id.button_plus).setOnClickListener(this);
        assignId(R.id.button_minus).setOnClickListener(this);
        assignId(R.id.button_multi).setOnClickListener(this);
        assignId(R.id.button_divide).setOnClickListener(this);
        assignId(R.id.button_modulo).setOnClickListener(this);

    }

    private MaterialButton assignId(int id) {
        MaterialButton button = findViewById(id);
        button.setOnClickListener(this);
        return button;
    }

    @Override
    public void onClick(View view) {
        MaterialButton Button = (MaterialButton) view;
        String ButtonText = Button.getText().toString();
        String dataToCalculate = solutionView.getText().toString();

        if(ButtonText.equals("=")){
            try {
                String finalResult = getResult(dataToCalculate);
                solutionView.setText(finalResult);
                resultView.setText(finalResult);
            } catch (Exception e) {
                // Handle error case
                Toast.makeText(this, "Invalid expression", Toast.LENGTH_SHORT).show();
                solutionView.setText(""); // Clear the input field to let the user enter a new expression
            }
        }
        else if(ButtonText.equals("C")){
          if (dataToCalculate.length() > 0) {
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            }
            solutionView.setText(dataToCalculate);
        }
        else if (ButtonText.equals("AC")) {
            solutionView.setText("0");
            resultView.setText("0");
        }
        else{
            dataToCalculate = dataToCalculate+ButtonText;
            solutionView.setText(dataToCalculate);
        }
        solutionView.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

    }
    String getResult(String data) {
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }
}