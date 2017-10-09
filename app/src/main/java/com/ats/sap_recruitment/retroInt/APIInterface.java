package com.ats.sap_recruitment.retroInt;

import com.ats.sap_recruitment.bean.LoginBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {


    @POST("get_login")
    Call<LoginBean> getLoginDetails(@Query("frm_mode") String frm_mode, @Query("user_mobile") String user_mobile, @Query("user_password") String user_password);
}
