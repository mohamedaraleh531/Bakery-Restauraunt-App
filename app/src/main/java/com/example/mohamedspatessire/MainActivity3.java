package com.example.mohamedspatessire;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    TextView orderSummaryText, subtotalText, totalText;
    Button submitOrderButton;
    double subtotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        orderSummaryText = findViewById(R.id.orderSummary);
        subtotalText = findViewById(R.id.subtotalText);
        totalText = findViewById(R.id.totalText);
        submitOrderButton = findViewById(R.id.submitOrderButton);


        String orderSummary = getIntent().getStringExtra("orderSummary");
        subtotal = getIntent().getDoubleExtra("subtotal", 0.0);

        double tax = subtotal * 0.07; // 7% tax
        double total = subtotal + tax;


        orderSummaryText.setText(orderSummary.isEmpty() ? "No items selected." : orderSummary);
        subtotalText.setText(String.format("Subtotal: $%.2f", subtotal));
        totalText.setText(String.format("Total (with tax): $%.2f", total));


        submitOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailSubject = "New Bakery Order";
                String emailBody = "Order Details:\n" + orderSummary +
                        String.format("\nSubtotal: $%.2f\nTotal (with tax): $%.2f", subtotal, total);

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:youremail@example.com")); // replace with your email
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, emailBody);

                if (emailIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(emailIntent);
                }
            }
        });
    }
}
