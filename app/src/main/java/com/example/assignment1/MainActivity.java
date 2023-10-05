package com.example.mobileappdevassignment1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText num1; // initial principal
    private EditText num2; // annual interest rate
    private EditText num3; // total number of payments in months
    private Button add; // calculation button
    private TextView num4; // payment amount per period --> final answer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_first);

        // Finds the numbers inputted into the text boxes by using the id in first fragment
        num1 = (EditText) findViewById(R.id.Principal);
        num2 = (EditText) findViewById(R.id.Interest);
        num3 = (EditText) findViewById(R.id.TotalPayment);
        num4 = (TextView) findViewById(R.id.PaymentAmount);
        add = (Button) findViewById(R.id.button1);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Converts the numbers from the text boxes to strings
                    double initialPrincipal = Double.parseDouble(num1.getText().toString());
                    double annualInterestRate = Double.parseDouble(num2.getText().toString());
                    double totalPayments = Double.parseDouble(num3.getText().toString());

                    // Check if any of the input values is negative
                    if (initialPrincipal < 0 || annualInterestRate < 0 || totalPayments < 0) {
                        num4.setText("Invalid input. Please enter positive numbers.");
                        // Exits
                        return;
                    }

                    // Converts the annual interest rate to a decimal and then to a monthly rate
                    double monthlyInterestRate = annualInterestRate / 12 / 100;

                    // Calculate the monthly payment using the correct amortization formula
                    double finalCalc = (initialPrincipal * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, totalPayments)) /
                            (Math.pow(1 + monthlyInterestRate, totalPayments) - 1);

                    // Format the result to two decimal places
                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    String formattedResult = decimalFormat.format(finalCalc);
                    num4.setText("The total amount to be paid monthly is $" + formattedResult);
                } catch (NumberFormatException e) {
                    // Handles anything not numbers
                    num4.setText("Invalid input. Please enter valid numbers.");
                }
            }
        });
    }
}
