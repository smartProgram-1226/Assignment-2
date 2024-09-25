package com.example.assignment_2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroupDelivery;
    private CheckBox checkBoxExtraSauce, checkBoxExpressDelivery;
    private SeekBar seekBarQuantity;
    private RatingBar ratingBarFood;
    private TextView quantityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        radioGroupDelivery = findViewById(R.id.radioGroupDelivery);
        checkBoxExtraSauce = findViewById(R.id.checkBoxExtraSauce);
        checkBoxExpressDelivery = findViewById(R.id.checkBoxExpressDelivery);
        seekBarQuantity = findViewById(R.id.seekBarQuantity);
        ratingBarFood = findViewById(R.id.ratingBarFood);
        quantityTextView = findViewById(R.id.quantityTextView);
        Button submitButton = findViewById(R.id.buttonSubmit);

        // SeekBar listener for quantity
        seekBarQuantity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                quantityTextView.setText("Quantity: " + (progress + 1));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Submit button click listener
        submitButton.setOnClickListener(v -> {
            // Get selected delivery method
            int selectedDeliveryId = radioGroupDelivery.getCheckedRadioButtonId();
            RadioButton selectedDeliveryButton = findViewById(selectedDeliveryId);
            String deliveryMethod = selectedDeliveryButton != null ? selectedDeliveryButton.getText().toString() : "None";

            // Get optional services
            boolean isExtraSauce = checkBoxExtraSauce.isChecked();
            boolean isExpressDelivery = checkBoxExpressDelivery.isChecked();

            // Get quantity
            int quantity = seekBarQuantity.getProgress() + 1;

            // Get rating
            float rating = ratingBarFood.getRating();

            // Create order summary string
            String orderSummary = "Delivery Method: " + deliveryMethod +
                    "\nExtra Sauce: " + (isExtraSauce ? "Yes" : "No") +
                    "\nExpress Delivery: " + (isExpressDelivery ? "Yes" : "No") +
                    "\nQuantity: " + quantity +
                    "\nRating: " + rating;

            // Display order summary in a dialog
// Display order summary in a dialog
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Order Summary")
                    .setMessage(orderSummary)
                    .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                    .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                    .show();
        });
    }
}