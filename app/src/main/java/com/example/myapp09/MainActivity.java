package com.example.myapp09;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapp09.Retrofit2.APIUtils;
import com.example.myapp09.Retrofit2.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btndangky, btndangnhap;
    EditText edtTk, edtMk;
    String taikhoan;
    String matkhau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //chuyen doi manh hinh Main qua man hinh Dangky
                Intent intent = new Intent( MainActivity.this, DangkyActivity.class);
                startActivity(intent);
            }
        });
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taikhoan = edtTk.getText().toString();
                matkhau = edtMk.getText().toString();
                if (taikhoan.length() > 0 && matkhau.length() > 0){
                    //gui du lieu di
                    DataClient dataClient = APIUtils.getData();
                    Call<List<Sinhvien>> callback = dataClient.Logindata(taikhoan,matkhau);
                    //nhan du lieu tra ve
                    callback.enqueue(new Callback<List<Sinhvien>>() {
                        @Override
                        public void onResponse(Call<List<Sinhvien>> call, Response<List<Sinhvien>> response) {
                            ArrayList<Sinhvien> mangsinhvien = (ArrayList<Sinhvien>) response.body();
                            if (mangsinhvien.size() > 0) {
                                //Log.d("BBB", mangsinhvien.get(0).getTaikhoan());
                                //Log.d("BBB", mangsinhvien.get(0).getMatkhau());
                                //Log.d("BBB", mangsinhvien.get(0).getHinhanh());
                                Intent intent = new Intent(MainActivity.this, ThongtinUserActivity.class); // chuyển màn hình
                                intent.putExtra("mangsinhvien",mangsinhvien);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Sinhvien>> call, Throwable t) {
                            Toast.makeText( MainActivity.this, "Khong ton tai", Toast.LENGTH_SHORT);
                            Log.d("BBB", "FAIL");
                        }
                    });
                }
            }
        });
    }

    private void anhxa() {
        btndangky = findViewById(R.id.buttonRegister);
        btndangnhap = findViewById(R.id.buttonLogin);
        edtTk = findViewById(R.id.edittexttaikhoan);
        edtMk = findViewById(R.id.edittextmatkhau);
    }
}
