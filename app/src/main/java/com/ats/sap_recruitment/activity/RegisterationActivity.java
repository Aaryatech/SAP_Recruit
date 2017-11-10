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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.bean.RegistratinBean;
import com.ats.sap_recruitment.retroInt.APIClient;
import com.ats.sap_recruitment.retroInt.APIInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterationActivity extends AppCompatActivity {

    private static final String TAG ="RegisterationActivity" ;
    @BindView(R.id.textRegFirstName)
    TextInputLayout textName;
    @BindView(R.id.textRegLastName)
    TextInputLayout textLastName;
    @BindView(R.id.textEmail)
    TextInputLayout textEmail;
    @BindView(R.id.textMobile)
    TextInputLayout textMobile;
    @BindView(R.id.textPassword)
    TextInputLayout textPassword;

    @BindView(R.id.edRegFirstName)
    EditText edFirstName;
    @BindView(R.id.edRegLastName)
    EditText edLastName;
    @BindView(R.id.edRegisterMobile)
    EditText edMobile;
    @BindView(R.id.edRegisterEmail)
    EditText edEmail;
    @BindView(R.id.edRegisterPassword)
    EditText edPassword;

    @BindView(R.id.rbRecruiter)
    RadioButton rbRecruiter;
    @BindView(R.id.rbJobSeeker)
    RadioButton rbJobSeeker;


    @BindView(R.id.cbRegister)
    CheckBox cbRegister;
    @BindView(R.id.btnRegisterJoinNow)
    Button btnSubmit;
    @BindView(R.id.tvRegisterSignIn)
    TextView tvSignIn;

    private APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        ButterKnife.bind(this);

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "Free_Serif.ttf");
        Typeface myTypefaceBold = Typeface.createFromAsset(getAssets(), "Free_Serif.ttf");


        textName.setTypeface(myTypeface);
        textLastName.setTypeface(myTypeface);
        textMobile.setTypeface(myTypeface);
        textEmail.setTypeface(myTypeface);
        textPassword.setTypeface(myTypeface);
        edFirstName.setTypeface(myTypefaceBold);
        edLastName.setTypeface(myTypefaceBold);
        edMobile.setTypeface(myTypefaceBold);
        edEmail.setTypeface(myTypefaceBold);
        edPassword.setTypeface(myTypefaceBold);
        cbRegister.setTypeface(myTypefaceBold);
        btnSubmit.setTypeface(myTypefaceBold);
        tvSignIn.setTypeface(myTypefaceBold);


        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterationActivity.this, LoginActivity.class));
                finish();
            }
        });

    }

    @OnClick(R.id.btnRegisterJoinNow)
    public void getRegisterUser() {
        boolean validData = true;

        String fname = edFirstName.getText().toString();
        String lname = edLastName.getText().toString();
        String mobileNo = edMobile.getText().toString();
        String emailId = edEmail.getText().toString();
        String password = edPassword.getText().toString();
        String radioSeleted = "-1";


        if (rbJobSeeker.isChecked()) {
            radioSeleted = "1";
        } else if (rbRecruiter.isChecked()) {
            radioSeleted = "0";
        } else {
            validData = false;
            Toast.makeText(this, "please select JobSeeker/Recruiter", Toast.LENGTH_SHORT).show();
        }
        if (!cbRegister.isChecked()) {
            validData = false;
            Toast.makeText(this, "Please Accept terms & condition", Toast.LENGTH_SHORT).show();
        }

        if (fname.equalsIgnoreCase("")) {
            edFirstName.setError("required");
            edFirstName.requestFocus();
            validData = false;
        }
        if (lname.equalsIgnoreCase("")) {
            edLastName.setError("required");
            edLastName.requestFocus();
            validData = false;
        }
        if (mobileNo.equalsIgnoreCase("")) {
            edMobile.setError("Mobile No required");
            edMobile.requestFocus();
            validData = false;
        }
        if (password.equalsIgnoreCase("")) {
            edPassword.setError("please enter password");
            edPassword.requestFocus();
            validData = false;
        }
        if (emailId.equalsIgnoreCase("")) {
            edEmail.setError("please enter Email Address");
            edEmail.requestFocus();
            validData = false;
        }

        if (validData) {
            final AlertDialog dialog = new SpotsDialog(this);
            dialog.show();
            final Call<RegistratinBean> registratinBeanCall = apiInterface.getRegister("", fname, lname, mobileNo, emailId, password, radioSeleted);
            registratinBeanCall.enqueue(new Callback<RegistratinBean>() {
                @Override
                public void onResponse(Call<RegistratinBean> call, Response<RegistratinBean> response) {
                    dialog.dismiss();
                    if (response.body() != null) {
                        RegistratinBean registratinBean = response.body();
                        String status = registratinBean.getStatus();
                        if (status.equalsIgnoreCase("success")) {
                            Toast.makeText(RegisterationActivity.this, "User Successfully Register", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterationActivity.this, LoginActivity.class));
                            finish();
                        } else if (status.equalsIgnoreCase("mobile_exist")) {
                            Toast.makeText(RegisterationActivity.this, "Mobile No Already Exist", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterationActivity.this, "Failed to Register", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterationActivity.this, "No valid response from server ", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RegistratinBean> call, Throwable t) {
                    dialog.dismiss();
                    Log.e(TAG, "onFailure: "+t.getMessage());
                    Toast.makeText(RegisterationActivity.this, "Server Connection Error", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }
}
