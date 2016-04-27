package com.and104.cognos.lab17.util;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by aescobar on 14/4/2016.
 */
public class MiContentProvider extends ContentProvider{

    public static final String NOMBRE_PROVIDER = "com.and104.cognos.lab17";
    public static final String URL = "content://"+ NOMBRE_PROVIDER + "/empleados";
    public static final Uri CONTENT_URI = Uri.parse(URL);

    public static final String NOMBRE_BASE = "LAB17";
    public static final String ID = "id";
    public static final String NOMBRE = "nombre";
    public static final String DATO = "dato";

    static final int EMPLEADO = 1;
    static final int EMPLEADO_ID = 2;

    private DBHelper dbHeleper;
    private SQLiteDatabase db;

    static final int VERSION = 1;

    static final String TAG = "MiContentProvider";

    private static HashMap<String, String> empeladosMap;

    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(NOMBRE_PROVIDER, "empleados", EMPLEADO);
        uriMatcher.addURI(NOMBRE_PROVIDER, "empleados/#", EMPLEADO_ID);
    }


    private static class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
                String query = "CREATE TABLE empleados (id integer primary key autoincrement, nombre text, dato text);";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String query = "DROP TABLE IF EXISTS empleados";
            db.execSQL(query);
            onCreate(db);
        }
    }





    @Override
    public boolean onCreate() {
        Context context = getContext();
        dbHeleper = new DBHelper(context, NOMBRE_BASE, null, VERSION);
        db = dbHeleper.getWritableDatabase();
        if(db == null)
            return false;
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables("empleados");
        switch (uriMatcher.match(uri)){
            case EMPLEADO:
                queryBuilder.setProjectionMap(empeladosMap);
                break;
            case EMPLEADO_ID:
                queryBuilder.appendWhere(ID + "="+ uri.getLastPathSegment());
                break;
            default:
                Log.e(TAG, "Error Seleccionar Url NO existe" + uri);

        }
        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case EMPLEADO:
                return "vnd.android.cursor.dir/vnd.ejemplo.empleados";
            case EMPLEADO_ID:
                return "vnd.android.cursor.item/vnd.ejemplo.empleados";
            default:
                Log.e(TAG, "Url NO existe" + uri);

        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Long id = db.insert("empleados", "", values);
        if(id > 0){
            Uri nuevaUri = ContentUris.withAppendedId(CONTENT_URI, id);
            getContext().getContentResolver().notifyChange(nuevaUri, null);
            return nuevaUri;
        }
        Log.e(TAG, "Error al insertar");
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int num = 0;
        switch (uriMatcher.match(uri)){
            case EMPLEADO:
               num = db.delete("empleados",selection,selectionArgs);
                break;
            case EMPLEADO_ID:
                String id = uri.getLastPathSegment();
                num = db.delete("empleados", ID + "=" + id + (!TextUtils.isEmpty(selection) ? "AND ("+ selection+")" : ""), selectionArgs);
                break;
            default:
                Log.e(TAG, "Error Eliminar Url NO existe" + uri);

        }
        return num;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int num = 0;
        switch (uriMatcher.match(uri)){
            case EMPLEADO:
                num = db.update("empleados", values, selection, selectionArgs);
                break;
            case EMPLEADO_ID:
                String id = uri.getLastPathSegment();
                String nuevaSelectiom = ID + " = " + id;
                if(!TextUtils.isEmpty(selection))
                    nuevaSelectiom += " AND (" + selection + " )";
                num = db.update("empleados", values, nuevaSelectiom, selectionArgs);
                break;
            default:
                Log.e(TAG, "Error Update Url NO existe" + uri);

        }
        return num;
    }
}
