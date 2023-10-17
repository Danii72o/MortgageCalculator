package com.example.mymortgageapp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextLoanAmount;
    private EditText editTextDownPayment;
    private EditText editTextInterestRate;
    private EditText editTextLoanTerm;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextLoanAmount = findViewById(R.id.editTextLoanAmount);
        editTextDownPayment = findViewById(R.id.editTextDownPayment);
        editTextInterestRate = findViewById(R.id.editTextInterestRate);
        editTextLoanTerm = findViewById(R.id.editTextLoanTerm);
        tvResult = findViewById(R.id.tvResult);

        Button btnCalculate = findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateLoanPayment();
            }
        });
    }

    private void calculateLoanPayment() {
        double loanAmount = Double.parseDouble(editTextLoanAmount.getText().toString());
        double downPayment = Double.parseDouble(editTextDownPayment.getText().toString());
        double interestRate = Double.parseDouble(editTextInterestRate.getText().toString());
        int loanTerm = Integer.parseInt(editTextLoanTerm.getText().toString());

        // Calculate the effective loan amount after deducting the down payment
        double effectiveLoanAmount = loanAmount - downPayment;

        double monthlyInterestRate = interestRate / (12 * 100);
        int totalPayments = loanTerm * 12;

        double monthlyPayment = (effectiveLoanAmount * monthlyInterestRate) /
                (1 - Math.pow(1 + monthlyInterestRate, -totalPayments));

        // Format the monthly payment to two decimal places
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedMonthlyPayment = decimalFormat.format(monthlyPayment);

        tvResult.setText("Monthly Loan Payment: $" + formattedMonthlyPayment);
    }
}
