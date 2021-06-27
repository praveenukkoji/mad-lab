package com.example.signinsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    public Crendentials crendentials;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        EditText email;
        EditText password;

        TextView login;

        Button signup;

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        login = findViewById(R.id.login);

        signup = findViewById(R.id.signup);

        crendentials = new Crendentials();

        sharedPreferences = getApplication().getSharedPreferences("CrendentialsDB", MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        if(sharedPreferences != null){

            Map<String, ?> preferencesMap = sharedPreferences.getAll();

            if(preferencesMap.size() != 0){
                crendentials.loadCrendentials(preferencesMap);
            }
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String input_email = email.getText().toString();
                String input_password = password.getText().toString();

                if(validate(input_email, input_password)){

                    if(crendentials.checkUsername(input_email)){
                        Toast.makeText(RegistrationActivity.this, "Email already exists.", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        crendentials.addCrendentials(input_email, input_password);

                        sharedPreferencesEditor.putString(input_email, input_password);

                        sharedPreferencesEditor.putString("LastSavedEmail", input_email);
                        sharedPreferencesEditor.putString("LastSavedPassword", input_password);

                        sharedPreferencesEditor.apply();


                        Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    boolean validate(String email, String password){
        if(email.isEmpty()){
            Toast.makeText(RegistrationActivity.this, "Enter Email.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(password.isEmpty()){
            Toast.makeText(RegistrationActivity.this, " Enter Password.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(password.length() < 8){
            Toast.makeText(RegistrationActivity.this, " Password should be min 8 characters.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}