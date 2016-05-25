package com.example.kdlim.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int quantity = 0;
    /**
     * This method is called when the plus button is clicked.
     */
    public void increment (View view) {
        quantity = quantity + 1;
        display(quantity);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
       // Figure out if the user wants wipped cream topping
        CheckBox whippedCreamCheckBox = (CheckBox)findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
       // Figure out if the user wants chocolate topping
        CheckBox chocolateCheckBox = (CheckBox)findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        Log.v("MainActivity","Has whipped crea :" + hasWhippedCream + "\n has chocolate :" + hasChocolate);
        //  Get text from edittext
        EditText NameField = (EditText)findViewById(R.id.name_field);
        String name = NameField.getText().toString();
        Log.v("MainActivity","NameField: " + name);
        int price = calculatePrice(hasWhippedCream,hasChocolate);
        displayMessage(createOrderSummary(name,price,hasWhippedCream,hasChocolate));
    }
    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement (View view) {
        quantity =quantity - 1;
        display(quantity);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
    /**
     * Calculates the price of the order
     * @return total price
     * @param addWhippedCream is whether or not the user wants whipping cream topping
     * @param addChotolate  is whether or not the user wants chotolate topping
     */
    private int calculatePrice(boolean addWhippedCream,boolean addChotolate){
        // price of 1 cup of coffee
        int basePrice = 5;
        // add 1$ if the user wants whipped cream
        if(addWhippedCream){
            basePrice = basePrice + 1;
        }
        //add 2$ if the user wants chotolate
        if (addChotolate){
            basePrice = basePrice + 2;
        }

        return quantity * basePrice;

    }

    /**
     * create summary of the order
     * @param name the user's name
     * @param price of the order
     * @return text summary
     * @param addWhippedCream is whether or not the user wants whipped cream topping
     * @param addChotolate is whether or not the user wants chotolate
     */
    private String createOrderSummary(String name ,int price , boolean addWhippedCream , boolean addChotolate){
        String priceMessage ="Name : " + name;
        priceMessage += "\nAdd Whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd Chocolate?" + addChotolate;
        priceMessage +=  "\nQuantity: " + quantity;
        priceMessage += "\nTotle: $ " + price;
        priceMessage +=  "\nThank you!";
        return priceMessage;
    }
}
