package com.e.mychallenge1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText editTextName, editTextEmail, editTextpassword, editTextPassRepeat;
    private Button btnPickImages, btnRegister;
    private TextView txtWarnName, txtWarnEmail, txtWarnPassword, txtWarnPassRepeat;
    private Spinner countriesSpinner;
    private RadioGroup rgGenger;
    private RadioButton rbmale, rbFemale, rbOther;
    private CheckBox checkAgree;
    private ConstraintLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        btnPickImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Allmost there", Toast.LENGTH_SHORT).show();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initRegister();
            }
        });
    }


    private void initRegister() {
        Log.d(TAG, "InitRegister: Started");

        if (validateData()) {
            if (checkAgree.isChecked()) {
                showSnackBar();
            } else {
                Toast.makeText(this, "You need to agree to the license agreement", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void showSnackBar() {
        Log.d(TAG, "showSnackBar: Started");
        txtWarnName.setVisibility(View.GONE);
        txtWarnEmail.setVisibility(View.GONE);
        txtWarnPassword.setVisibility(View.GONE);
        txtWarnPassRepeat.setVisibility((View.GONE));

        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String country = countriesSpinner.getSelectedItem().toString();
        String gender = "";
        switch (rgGenger.getCheckedRadioButtonId()){
            case R.id.rbMale:
                gender = "Male";
                break;
            case R.id.rbFemale:
                gender = "Female";
            default:
                gender = "Unknown";
                break;
        }
        String snackText = "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Gender: " + gender + "\n" +
                "Cpintry: " + country;
        Log.d(TAG, "showSnackBar: Snack Bar Text:" + snackText);

        Snackbar.make(parent, snackText, Snackbar.LENGTH_INDEFINITE)
                .setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editTextName.setText("");
                        editTextEmail.setText("");
                        editTextpassword.setText("");
                        editTextPassRepeat.setText("");
                    }
                }).show();

    }

    private boolean validateData() {
        Log.d(TAG, "validateDate: started");
        if (editTextName.getText().toString().equals("")) {
            txtWarnName.setVisibility(View.VISIBLE);
            txtWarnName.setText("Name Missing!");
            return false;
        }
        if (editTextEmail.getText().toString().equals("")) {
            txtWarnEmail.setVisibility(View.VISIBLE);
            txtWarnEmail.setText("Email Missing!");
            return false;
        }
        if (editTextpassword.getText().toString().equals("")) {
            txtWarnPassword.setVisibility(View.VISIBLE);
            txtWarnPassword.setText("Password Missing!");
            return false;
        }
        if (editTextPassRepeat.getText().toString().equals("")) {
            txtWarnPassRepeat.setVisibility(View.VISIBLE);
            txtWarnPassRepeat.setText("Confirm Passwword Missing!");
            return false;
        }
        if (!editTextpassword.getText().toString().equals(editTextPassRepeat.getText().toString())) {
            txtWarnPassRepeat.setVisibility(View.VISIBLE);
            txtWarnPassRepeat.setText("Not Matching");
            return false;
        }
        return true;
    }

    private void initViews() {
        Log.d(TAG, "InitViews: started");
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextpassword = findViewById(R.id.editTextPassword);
        editTextPassRepeat = findViewById(R.id.editTextTextPasswRepeat);

        btnPickImages = findViewById(R.id.btnPickImage);
        btnRegister = findViewById(R.id.btnRegister);

        txtWarnName = findViewById(R.id.txtWarnName);
        txtWarnEmail = findViewById(R.id.txtWarnEmail);
        txtWarnPassword = findViewById(R.id.txtWarnPass);
        txtWarnPassRepeat = findViewById(R.id.txtWarnPassrepeat);

        countriesSpinner = findViewById(R.id.spinnerCountry);
        rgGenger = findViewById(R.id.rgGender);
        checkAgree = findViewById(R.id.checkAgreement);
        //rbmale = findViewById(R.id.rbMale);
        //rbFemale = findViewById(R.id.rbFemale);
        parent = findViewById(R.id.parent);
    }
}