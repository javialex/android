package bo.miteleferico.asistenciamovil.util;

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
 * Created by Administrador on 11/05/2016.
 */
public class EventosAsiMovContentProvider  extends ContentProvider {
    public static final String NOMBRE_PROVIDER = "bo.miteleferico.asistenciamovil";
    public static final String URL = "content://"+ NOMBRE_PROVIDER + "/eventosmoviles";
    public static final Uri CONTENT_URI = Uri.parse(URL);

    public static final String NOMBRE_BASE = "bd_asistenciamovil";
    public static final String ID = "id";
    public static final String EVENTO_MOVIL = "evento_movil";
    public static final String LATITUD = "latitud";
    public static final String LONGITUD = "longitud";
    public static final String RADIO = "radio";
    public static final String FECHA_INI = "fecha_ini";
    public static final String FECHA_FIN = "fecha_fin";
    public static final String CANT_MARCACIONES_DIA = "cant_marcaciones_dia";
    public static final String OBSERVACION = "observacion";

    static final int EVENTO = 1;
    static final int EVENTO_ID = 2;

    private DBHelper dbHeleper;
    private SQLiteDatabase db;

    static final int VERSION = 1;

    static final String TAG = "EventosAsiMovContentProvider";

    private static HashMap<String, String> eventosAsiMovMap;

    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(NOMBRE_PROVIDER, "eventosmoviles", EVENTO);
        uriMatcher.addURI(NOMBRE_PROVIDER, "eventosmoviles/#", EVENTO_ID);
    }


    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE eventosasimov (id integer primary key autoincrement, evento_movil text, latitud integer,longitud integer,radio integer,fecha_ini datetime,hora_ini text,fecha_fin datetime,hora_fin text,cant_marcaciones_dia integer,observacion text, estado integer, baja_logica integer,agrupador integer,user_reg_id integer, fecha_reg datetime, user_mod_id integer, fecha_mod datetime);";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String query = "DROP TABLE IF EXISTS eventosasimov";
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
            case EVENTO:
                queryBuilder.setProjectionMap(eventosAsiMovMap);
                break;
            case EVENTO_ID:
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
            case EVENTO:
                return "vnd.android.cursor.dir/vnd.asistenciamovil.eventosasimov";
            case EVENTO_ID:
                return "vnd.android.cursor.item/vnd.asistenciamovil.eventosasimov";
            default:
                Log.e(TAG, "Url NO existe" + uri);

        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Long id = db.insert("eventosasimov", "", values);
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
            case EVENTO:
                num = db.delete("eventosasimov",selection,selectionArgs);
                break;
            case EVENTO_ID:
                String id = uri.getLastPathSegment();
                num = db.delete("eventosasimov", ID + "=" + id + (!TextUtils.isEmpty(selection) ? "AND ("+ selection+")" : ""), selectionArgs);
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
            case EVENTO:
                num = db.update("eventosasimov", values, selection, selectionArgs);
                break;
            case EVENTO_ID:
                String id = uri.getLastPathSegment();
                String nuevaSelectiom = ID + " = " + id;
                if(!TextUtils.isEmpty(selection))
                    nuevaSelectiom += " AND (" + selection + " )";
                num = db.update("eventosasimov", values, nuevaSelectiom, selectionArgs);
                break;
            default:
                Log.e(TAG, "Error Update Url NO existe" + uri);

        }
        return num;
    }

}
