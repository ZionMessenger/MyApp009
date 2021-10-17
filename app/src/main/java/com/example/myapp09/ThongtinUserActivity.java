package com.example.myapp09;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapp09.Retrofit2.APIUtils;
import com.example.myapp09.Retrofit2.DataClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongtinUserActivity extends AppCompatActivity {
    //khai bao
    Button btnDelete;
    TextView txtTaikhoan, txtMatkhau;
    ImageView imgUser;
    ArrayList<Sinhvien> sinhvienArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtin_user);
        //anh xa
        anhxa();
        //khoi tao
        init();
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namefolder = sinhvienArrayList.get(0).getHinhanh();
                namefolder = namefolder.substring(namefolder.lastIndexOf("/")); //thêm "/" vào chuỗi hình
                //Log.d("BBB",namefolder);

                DataClient dataClient = APIUtils.getData();
                Call<String> callback = dataClient.DeleteData(sinhvienArrayList.get(0).getId(),namefolder);
                callback.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String ketqua = response.body();
                        if (ketqua.equals("Success")){
                            Log.d("BBB","OK");
                            finish();
                        }else {
                            Log.d("BBB","Fail");
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });



            }
        });



    }

    private void init() {
        //Lay thong tin ve tu man hinh Main
        Intent intent = getIntent();
        sinhvienArrayList = intent.getParcelableArrayListExtra("mangsinhvien");
        //Log.d("BBB", sinhvienArrayList.get(0).getTaikhoan());
        //Log.d("BBB", sinhvienArrayList.get(0).getMatkhau());
        txtTaikhoan.setText(sinhvienArrayList.get(0).getTaikhoan());
        txtMatkhau.setText(sinhvienArrayList.get(0).getMatkhau());
        Picasso.get().load(sinhvienArrayList.get(0).getHinhanh()).into(imgUser);

    }

    private void anhxa() {
        btnDelete = findViewById(R.id.scr03btnDelete);
        txtTaikhoan = findViewById(R.id.scr03txtvUser);
        txtMatkhau = findViewById(R.id.scr03txtvPass);
        imgUser = findViewById(R.id.scr03imageviewuser);
    }
}
