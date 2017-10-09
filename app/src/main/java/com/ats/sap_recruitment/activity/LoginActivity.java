package com.ats.sap_recruitment.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.bean.LoginBean;
import com.ats.sap_recruitment.retroInt.APIClient;
import com.ats.sap_recruitment.retroInt.APIInterface;
import com.ats.sap_recruitment.utils.Constants;
import com.google.gson.Gson;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private Button btnSignIn;
    private TextView tvForgotPassword;
    private EditText edUsername, edPassword;
    private TextInputLayout textUsername, textPassword;
    private APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        apiInterface = APIClient.getClient().create(APIInterface.class);

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
                if (edUsername.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "please enter email or phone", Toast.LENGTH_SHORT).show();
                    edUsername.requestFocus();
                } else if (edPassword.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "please enter password", Toast.LENGTH_SHORT).show();
                    edPassword.requestFocus();
                } else {

                    final AlertDialog dialog = new SpotsDialog(LoginActivity.this);
                    dialog.show();

                    Call<LoginBean> loginBeanCall = apiInterface.getLoginDetails("get_login", edUsername.getText().toString(), edPassword.getText().toString());
                    loginBeanCall.enqueue(new Callback<LoginBean>() {
                        @Override
                        public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {

                            LoginBean loginBean = response.body();
                            Log.e(TAG, "onResponse: btnSignIn.setOnClickListener" + loginBean);
                            if (loginBean != null) {
                                dialog.dismiss();
                                if (loginBean.getStatus().equals("success")) {
                                    SharedPreferences pref = getApplicationContext().getSharedPreferences(Constants.myPref, MODE_PRIVATE);
                                    SharedPreferences.Editor editor = pref.edit();
                                    Gson gson = new Gson();
                                    String json = gson.toJson(loginBean);
                                    editor.putString("loginBean", json);
                                    editor.commit();

                                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                    finish();

                                } else {
                                    dialog.dismiss();
                                    Toast.makeText(LoginActivity.this, "Invalid User", Toast.LENGTH_SHORT).show();
                                }


                            } else {
                                dialog.dismiss();
                                Toast.makeText(LoginActivity.this, "Response Timeout, try again ", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginBean> call, Throwable t) {
                            Log.e(TAG, "onFailure: " + t.getMessage());
                            Toast.makeText(LoginActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                        }
                    });


                }
            }
        });

    }
}
