package com.example.calcy;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView inpView, expView;
    private boolean op;
    String str, operator;
    float a, b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inpView = findViewById(R.id.inpView);
        inpView.setText("0");
        expView = findViewById(R.id.expView);
        expView.setText("Exp: ");

         op = false;
         str = "";
         operator = "";
         a = 0;
         b = 0;
    }

    public void numberEvent(View view) {
        String inp = inpView.getText().toString();
        switch (view.getId()) {
            case R.id.oneBtn:
                if (inp == "0")
                    inp = "1";
                else
                    inp += "1";
                break;
            case R.id.twoBtn:
                if (inp == "0")
                    inp = "2";
                else
                    inp += "2";
                break;
            case R.id.threeBtn:
                if (inp == "0")
                    inp = "3";
                else
                    inp += "3";
                break;
            case R.id.fourBtn:
                if (inp == "0")
                    inp = "4";
                else
                    inp += "4";
                break;
            case R.id.fiveBtn:
                if (inp == "0")
                    inp = "5";
                else
                    inp += "5";
                break;
            case R.id.sixBtn:
                if (inp == "0")
                    inp = "6";
                else
                    inp += "6";
                break;
            case R.id.sevenBtn:
                if (inp == "0")
                    inp = "7";
                else
                    inp += "7";
                break;
            case R.id.eightBtn:
                if (inp == "0")
                    inp = "8";
                else
                    inp += "8";
                break;
            case R.id.nineBtn:
                if (inp == "0")
                    inp = "9";
                else
                    inp += "9";
                break;
            case R.id.zeroBtn:
                if (inp == "0")
                    inp = "0";
                else
                    inp += "0";
                break;
        }
        inpView.setText(inp);
    }

    public void buttonEvent(View view) {
        String inp = inpView.getText().toString();
        switch (view.getId()) {
            case R.id.cBtn:
                if (inp.length() > 1)
                    inp = inp.substring(0, inp.length() - 1);
                else if (inp.length() > 0)
                    inp = "0";
                break;
            case R.id.acBtn:
                inp = "0";
                op = false;
                str = "";
                operator = "";
                a = 0;
                b = 0;
                expView.setText("Exp: ");
                break;
        }
        inpView.setText(inp);
    }

    public void operatorEvent(View view) {
        switch(view.getId()){
            case R.id.perBtn:
                str = inpView.getText().toString();
                a = Float.parseFloat(str);
                expView.setText("Exp: " + Float.toString(a) + "%");
                inpView.setText(Float.toString(a/100));
                break;
            case R.id.addBtn:
                op = true;
                str = inpView.getText().toString();
                a = Float.parseFloat(str);
                operator = "+";
                expView.setText("Exp: " + Float.toString(a) + operator);
                inpView.setText("0");
                break;
            case R.id.subBtn:
                op = true;
                str = inpView.getText().toString();
                a = Float.parseFloat(str);
                operator = "-";
                expView.setText("Exp: " + Float.toString(a) + operator);
                inpView.setText("0");
                break;
            case R.id.mulBtn:
                op = true;
                str = inpView.getText().toString();
                a = Float.parseFloat(str);
                operator = "*";
                expView.setText("Exp: " + Float.toString(a) + operator);
                inpView.setText("0");
                break;
            case R.id.divBtn:
                op = true;
                str = inpView.getText().toString();
                a = Float.parseFloat(str);
                operator = "/";
                expView.setText("Exp: " + Float.toString(a) + operator);
                inpView.setText("0");
                break;
            case R.id.eqlBtn:
                str = inpView.getText().toString();
                b = Float.parseFloat(str);
                if(op) {
                    switch (operator) {
                        case "+":
                            expView.setText(expView.getText().toString() + " " + Float.toString(b));
                            inpView.setText(Float.toString(a + b));
                            break;
                        case "-":
                            expView.setText(expView.getText().toString() + " " + Float.toString(b));
                            inpView.setText(Float.toString(a - b));
                            break;
                        case "*":
                            expView.setText(expView.getText().toString() + " " + Float.toString(b));
                            inpView.setText(Float.toString(a * b));
                            break;
                        case "/":
                            expView.setText(expView.getText().toString() + " " + Float.toString(b));
                            inpView.setText(Float.toString(a / b));
                            break;
                    }
                }
                op = false;
                break;

        }
    }
}

