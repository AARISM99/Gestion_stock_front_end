package com.example.scolarite.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Categories implements  ICategories, Parcelable {
  private int id;
  private String designation;

  public Categories(int id, String designation) {
    this.id = id;
    this.designation = designation;
  }

  public Categories(String designation) {
    this.designation = designation;
  }

  public Categories() {
  }

  protected Categories(Parcel in) {
    id = in.readInt();
    designation = in.readString();
  }

  public static final Creator<Categories> CREATOR = new Creator<Categories>() {
    @Override
    public Categories createFromParcel(Parcel in) {
      return new Categories(in);
    }

    @Override
    public Categories[] newArray(int size) {
      return new Categories[size];
    }
  };

  @Override
  public  String toString(){
    return this.designation;
  }

  @Override
  public int getId() {
    return this.id;
  }

  @Override
  public String getDesignation() {
    return this.designation;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeInt(id);
    parcel.writeString(designation);
  }
}
