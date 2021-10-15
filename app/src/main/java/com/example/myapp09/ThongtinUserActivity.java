package com.example.myapp09;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ThongtinUserActivity extends AppCompatActivity {
    //khai bao
    Button btnDelete;
    TextView txtTaikhoan, txtMatkhau;
    ImageView imgUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtin_user);
        //anh xa
        anhxa();
        //khoi tao
        init();



    }

    private void init() {
        //Lay thong tin ve tu man hinh Main
        Intent intent = getIntent();
        ArrayList<Sinhvien> sinhvienArrayList = intent.getParcelableArrayListExtra("mangsinhvien");
        //Log.d("BBB", sinhvienArrayList.get(0).getTaikhoan());
        //Log.d("BBB", sinhvienArrayList.get(0).getMatkhau());
        txtTaikhoan.setText(sinhvienArrayList.get(0).getTaikhoan());
        txtMatkhau.setText(sinhvienArrayList.get(0).getMatkhau());

    }

    private void anhxa() {
        btnDelete = findViewById(R.id.scr03btnDelete);
        txtTaikhoan = findViewById(R.id.scr03txtvUser);
        txtMatkhau = findViewById(R.id.scr03txtvPass);
        imgUser = findViewById(R.id.scr03imageviewuser);
    }
}
