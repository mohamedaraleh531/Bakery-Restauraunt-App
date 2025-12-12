package com.example.mohamedspatessire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {


    CheckBox croissantCheese, croissantChocolate;

    CheckBox cakeStrawberries, cakeWhipped;

    CheckBox cookieExtraChips, cookieWalnuts;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        croissantCheese = findViewById(R.id.croissantCheese);
        croissantChocolate = findViewById(R.id.croissantChocolate);
        cakeStrawberries = findViewById(R.id.cakeStrawberries);
        cakeWhipped = findViewById(R.id.cakeWhipped);
        cookieExtraChips = findViewById(R.id.cookieExtraChips);
        cookieWalnuts = findViewById(R.id.cookieWalnuts);
        nextButton = findViewById(R.id.nextButton);


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                StringBuilder order = new StringBuilder();

                double subtotal = 0.0;


                if(croissantCheese.isChecked() || croissantChocolate.isChecked()) {
                    order.append("Croissant:\n");
                    if(croissantCheese.isChecked()) {
                        order.append("- Cheese (+$1.00)\n");
                        subtotal += 1.00;
                    }
                    if(croissantChocolate.isChecked()) {
                        order.append("- Chocolate Filling (+$1.50)\n");
                        subtotal += 1.50;
                    }
                }


                if(cakeStrawberries.isChecked() || cakeWhipped.isChecked()) {
                    order.append("Slice of Cake:\n");
                    if(cakeStrawberries.isChecked()) {
                        order.append("- Strawberries (+$1.00)\n");
                        subtotal += 1.00;
                    }
                    if(cakeWhipped.isChecked()) {
                        order.append("- Whipped Cream (+$0.75)\n");
                        subtotal += 0.75;
                    }
                }


                if(cookieExtraChips.isChecked() || cookieWalnuts.isChecked()) {
                    order.append("Chocolate Chip Cookie:\n");
                    if(cookieExtraChips.isChecked()) {
                        order.append("- Extra Chips (+$0.50)\n");
                        subtotal += 0.50;
                    }
                    if(cookieWalnuts.isChecked()) {
                        order.append("- Walnuts (+$0.75)\n");
                        subtotal += 0.75;
                    }
                }


                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                intent.putExtra("orderSummary", order.toString());
                intent.putExtra("subtotal", subtotal);
                startActivity(intent);
            }
        });
    }
}
