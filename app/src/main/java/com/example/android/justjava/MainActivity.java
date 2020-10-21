/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 1;
    int price;
    String cream;
    String chocolate;
    String name;
    int chocolatePrice;
    int creamPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {
        calculatePrice();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(intent.EXTRA_SUBJECT,"Just Java Order for " + name);
        intent.putExtra(intent.EXTRA_TEXT,createOrderSummary());
        if (intent.resolveActivity(getPackageManager()) != null) {
        startActivity(intent);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int quantity) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }
    /**
     * This method displays the given price on the screen.
     */
    public void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    public void increment(View view) {
        if(quantity <=99) {
            quantity = quantity + 1;
            display(quantity);
            price = calculatePrice();
            displayPrice(price);
        }
        else if (quantity == 100){
            Toast.makeText(this,"you cannot have more than 100 Coffees!", Toast.LENGTH_SHORT).show();
        }
    }
    public void decrement(View view) {
        if(quantity > 1 ) {
            quantity = quantity - 1;
            display(quantity);
            price = calculatePrice();
            displayPrice(price);
        }
        else if (quantity == 1){
            Toast.makeText(this,"you cannot have less than one Coffee!",Toast.LENGTH_SHORT).show();
        }

        display(quantity);
        price = calculatePrice();
        displayPrice(price);

    }

    private int calculatePrice() {
       return  price =  quantity * ( 5 + chocolatePrice + creamPrice);
    }

    private String createOrderSummary() {
        inputsName();
       return "Ordered Placed!,\nName : "+ name + "\nQuantity : " + quantity + cream + chocolate + "\nTotal : $"+ price +"\nThank You!!";
    }
    /**
     * This method displays the given text on the screen.
     */


    public void hasWhippedCream(View view) {
        CheckBox WhippedCream = (CheckBox) findViewById(R.id.cream_checkBox);
        if(WhippedCream.isChecked()) {
            cream = "\nAdd Whipped Cream";
            creamPrice = 1;
        }
        else {
            cream = " false";
            creamPrice = 0;
        }
    }

    public void hasChoclate(View view) {
        CheckBox Choclate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        if(Choclate.isChecked()) {
            chocolate = "\nAdd Choclate";
            chocolatePrice = 2;
        }
        else {
            chocolate = " false";
            chocolatePrice = 0;
        }
    }

    public void inputsName(){
        EditText nameTextView = (EditText)findViewById(R.id.editTextTextPersonName);
        name = nameTextView.getText().toString();
    }
}