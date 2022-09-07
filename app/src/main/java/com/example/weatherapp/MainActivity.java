package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    MaterialButton btnLogin;
    TextInputEditText edtUserName,edtUserPassword;
    private String userName="1";
    private String userPassword="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUserName=findViewById(R.id.edtUserName);
        edtUserPassword=findViewById(R.id.edtUserPassword);
        btnLogin=findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(edtUserName.getText().toString().isEmpty()&&edtUserPassword.getText().toString().isEmpty())){
                    if (edtUserName.getText().toString().equals(userName)&&edtUserPassword.getText().toString().equals(userPassword)){
                        Intent i=new Intent(MainActivity.this,WeatherActivity.class);
                        startActivity(i);
                    }else {
                        Toast.makeText(MainActivity.this, "UserName and password are not correct4", Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(MainActivity.this, "UserName and password field can not be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}