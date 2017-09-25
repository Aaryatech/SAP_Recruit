package com.ats.sap_recruitment.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ats.sap_recruitment.R;

public class LoginActivity extends AppCompatActivity {

    private Button btnSignIn;
    private TextView tvForgotPassword;
    private EditText edUsername, edPassword;
    private TextInputLayout textUsername, textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "Free_Serif.ttf");
        Typeface myTypefaceBold = Typeface.createFromAsset(getAssets(), "Free_Serif.ttf");

        textUsername = (TextInputLayout) findViewById(R.id.textUsername);
        textPassword = (TextInputLayout) findViewById(R.id.textPassword);

        textUsername.setTypeface(myTypeface);
        textPassword.setTypeface(myTypeface);

        edUsername = (EditText) findViewById(R.id.edLoginUsername);
        edPassword = (EditText) findViewById(R.id.edLoginPassword);

        edUsername.setTypeface(myTypeface);
        edPassword.setTypeface(myTypeface);


        tvForgotPassword = (TextView) findViewById(R.id.tvForgotPass);
        tvForgotPassword.setTypeface(myTypefaceBold);

        btnSignIn = (Button) findViewById(R.id.btnLoginSignIn);
        btnSignIn.setTypeface(myTypefaceBold);

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (edUsername.getText().toString().isEmpty()) {
//                    Toast.makeText(LoginActivity.this, "please enter email or phone", Toast.LENGTH_SHORT).show();
//                    edUsername.requestFocus();
//                } else if (edPassword.getText().toString().isEmpty()) {
//                    Toast.makeText(LoginActivity.this, "please enter password", Toast.LENGTH_SHORT).show();
//                    edPassword.requestFocus();
//                } else {
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish();
                // }
            }
        });

    }
}
