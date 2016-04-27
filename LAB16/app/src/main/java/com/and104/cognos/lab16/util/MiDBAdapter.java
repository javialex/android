package com.and104.cognos.lab16.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.and104.cognos.lab16.obj.Empleado;

import java.util.ArrayList;

/**
 * Created by aescobar on 13/4/2016.
 */
public class MiDBAdapter {
    private Context context;
    private MiDBHelper miDBHelper;
    private SQLiteDatabase db;
    private static final String NOMBRE_BASE = "LAB16";
    private static final int VERSION = 2;
    private static final String TAG = "MiDBAdapter";

    public MiDBAdapter(Context context) {
        this.context = context;
        miDBHelper = new MiDBHelper(context, NOMBRE_BASE, null, VERSION);
    }

    public void open(){
        db = miDBHelper.getWritableDatabase();
    }

    public void insertarEmpleado(String nombre, int area){
        try {
            Log.i(TAG, "Insertando registros");
            ContentValues cv = new ContentValues();
            cv.put("nombre", nombre);
            cv.put("area", area);
            db.insert("empleados", null, cv);
            Log.i(TAG, "Registro insertado");
        }catch (Exception ex){
            Log.e(TAG, "Error al insertar registro" + ex.getMessage());
        }
    }

    public ArrayList<String> seleccionarEmpleados(){
        try {
            Log.i(TAG, "Seleccionar registros");
            ArrayList<String> emepleados = new ArrayList<>();
            Cursor cursor = db.query("empleados", null, null, null, null, null, "nombre");
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    emepleados.add(cursor.getString(1));
                } while (cursor.moveToNext());
            }
            Log.i(TAG, "Registros obtenidos");
            return emepleados;
        }catch (Exception ex){
            Log.e(TAG, "Error al leer registro" + ex.getMessage());
            return  new ArrayList<>();
        }
    }

    public void eliminarSistemas(){
        try {
            db.delete("empleados", "area=1", null);
        }catch(Exception ex){
            Log.e(TAG, "Error al eliminar registros" + ex.getMessage());
        }
    }

    public void eliminar(int id){
        try {
            db.delete("empleados", "id="+id, null);
        }catch(Exception ex){
            Log.e(TAG, "Error al eliminar registros" + ex.getMessage());
        }
    }

    public void update(Empleado emp){
        try {
            ContentValues values = new ContentValues();
            values.put("nombre", emp.getNombre());
            values.put("area", emp.getAreea());
            db.update("empleados", values, "id=?",new String[]{String.valueOf(emp.getId())});
        }catch(Exception ex){
            Log.e(TAG, "Error al actualizar registros" + ex.getMessage());
        }
    }





    public class MiDBHelper extends SQLiteOpenHelper{

        public MiDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE empleados (id integer primary key autoincrement, nombre text, area integer);";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String query = "DROP TABLE IF EXISTS empleados";
            db.execSQL(query);
            onCreate(db);
        }
    }

}
