package com.ats.sap_recruitment.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.ats.sap_recruitment.R;

public class RegisterationActivity extends AppCompatActivity {

    private EditText edName, edMobile, edEmail, edPassword;
    private Button btnSubmit;
    private TextView tvSignIn;
    private CheckBox cbRegister;
    private TextInputLayout textName, textMobile, textEmail, textPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "Free_Serif.ttf");
        Typeface myTypefaceBold = Typeface.createFromAsset(getAssets(), "Free_Serif.ttf");

        textName = (TextInputLayout) findViewById(R.id.textFirstName);
        textEmail = (TextInputLayout) findViewById(R.id.textEmail);
        textMobile = (TextInputLayout) findViewById(R.id.textMobile);
        textPassword = (TextInputLayout) findViewById(R.id.textPassword);

        textName.setTypeface(myTypeface);
        textMobile.setTypeface(myTypeface);
        textEmail.setTypeface(myTypeface);
        textPassword.setTypeface(myTypeface);

        edName = (EditText) findViewById(R.id.edRegisterName);
        edMobile = (EditText) findViewById(R.id.edRegisterMobile);
        edEmail = (EditText) findViewById(R.id.edRegisterEmail);
        edPassword = (EditText) findViewById(R.id.edRegisterPassword);

        edName.setTypeface(myTypefaceBold);
        edMobile.setTypeface(myTypefaceBold);
        edEmail.setTypeface(myTypefaceBold);
        edPassword.setTypeface(myTypefaceBold);

        cbRegister = (CheckBox) findViewById(R.id.cbRegister);
        cbRegister.setTypeface(myTypefaceBold);

        btnSubmit = (Button) findViewById(R.id.btnRegisterJoinNow);
        btnSubmit.setTypeface(myTypefaceBold);

        tvSignIn = (TextView) findViewById(R.id.tvRegisterSignIn);
        tvSignIn.setTypeface(myTypefaceBold);

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterationActivity.this, LoginActivity.class));
                finish();
            }
        });

    }
}
