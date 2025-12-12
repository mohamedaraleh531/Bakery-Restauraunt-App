package com.example.mohamedspatessire;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    TextView orderSummaryText, subtotalText, totalText;
    Button submitOrderButton;
    double subtotal, total;
    String orderSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        orderSummaryText = findViewById(R.id.orderSummary);
        subtotalText = findViewById(R.id.subtotalText);
        totalText = findViewById(R.id.totalText);
        submitOrderButton = findViewById(R.id.submitOrderButton);

        orderSummary = getIntent().getStringExtra("orderSummary");
        subtotal = getIntent().getDoubleExtra("subtotal", 0.0);
        double tax = subtotal * 0.07; // 7% tax
        total = subtotal + tax;

        orderSummaryText.setText(orderSummary == null || orderSummary.isEmpty() ? "No items selected." : orderSummary);
        subtotalText.setText(String.format("Subtotal: $%.2f", subtotal));
        totalText.setText(String.format("Total (with tax): $%.2f", total));

        submitOrderButton.setOnClickListener(v -> sendOrderByEmail());
    }

    private void sendOrderByEmail() {
        String emailSubject = "New Bakery Order";
        String emailBody = "Order Details:\n" + orderSummary +
                String.format("\nSubtotal: $%.2f\nTotal (with tax): $%.2f", subtotal, total);

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"mohamed@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailBody);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send email with:"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "No email apps installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
