package com.udacitynanodegree.cristhian.capstoneproject.persistence.database;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import com.udacitynanodegree.cristhian.capstoneproject.R;

import java.io.File;

public class IronHideDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ironhide.db";
    private Context context;

    public IronHideDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("@TAG", "ONCREATE DATABSE");
        db.execSQL(context.getString(R.string.create_table_user));
        db.execSQL(context.getString(R.string.create_table_vehicle));
        db.execSQL(context.getString(R.string.create_table_category));
        db.execSQL(context.getString(R.string.create_table_part));
        db.execSQL(context.getString(R.string.create_table_default_vehicle));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(context.getString(R.string.drop_table, context.getString(R.string.path_user_table)));
        db.execSQL(context.getString(R.string.drop_table, context.getString(R.string.path_vehicle_table)));
        db.execSQL(context.getString(R.string.drop_table, context.getString(R.string.path_part_category_table)));
        db.execSQL(context.getString(R.string.drop_table, context.getString(R.string.path_part_table)));
        db.execSQL(context.getString(R.string.drop_table, context.getString(R.string.path_default_vehicle_table)));
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public Uri getContentUri(String path) {
//        return Uri.parse(context.getString(R.string.prefix_base_content_uri).concat(context.getString(R.string.content_authority)))
//                .buildUpon().appendPath(path).build();

        return DatabaseContract.getBaseContentUri(context).buildUpon().appendPath(path).build();
//        return null;
    }

    public String getContentType(String path) {
//        return ContentResolver.CURSOR_DIR_BASE_TYPE.concat(File.separator).concat(context.getString(R.string.content_authority)).concat(File.separator).concat(path);

        return ContentResolver.CURSOR_DIR_BASE_TYPE.concat(File.separator).concat(DatabaseContract.getContentAuthority(context)).concat(File.separator).concat(path);

//        return null;
    }

    public Uri buildUri(Uri uri, long id) {
        return ContentUris.withAppendedId(uri, id);
    }
}