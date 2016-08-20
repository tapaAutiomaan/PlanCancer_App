package com.plancancer.plancancernews.persistance.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

import com.plancancer.plancancernews.core_services.loggers.NewsToaster;
import com.plancancer.plancancernews.persistance.model.NewsElements;

/**
 * Created by Assou on 02/08/2015.
 */
public class PlanCancerDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private NewsToaster newsToaster;

    private static final String DATABASE_NAME="jiltarjihnews";
    private static final String TABLE_NAME="jiltarjihnews";
    private static final int DATABASE_VERSION=9;


    /*Columns description*/
    private static final String NID ="_id";
    private static final String TITLE="title";
    private static final String SUBTITLE="subtitle";
    private static final String DESCRIPTION ="text";
    private static final String URL_IMAGE="urlimage";
    private static final String PUBLISH_DATE="publishdate";


    /*Database queries*/
    //private static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("+ NID +" INTEGER PRIMARY KEY AUTOINCREMENT ,"+TITLE+" VARCHAR(255) ,"+SUBTITLE+" TEXT , "+URL_IMAGE+" TEXT , "+ DESCRIPTION +" TEXT);";
    private static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("+ NID +" INTEGER PRIMARY KEY AUTOINCREMENT ,"+TITLE+" VARCHAR(255) ,"+ DESCRIPTION +" TEXT ,"+ PUBLISH_DATE +" DATETIME , "+ URL_IMAGE + " TEXT);";
    private static final String DROP_TABLE="DROP TABLE  IF EXISTS "+TABLE_NAME;



    public PlanCancerDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
        this.newsToaster=new NewsToaster(this.context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
            this.newsToaster.printMessage("OnCreateCalled");
        }catch (SQLException e){
            this.newsToaster.printMessage(e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            this.newsToaster.printMessage("onUpgrade Called");
            db.execSQL(DROP_TABLE);
            this.onCreate(db);
        }catch (SQLException e){
            this.newsToaster.printMessage(e.getMessage());

        }

    }


    public void insertRandomNews(){
        ContentValues values=new ContentValues();
        values.put(TITLE,"السلام عليكم");
        values.put(DESCRIPTION,"هذا محتوى تجريبي");
        long id=this.getWritableDatabase().insert(this.TABLE_NAME,null,values);
    }

    public long insertNews(String title,String description,String datePub){
        ContentValues values=new ContentValues();
        values.put(TITLE,"السلام عليكم");
        values.put(DESCRIPTION,"هذا محتوى تجريبي");
        long id=this.getWritableDatabase().insert(this.TABLE_NAME,null,values);
        return 0;
    }



    public long insertNewsElements(NewsElements elements){
        ContentValues values=new ContentValues();
        values.put(TITLE,elements.getTitle());
        values.put(DESCRIPTION,elements.getDescription());
        values.put(URL_IMAGE,elements.getImageUrl());
        values.put(PUBLISH_DATE,elements.getPubDate());
        return this.getWritableDatabase().insert(this.TABLE_NAME,null,values);
    }


    public boolean isExistInDataBase(String title){
        String columns[]={TITLE};
        Cursor cursor=this.getReadableDatabase().query(TABLE_NAME,null, PlanCancerDatabaseHelper.TITLE + "='" +title +"'",null,null,null,null);
        String st="";
        if(cursor.moveToNext())
            st=cursor.getString(1);
        cursor.close();
        return (!st.equals(""));

    }

    public void deleteAllElements(){
        long cursor=this.getWritableDatabase().delete(TABLE_NAME, null, null);
    }

    public void deleteElments(String ch){

        long cursor=this.getWritableDatabase().delete(TABLE_NAME, TITLE + "=?", new String[]{ch});
    }

    public ArrayList<NewsElements> getContent(){
        ArrayList<NewsElements> list=new ArrayList<>();
        String columns[]={TITLE,DESCRIPTION,URL_IMAGE,PUBLISH_DATE};
        Cursor cursor=this.getReadableDatabase().query(TABLE_NAME,columns,null,null,null,null,null);
        while(cursor.moveToNext()){
            NewsElements newsElements=new NewsElements();
            newsElements.setTitle(cursor.getString(0));
            newsElements.setPubDate(cursor.getString(3));
            newsElements.setImageUrl(cursor.getString(2));
            newsElements.setDescription(cursor.getString(1));
            list.add(newsElements);
        }
        cursor.close();
        return list;

    }

    public void showContent(){

        String columns[]={NID,TITLE,DESCRIPTION};
        Cursor cursor=this.getReadableDatabase().query(TABLE_NAME,null,null,null,null,null,null);
        StringBuffer buffer=new StringBuffer();
        while(cursor.moveToNext()){
            int id=cursor.getInt(0);
            String titre=cursor.getString(1);
            String txt=cursor.getString(4);
            buffer.append(id+" "+titre+" "+" "+txt+"\n");
        }
        cursor.close();

        Toast.makeText(this.context, buffer, Toast.LENGTH_LONG).show();
    }


    /*
    public void insertRandomNews(){
        ContentValues values=new ContentValues();
        values.put(TITLE,"Le premier element dans la base");
        values.put(SUBTITLE,"تحت العنوان الأول");
        values.put(URL_IMAGE,"www.google.fr");
        values.put(DESCRIPTION,"");
        long id=this.getWritableDatabase().insert(this.TABLE_NAME,null,values);
    }*/

/*    public void showContent(){
        String columns[]={NID,TITLE,SUBTITLE,URL_IMAGE, DESCRIPTION};
        Cursor cursor=this.getReadableDatabase().query(TABLE_NAME,null,null,null,null,null,null);
        StringBuffer buffer=new StringBuffer();
        while(cursor.moveToNext()){
            int id=cursor.getInt(0);
            String titre=cursor.getString(1);
            String sb=cursor.getString(2);
            String img=cursor.getString(3);
            String txt=cursor.getString(4);
            buffer.append(id+" "+titre+" "+sb+" "+img+" "+txt+"\n");
        }

        cursor.close();

        Toast.makeText(this.context,buffer,Toast.LENGTH_LONG).show();
    }*/

}
