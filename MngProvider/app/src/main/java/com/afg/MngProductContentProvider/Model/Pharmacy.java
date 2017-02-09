package com.afg.MngProductContentProvider.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by joselu on 11/01/17.
 */

public class Pharmacy implements Parcelable {
    long id;
    String cif;
    String address;
    String phone;
    private String name;
    private String email;

    public Pharmacy() {

        this.cif = "";
        this.name = "";
        this.address = "";
        this.phone = "";
        this.email = "";

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pharmacy(int id, String cif, String address, String phone) {
        this.id = id;
        this.cif = cif;
        this.address = address;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public String getCif() {
        return cif;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.cif);
        dest.writeString(this.address);
        dest.writeString(this.phone);
        dest.writeString(this.name);
        dest.writeString(this.email);
    }

    protected Pharmacy(Parcel in) {
        this.id = in.readLong();
        this.cif = in.readString();
        this.address = in.readString();
        this.phone = in.readString();
        this.name = in.readString();
        this.email = in.readString();
    }

    public static final Parcelable.Creator<Pharmacy> CREATOR = new Parcelable.Creator<Pharmacy>() {
        @Override
        public Pharmacy createFromParcel(Parcel source) {
            return new Pharmacy(source);
        }

        @Override
        public Pharmacy[] newArray(int size) {
            return new Pharmacy[size];
        }
    };

    @Override
    public boolean equals(Object obj) {

        boolean result = false;

        if(obj != null){

            if(obj instanceof Pharmacy){

                result = this.id == ((Pharmacy) obj).getId();
            }
        }

        return result;
    }
}
