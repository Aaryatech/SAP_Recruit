package com.ats.sap_recruitment.retroInt;

import com.ats.sap_recruitment.bean.EducationalProfile;
import com.ats.sap_recruitment.bean.LoginBean;
import com.ats.sap_recruitment.bean.PersonProfile;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIInterface {

    @FormUrlEncoded
    @POST("Ser_authen/get_login")
    Call<LoginBean> getLoginDetails(@Field("frm_mode") String frm_mode, @Field("user_mobile") String user_mobile, @Field("user_password") String user_password);

    @FormUrlEncoded
    @POST("Ser_profile/get_personal")
    Call<PersonProfile> getPersonalDetail(@Field("frm_mode") String frm_mode, @Field("user_type") String user_type, @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("Ser_profile/get_education")
    Call<EducationalProfile> getEducationalDetails(@Field("frm_mode") String frm_mode, @Field("user_type") String user_type, @Field("user_id") String user_id);

}
