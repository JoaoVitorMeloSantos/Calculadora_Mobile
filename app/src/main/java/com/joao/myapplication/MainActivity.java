package com.joao.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Calculadora calculadora;
    Button btnEnter;
    Button btn0;
    Button btnPoint;
    Button btnDelete;
    Button btnSum;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btnSub;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btnMult;
    Button btnClear;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btnDiv;
    Button btnPV;
    Button btnFV;
    Button btnPMT;
    Button btnI;
    Button btnN;
    EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        calculadora = new Calculadora();

        btnEnter = findViewById(R.id.btnEnter);
        btn0 = findViewById(R.id.btn0);
        btnPoint = findViewById(R.id.btnPoint);
        btnDelete = findViewById(R.id.btnDelete);
        btnSum = findViewById(R.id.btnSum);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btnSub = findViewById(R.id.btnSub);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btnMult = findViewById(R.id.btnMult);
        btnClear = findViewById(R.id.btnClear);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnDiv = findViewById(R.id.btnDiv);
        btnPV = findViewById(R.id.btnPV);
        btnFV = findViewById(R.id.btnFV);
        btnPMT = findViewById(R.id.btnPMT);
        btnI = findViewById(R.id.btnI);
        btnN = findViewById(R.id.btnN);
        display = findViewById(R.id.display);

        btn0.setOnClickListener(onClickNumber("0"));
        btn1.setOnClickListener(onClickNumber("1"));
        btn2.setOnClickListener(onClickNumber("2"));
        btn3.setOnClickListener(onClickNumber("3"));
        btn4.setOnClickListener(onClickNumber("4"));
        btn5.setOnClickListener(onClickNumber("5"));
        btn6.setOnClickListener(onClickNumber("6"));
        btn7.setOnClickListener(onClickNumber("7"));
        btn8.setOnClickListener(onClickNumber("8"));
        btn9.setOnClickListener(onClickNumber("9"));

        btnEnter.setOnClickListener(onClickEnter());
        btnClear.setOnClickListener(onClickClear());
        btnDelete.setOnClickListener(onClickDelete());

    }

    public View.OnClickListener onClickNumber(String num) {
        return (v) -> {
            if (calculadora.getModo() == Calculadora.MODO_EXIBINDO) {
                display.setText("");
            }
            if (display.getText().toString().contains(".") && num.equals(".")) {
                return;
            }
            display.append(num);
            calculadora.setNumero(Double.parseDouble(display.getText().toString()));
        };
    }

    public View.OnClickListener onClickEnter(){
        return (v)->{
            calculadora.enter();
            display.setText("");
        };
    }

    public View.OnClickListener onClickClear(){
        return (v)->{
            calculadora.limpar();
            display.setText("");
        };
    }

    public View.OnClickListener onClickDelete() {
        return (v) -> {
            String currentText = display.getText().toString();
            if (!currentText.isEmpty()) {
                currentText = currentText.substring(0, currentText.length() - 1);
                display.setText(currentText);
                if (!currentText.isEmpty()) {
                    try {
                        double newNumber = Double.parseDouble(currentText);
                        calculadora.setNumero(newNumber);
                    } catch (NumberFormatException e) {
                        calculadora.setNumero(0);
                    }
                } else {
                    calculadora.setNumero(0);
                }
            }
        };
    }


}