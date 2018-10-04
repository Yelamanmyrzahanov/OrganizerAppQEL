package kz.djunglestones.organizerappqel.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Orders implements Parcelable{
    String username,email,orderNumber,date;
    int price;

    public Orders(String username, String email, String orderNumber, String date, int price) {
        this.username = username;
        this.email = email;
        this.orderNumber = orderNumber;
        this.date = date;
        this.price = price;
    }

    protected Orders(Parcel in) {
        username = in.readString();
        email = in.readString();
        orderNumber = in.readString();
        date = in.readString();
        price = in.readInt();
    }

    public static final Creator<Orders> CREATOR = new Creator<Orders>() {
        @Override
        public Orders createFromParcel(Parcel in) {
            return new Orders(in);
        }

        @Override
        public Orders[] newArray(int size) {
            return new Orders[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(email);
        dest.writeString(orderNumber);
        dest.writeString(date);
        dest.writeInt(price);
    }
}
