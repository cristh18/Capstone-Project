package com.udacitynanodegree.cristhian.capstoneproject.persistence.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.udacitynanodegree.cristhian.capstoneproject.R;

public class IronHideProvider extends ContentProvider {

    private UriMatcher uriMatcher;
    private IronHideDBHelper ironHideDBHelper;

    static final int USER = 100;
    static final int VEHICLE = 200;
    static final int CATEGORY = 300;
    static final int PART = 400;
    static final int DEFAULT_VEHICLE = 500;

    private String pathUser;
    private String pathVehicle;
    private String pathCategory;
    private String pathPart;
    private String pathDefaultVehicle;

    @Override
    public boolean onCreate() {
        uriMatcher = buildUriMatcher();
        init();
        ironHideDBHelper = new IronHideDBHelper(getContext());
        return true;
    }

    private void init() {
        pathUser = getContext().getString(R.string.path_user_table);
        pathVehicle = getContext().getString(R.string.path_vehicle_table);
        pathCategory = getContext().getString(R.string.path_part_category_table);
        pathPart = getContext().getString(R.string.path_part_table);
        pathDefaultVehicle = getContext().getString(R.string.path_default_vehicle_table);


    }

    private UriMatcher buildUriMatcher() {

        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = DatabaseContract.getContentAuthority(getContext());

        matcher.addURI(authority, DatabaseContract.getPathUserTable(getContext()), USER);
        matcher.addURI(authority, DatabaseContract.getPathVehicleTable(getContext()), VEHICLE);
        matcher.addURI(authority, DatabaseContract.getPathPartVehicleTable(getContext()), CATEGORY);
        matcher.addURI(authority, DatabaseContract.getPathPartTable(getContext()), PART);
        matcher.addURI(authority, DatabaseContract.getPathDefaultVehicleTable(getContext()), DEFAULT_VEHICLE);
        return matcher;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor;

        switch (uriMatcher.match(uri)) {
            case USER: {
                retCursor = ironHideDBHelper.getReadableDatabase().query(pathUser, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            }
            case VEHICLE: {
                retCursor = ironHideDBHelper.getReadableDatabase().query(pathVehicle, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            }

            case CATEGORY: {
                retCursor = ironHideDBHelper.getReadableDatabase().query(pathCategory, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            }

            case PART: {
                retCursor = ironHideDBHelper.getReadableDatabase().query(pathPart, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            }

            case DEFAULT_VEHICLE: {
                retCursor = ironHideDBHelper.getReadableDatabase().query(pathDefaultVehicle, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = uriMatcher.match(uri);

        switch (match) {
            case USER:
                return ironHideDBHelper.getContentType(pathUser);
            case VEHICLE:
                return ironHideDBHelper.getContentType(pathVehicle);
            case CATEGORY:
                return ironHideDBHelper.getContentType(pathCategory);
            case PART:
                return ironHideDBHelper.getContentType(pathPart);
            case DEFAULT_VEHICLE:
                return ironHideDBHelper.getContentType(pathDefaultVehicle);

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        final SQLiteDatabase db = ironHideDBHelper.getWritableDatabase();
        final int match = uriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case USER: {
                long _id = db.insert(pathUser, null, values);
                if (_id > 0) {
                    returnUri = ironHideDBHelper.buildUri(ironHideDBHelper.getContentUri(pathUser), _id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            case VEHICLE: {
                long _id = db.insert(pathVehicle, null, values);
                if (_id > 0) {
                    returnUri = ironHideDBHelper.buildUri(ironHideDBHelper.getContentUri(pathVehicle), _id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            case CATEGORY: {
                long _id = db.insert(pathCategory, null, values);
                if (_id > 0) {
                    returnUri = ironHideDBHelper.buildUri(ironHideDBHelper.getContentUri(pathCategory), _id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            case PART: {
                long _id = db.insert(pathPart, null, values);
                if (_id > 0) {
                    returnUri = ironHideDBHelper.buildUri(ironHideDBHelper.getContentUri(pathPart), _id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        db.close();
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = ironHideDBHelper.getWritableDatabase();
        final int match = uriMatcher.match(uri);
        int rowsDeleted;

        if (null == selection) selection = "1";
        switch (match) {
            case USER:
                rowsDeleted = db.delete(pathUser, selection, selectionArgs);
                break;
            case VEHICLE:
                rowsDeleted = db.delete(pathVehicle, selection, selectionArgs);
                break;
            case CATEGORY:
                rowsDeleted = db.delete(pathCategory, selection, selectionArgs);
                break;
            case PART:
                rowsDeleted = db.delete(pathPart, selection, selectionArgs);
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = ironHideDBHelper.getWritableDatabase();
        final int match = uriMatcher.match(uri);
        int rowsUpdated;
        switch (match) {
            case USER:
                rowsUpdated = db.update(pathUser, values, selection, selectionArgs);
                break;
            case VEHICLE:
                rowsUpdated = db.update(pathVehicle, values, selection, selectionArgs);
                break;
            case CATEGORY:
                rowsUpdated = db.update(pathCategory, values, selection, selectionArgs);
                break;
            case PART:
                rowsUpdated = db.update(pathPart, values, selection, selectionArgs);
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }

}
