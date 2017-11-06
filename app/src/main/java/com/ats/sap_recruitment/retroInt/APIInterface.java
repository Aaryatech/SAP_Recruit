package com.ats.sap_recruitment.retroInt;

import com.ats.sap_recruitment.bean.ActvityInformation;
import com.ats.sap_recruitment.bean.Categories;
import com.ats.sap_recruitment.bean.CompanyProfile;
import com.ats.sap_recruitment.bean.DashBoardProfile;
import com.ats.sap_recruitment.bean.EduStatusCode;
import com.ats.sap_recruitment.bean.EducationalProfile;
import com.ats.sap_recruitment.bean.LoginBean;
import com.ats.sap_recruitment.bean.PersonProfile;
import com.ats.sap_recruitment.bean.SapProfile;
import com.ats.sap_recruitment.bean.SaveJobBaens;

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

    @FormUrlEncoded
    @POST("Ser_specilization/get_categories")
    Call<Categories> getSpecialisedCategories(@Field("frm_mode") String frm_mode, @Field("user_type") String user_type, @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("Ser_profile/get_dashboard")
    Call<DashBoardProfile> getDashBpardDetials(@Field("frm_mode") String frm_mode, @Field("user_type") String user_type, @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("Ser_profile/save_rec_educational")
    Call<EduStatusCode> saveEducationalDetails(@Field("frm_mode") String frm_mode,
                                               @Field("user_type") String user_type,
                                               @Field("user_id") String user_id,
                                               @Field("prof_edu_course_detail") String prof_edu_course_detail,
                                               @Field("prof_specilalzation") String prof_specilalzation,
                                               @Field("prof_edu_university") String prof_edu_university,
                                               @Field("prof_edu_passing_year") String prof_edu_passing_year,
                                               @Field("prof_edu_grade_range") String prof_edu_grade_range,
                                               @Field("prof_edu_misc_skill_details") String prof_edu_misc_skill_details);

    @FormUrlEncoded
    @POST("Ser_profile/save_rec_personal")
    Call<EduStatusCode> savePersonalDetails(@Field("frm_mode") String frm_mode,
                                            @Field("user_type") String user_type,
                                            @Field("user_id") String user_id,
                                            @Field("prof_fname") String prof_fname,
                                            @Field("prof_mname") String prof_mname,
                                            @Field("prof_lname") String prof_lname,
                                            @Field("prof_dob") String prof_dob,
                                            @Field("prof_curr_location") String prof_curr_location,
                                            @Field("prof_w_status") String prof_w_status,
                                            @Field("prof_w_exp_year") String prof_w_exp_year,
                                            @Field("prof_w_exp_month") String prof_w_exp_month,
                                            @Field("prof_w_like") String prof_w_like,
                                            @Field("prof_full_part_time") String prof_full_part_time,
                                            @Field("prof_curr_salary") String prof_curr_salary,
                                            @Field("prof_company_name") String prof_company_name,
                                            @Field("prof_company_email") String prof_company_email,
                                            @Field("prof_profile_flag") String prof_profile_flag,
                                            @Field("prof_mobile") String prof_mobile,
                                            @Field("prof_alternet_mobile") String prof_alternet_mobile,
                                            @Field("prof_email") String prof_email);

    @FormUrlEncoded
    @POST("Ser_profile_recu/get_categories")
    Call<Categories> getCategoryRecruiter(@Field("frm_mode") String frm_mode);

    @FormUrlEncoded
    @POST("Ser_profile_recu/save_job")
    Call<SaveJobBaens> saveJobProfile(@Field("frm_mode") String frm_mode, @Field("user_type") String user_type, @Field("user_id") String user_id, @Field("job_title") String job_title, @Field("job_desc") String job_desc, @Field("job_exp_year") String job_exp_year, @Field("job_exp_month") String job_exp_month);

    @FormUrlEncoded
    @POST
    Call<CompanyProfile> getCompanyProfile(@Field("frm_mode") String frm_mode, @Field("user_type") String user_type, @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("Ser_specilization/get_activity_data")
    Call<ActvityInformation> getActivityInformation(@Field("frm_mode") String frm_mode, @Field("user_type") String user_type, @Field("user_id") String user_id, @Field("prof_cat_id") String prof_cat_id);

    @FormUrlEncoded
    @POST("Ser_profile/save_sap_profile")
    Call<EduStatusCode> saveSapProfile(@Field("frm_mode") String frm_mode, @Field("user_type") String user_type, @Field("user_id") String user_id, @Field("main_cat_id") String main_cat_id, @Field("sub_cat_id") String sub_cat_id, @Field("sub_sub_cat_id") String sub_sub_cat_id, @Field("act_id") String act_id, @Field("remrk_id") String remrk_id, @Field("exp_year") String exp_year, @Field("exp_month") String exp_month);

    @FormUrlEncoded
    @POST("Ser_specilization/my_sap_profile_list")
    Call<SapProfile> getSapProfile(@Field("frm_mode") String frm_mode, @Field("user_type") String user_type, @Field("user_id") String user_id, @Field("prof_cat_id") String prof_cat_id);

}
