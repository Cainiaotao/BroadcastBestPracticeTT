package com.example.tantao.broadcastbestpractice;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tantao.broadcastbestpractice.database.MyDatabaseHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SqlActivity extends AppCompatActivity {

    @InjectView(R.id.createsqlBtn)
    Button createsqlBtn;
    @InjectView(R.id.bookname)
    EditText bookname;
    @InjectView(R.id.bookauthor)
    EditText bookauthor;
    @InjectView(R.id.bookprice)
    EditText bookprice;
    @InjectView(R.id.bookpage)
    EditText bookpage;
    @InjectView(R.id.addBtn)
    Button addBtn;
    @InjectView(R.id.updateBtn)
    Button updateBtn;
    @InjectView(R.id.findBtn)
    Button findBtn;

    private MyDatabaseHelper dbHepler;
    private SQLiteDatabase db;
    private ContentValues contentValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        ButterKnife.inject(this);

        dbHepler = new MyDatabaseHelper(this, "BookStore.db", null, 2);
        contentValues = new ContentValues();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    private void adddatabase(String name, String author, int page, float price) {
        db = dbHepler.getWritableDatabase();
        //ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("author", author);
        contentValues.put("pages", page);
        contentValues.put("price", price);
        db.insert("Book", null, contentValues);
        contentValues.clear();
    }

    private void updatedatabase(){
        contentValues.put("price",11.2);
        db.update("Book", contentValues, "name=?", new String[]{"diba"});
        Toast.makeText(this, "price update successful", Toast.LENGTH_SHORT).show();
    }

    private void deteledata(){
        // db.delete();
        Toast.makeText(this,"delete successful",Toast.LENGTH_SHORT).show();
    }

    private void finddatabase(){
        db = dbHepler.getWritableDatabase();
        Cursor cursor=db.query("Book",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do{
                String name=cursor.getString(cursor.getColumnIndex("name"));
                String author=cursor.getString(cursor.getColumnIndex("author"));
                int page=cursor.getInt(cursor.getColumnIndex("pages"));
                float price=cursor.getFloat(cursor.getColumnIndex("price"));

                Log.d("MainActivit", "Book name:" + name);
                Log.d("MainActivit","Book author:"+author);
                Log.d("MainActivit","Book pages:"+page);
                Log.d("MainActivit","Book price:"+price);

            }while (cursor.moveToNext());
        }
        cursor.close();
    }

    private void clearEditText()
    {
        bookname.setText("");
        bookauthor.setText("");
        bookprice.setText("");
        bookpage.setText("");
    }



    @OnClick({R.id.createsqlBtn, R.id.addBtn, R.id.updateBtn, R.id.findBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.createsqlBtn:
                dbHepler.getWritableDatabase();
                break;
            case R.id.addBtn:
                String name=bookname.getText().toString();
                String author=bookauthor.getText().toString();
                String page=bookpage.getText().toString();
                String price=bookprice.getText().toString();
                int pa=0;
                float pr=0;

                if (!page.isEmpty()&&!price.isEmpty()){
                    pa=Integer.valueOf(page);
                    pr=Float.parseFloat(price);
                }else {
                    pa=0;
                    pr=0;
                }

                if (!name.isEmpty()&&!author.isEmpty())
                {
                    adddatabase(name,author,pa,pr);
                    Toast.makeText(this,"Add successful",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this,"Book name and author not empty!",Toast.LENGTH_SHORT).show();
                }

                clearEditText();
                break;
            case R.id.updateBtn:
                updatedatabase();
                break;
            case R.id.findBtn:
                finddatabase();
                break;
        }
    }
}
