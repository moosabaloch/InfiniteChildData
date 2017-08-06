package com.moosa.unlimitedtodo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Moosa on 05/08/2017.
 * moosa.bh@gmail.com
 */

public class TodoItem implements Parcelable {
    private String name;
    private String id;
    private ArrayList<TodoItem> childItems;

    public TodoItem(String name,String id) {
        this.name = name;
        this.id = id;
        this.childItems = new ArrayList<>();
    }

    public ArrayList<TodoItem> getChildItems() {
        return childItems;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.id);
        dest.writeList(this.childItems);
    }

    protected TodoItem(Parcel in) {
        this.name = in.readString();
        this.id = in.readString();
        this.childItems = new ArrayList<TodoItem>();
        in.readList(this.childItems, TodoItem.class.getClassLoader());
    }

    public static final Parcelable.Creator<TodoItem> CREATOR = new Parcelable.Creator<TodoItem>() {
        @Override
        public TodoItem createFromParcel(Parcel source) {
            return new TodoItem(source);
        }

        @Override
        public TodoItem[] newArray(int size) {
            return new TodoItem[size];
        }
    };
}
