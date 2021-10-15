package com.example.myapp09.Retrofit2;
//dung de cung cap duong dan ra
public class APIUtils {
    public static final String Base_Url = "http://10.0.0.113/qlsv/";

    public static DataClient getData() {
        return RetrofitClient.getClient(Base_Url).create(DataClient.class);
    }
}
