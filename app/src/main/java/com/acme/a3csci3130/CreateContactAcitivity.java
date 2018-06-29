package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Class that creates a contact activity with at most five fields:
 * Business Number (Required), Name (Required), Primary Business (Required),
 * Address (Can be empty), Province (Can be empty).
 */

public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText businessNumberField, nameField, primaryBusinessField, addressField, provinceField;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        businessNumberField = (EditText) findViewById(R.id.businessNumber);
        nameField = (EditText) findViewById(R.id.name);
        primaryBusinessField = (EditText) findViewById(R.id.primaryBusiness);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (EditText) findViewById(R.id.province);
    }

    /**
     * Get all texts from edit texts and create a contact variable to
     * pass to firebase when submitInfo button has been clicked.
     */
    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String personID = appState.firebaseReference.push().getKey();
        String businessNumber = businessNumberField.getText().toString();
        String name = nameField.getText().toString();
        String primaryBusiness = primaryBusinessField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getText().toString();
        Contact person = new Contact(personID, businessNumber, name, primaryBusiness, address, province);

        appState.firebaseReference.child(personID).setValue(person);

        finish();

    }
}
