package com.example.signinsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private boolean isValid = false;

    public Crendentials crendentials;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    int attempt = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText email;
        EditText password;

        CheckBox rememberme;

        TextView register;

        Button signin;

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        rememberme = findViewById(R.id.rememberme);

        register = findViewById(R.id.register);

        signin = findViewById(R.id.signup);

        crendentials = new Crendentials();

        sharedPreferences = getApplicationContext().getSharedPreferences("CrendentialsDB", MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        if(sharedPreferences != null){

            Map<String, ?> preferencesMap = sharedPreferences.getAll();

            if(preferencesMap.size() != 0){
                crendentials.loadCrendentials(preferencesMap);
            }

            String savedEmail = sharedPreferences.getString("LastSavedEmail", "");
            String savedPassword = sharedPreferences.getString("LastSavedPassword", "");

            if(sharedPreferences.getBoolean("RememberMe", false)){
                email.setText(savedEmail);
                password.setText(savedPassword);
                rememberme.setChecked(true);
            }
        }

        rememberme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferencesEditor.putBoolean("RememberMe", rememberme.isChecked());
                sharedPreferencesEditor.apply();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String input_email = email.getText().toString();
                String input_password = password.getText().toString();
                
                if(input_email.isEmpty() || input_password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter all crendentials.", Toast.LENGTH_SHORT).show();
                }
                else{
                    isValid = validate(input_email, input_password);

                    if(isValid && attempt > 0){
                        sharedPreferencesEditor.apply();

                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                    else{
//                        Toast.makeText(MainActivity.this, "Incorrect crendentials.", Toast.LENGTH_SHORT).show();
                        if (attempt > 0)
                            attempt = attempt - 1;
                        Toast.makeText(MainActivity.this, "SignIn Attempts: " + attempt + " left.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    boolean validate(String email, String password){
        return crendentials.verifyCrendentials(email, password);
    }
}