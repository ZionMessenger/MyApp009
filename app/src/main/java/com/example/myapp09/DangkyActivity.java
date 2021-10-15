package com.example.myapp09;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapp09.Retrofit2.APIUtils;
import com.example.myapp09.Retrofit2.DataClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangkyActivity extends AppCompatActivity {
    ImageView imgdangky;
    EditText edtUsername, edtPassword;
    Button btnHuy, btnDangky;
    int Request_Code_Image = 123;
    String realpath;
    String taikhoan;
    String matkhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        anhxa();
        imgdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, Request_Code_Image);
            }
        });
        // sau khi them getRealPathFromURI
        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //load
                taikhoan = edtUsername.getText().toString();
                matkhau = edtPassword.getText().toString();

                if(taikhoan.length() > 0 && matkhau.length() > 0){
                    //phan upload image
                    File file = new File(realpath);
                    String file_path = file.getAbsolutePath(); //tim duong dan tuyet doi
                    //Log.d("BBB", file_path); // test thu
                    String[] mangtenfile = file_path.split( "\\.");
                    file_path = mangtenfile[0] + System.currentTimeMillis() + "." + mangtenfile[1];
                    //Log.d("BBB", file_path); // test thu
                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);

                    MultipartBody.Part body = MultipartBody.Part.createFormData("upload_file", file_path, requestBody);

                    DataClient dataClient = APIUtils.getData(); //tao ket noi va tra du lieu ve
                    Call<String> callback = dataClient.UploadPhoto(body); //tra ve 2 su kien
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response != null) {
                                String message = response.body();
                                //Log.d("BBB", message); // test thu
                                DataClient insertdata = APIUtils.getData(); //nhan du lieu tra ve
                                Call<String> callback = insertdata.InsertData(taikhoan,matkhau,APIUtils.Base_Url + "image/" + message);
                                callback.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {
                                        String result = response.body(); //lay du lieu trong body
                                        if (result.equals("Success")){
                                            Toast.makeText(DangkyActivity.this,"THem Thanh Cong",Toast.LENGTH_SHORT);
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.d("BBB", t.getMessage()); // test thu
                        }
                    });

                } else {
                    Toast.makeText( DangkyActivity.this, "Hay Nhap Thong Tin",Toast.LENGTH_SHORT);
                }

            }
        });

    }
    //cho ket qua Img tra ve


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //
        if(requestCode == Request_Code_Image && resultCode == RESULT_OK && data != null  ) {
            Uri uri = data.getData();
            realpath = getRealPathFromURI(uri); //sau khi lay real path
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgdangky.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
        //
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void anhxa() {
        imgdangky = findViewById(R.id.scr02Image);
        edtPassword = findViewById(R.id.scr01edittextmk);
        edtUsername = findViewById(R.id.scr01edittexttk);
        btnDangky =findViewById(R.id.scr01btnXacNhan);
        btnHuy = findViewById(R.id.scr01btnBack);
    }
    // ket noi duong dan de lay duong dan thuc, nho import ve

    public String getRealPathFromURI (Uri contentUri) {
        String path = null;
        String[] proj = { MediaStore.MediaColumns.DATA };
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }
}
