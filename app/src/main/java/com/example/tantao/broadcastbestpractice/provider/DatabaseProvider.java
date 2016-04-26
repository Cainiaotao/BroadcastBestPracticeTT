package com.example.tantao.broadcastbestpractice.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.tantao.broadcastbestpractice.database.MyDatabaseHelper;

/**
 * Created by tantao on 2016/4/18.
 */
public class DatabaseProvider extends ContentProvider {

    public static final int Book_DIR=0;
    public static final int Book_ITEM=1;
    public static final int CATEGORY_DIR=2;
    public static final int CATEGORY_ITEM=3;

    public static final String AUTHORTIY="com.example.tantao.broadcastbestpractice.provider";
    private static UriMatcher uriMatcher;
    private MyDatabaseHelper dbHelper;

    static {
        uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORTIY,"book",Book_DIR);
        uriMatcher.addURI(AUTHORTIY,"book/#",Book_ITEM);
        uriMatcher.addURI(AUTHORTIY,"category",CATEGORY_DIR);
        uriMatcher.addURI(AUTHORTIY,"category/#",CATEGORY_ITEM);
    }

    @Override
    public boolean onCreate() {
        dbHelper=new MyDatabaseHelper(getContext(),"BookStore.db",null,2);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
