package com.example.myapp09;

//import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Generated("jsonschema2pojo")
public class Sinhvien implements Parcelable { //da chinh laij so voi ban dau de su dung noi bo

@SerializedName("Id")
@Expose
private String id;
@SerializedName("taikhoan")
@Expose
private String taikhoan;
@SerializedName("matkhau")
@Expose
private String matkhau;
@SerializedName("hinhanh")
@Expose
private String hinhanh;

    protected Sinhvien(Parcel in) {
        id = in.readString();
        taikhoan = in.readString();
        matkhau = in.readString();
        hinhanh = in.readString();
    }

    public static final Creator<Sinhvien> CREATOR = new Creator<Sinhvien>() {
        @Override
        public Sinhvien createFromParcel(Parcel in) {
            return new Sinhvien(in);
        }

        @Override
        public Sinhvien[] newArray(int size) {
            return new Sinhvien[size];
        }
    };

    public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getTaikhoan() {
return taikhoan;
}

public void setTaikhoan(String taikhoan) {
this.taikhoan = taikhoan;
}

public String getMatkhau() {
return matkhau;
}

public void setMatkhau(String matkhau) {
this.matkhau = matkhau;
}

public String getHinhanh() {
return hinhanh;
}

public void setHinhanh(String hinhanh) {
this.hinhanh = hinhanh;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(taikhoan);
        parcel.writeString(matkhau);
        parcel.writeString(hinhanh);
    }
}