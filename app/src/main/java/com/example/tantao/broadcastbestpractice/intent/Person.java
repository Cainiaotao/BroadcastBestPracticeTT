package com.example.tantao.broadcastbestpractice.intent;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tantao on 2016/4/22.
 */
public class Person implements Parcelable {
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
