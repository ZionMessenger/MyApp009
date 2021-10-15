package com.example.myapp09.Retrofit2;

import com.example.myapp09.Sinhvien;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

// giao tiếp data
public interface DataClient {
    // phuong thuc lang nghe va gui du lieu cho server
    //phan gui file
    @Multipart
    @POST("uploadhinh.php")
    Call<String> UploadPhoto(@Part MultipartBody.Part photo);

    //phan gui data
    @FormUrlEncoded
    @POST("insert.php")
    Call<String> InsertData(@Field("taikhoan") String taikhoan,
                            @Field("matkhau") String matkhau,
                            @Field("hinhanh") String hinhanh);

    //phan nhan data login
    @FormUrlEncoded
    @POST("login.php")
    Call<List<Sinhvien>> Logindata(@Field("taikhoan") String taikhoan,
                                   @Field("matkhau") String matkhau);
}
