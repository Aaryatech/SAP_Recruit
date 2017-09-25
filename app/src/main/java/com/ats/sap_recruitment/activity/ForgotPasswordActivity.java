package com.ats.sap_recruitment.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ats.sap_recruitment.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText edEmail;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        edEmail = (EditText) findViewById(R.id.edForgotPassEmail);
        btnSubmit = (Button) findViewById(R.id.btnForgotPassSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edEmail.getText().toString().isEmpty()) {
                    Toast.makeText(ForgotPasswordActivity.this, "please enter email first", Toast.LENGTH_SHORT).show();
                    edEmail.requestFocus();
                } else {
                    Toast.makeText(ForgotPasswordActivity.this, "password mailed to your email", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
