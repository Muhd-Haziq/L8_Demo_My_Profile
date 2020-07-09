package sg.edu.rp.c346.i.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etGPA;
    RadioGroup rgGender;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);
        btnSave = findViewById(R.id.buttonSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Step 1a: Get the user input from the EditText and store it in a variable
                String strName = etName.getText().toString();
                float floatGPA = Float.parseFloat(etGPA.getText().toString());
                int gCheck = rgGender.getCheckedRadioButtonId();

                // Step 1b: Obtain an instance of the SharedPreferences
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                // Step 1c: Obtain an instance of the SharedPreference Editor for update later
                SharedPreferences.Editor prefEdit = prefs.edit();

                // Step 1d: Add the key-value pair
                prefEdit.putString("name", strName);
                prefEdit.putFloat("gpa", floatGPA);
                prefEdit.putInt("gender", gCheck);

                // Step 1e: Call commit() to save the changes into SharedPreferences
                prefEdit.commit();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();


    }

    @Override
    protected void onResume() {
        super.onResume();

        // Step 2a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // Step 2b: Retrieve the saved data with the key from the SharedPreference object
        String name = prefs.getString("name", "");
        float gpa = prefs.getFloat("gpa", 0);
        int gender = prefs.getInt("gender", 0);

        etName.setText(name);
        etGPA.setText("" + gpa);
        rgGender.check(gender);
    }




}
