package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Class that updates or deletes a contact activity with at most five fields:
 * Business Number (Required), Name (Required), Primary Business (Required),
 * Address (Can be empty), Province (Can be empty).
 */

public class DetailViewActivity extends Activity {

    private EditText businessNumberField, nameField, primaryBusinessField, addressField, provinceField;
    Contact receivedPersonInfo;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");
        appState = ((MyApplicationData) getApplicationContext());

        businessNumberField = (EditText) findViewById(R.id.businessNumber);
        nameField = (EditText) findViewById(R.id.name);
        primaryBusinessField = (EditText) findViewById(R.id.primaryBusiness);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (EditText) findViewById(R.id.province);

        if(receivedPersonInfo != null){
            businessNumberField.setText(receivedPersonInfo.businessNumber);
            nameField.setText(receivedPersonInfo.name);
            primaryBusinessField.setText(receivedPersonInfo.primaryBusiness);
            addressField.setText(receivedPersonInfo.address);
            provinceField.setText(receivedPersonInfo.province);
        }
    }

    /**
     * Get all texts from edit texts after updating data and create a contact
     * variable to pass to firebase when submitInfo button has been clicked.
     */
    public void updateContact(View v){
        String uid = receivedPersonInfo.uid;
        String businessNumber = businessNumberField.getText().toString();
        String name = nameField.getText().toString();
        String primaryBusiness = primaryBusinessField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getText().toString();
        Contact person = new Contact(uid, businessNumber, name, primaryBusiness, address, province);

        appState.firebaseReference.child(uid).setValue(person);

        finish();

    }

    /**
     * Delete a specific contact activity.
     */
    public void eraseContact(View v)
    {
        appState.firebaseReference.child(receivedPersonInfo.uid).removeValue();

        finish();

    }
}
