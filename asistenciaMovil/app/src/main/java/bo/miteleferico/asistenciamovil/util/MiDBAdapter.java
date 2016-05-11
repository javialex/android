package bo.miteleferico.asistenciamovil.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import bo.miteleferico.asistenciamovil.obj.EventosAsiMov;

/**
 * Created by Administrador on 08/05/2016.
 */
public class MiDBAdapter {
    private Context context;
    private MiDBHelper miDBHelper;
    private SQLiteDatabase db;
    private static final String NOMBRE_BASE = "asistenciaMovil";
    private static final int VERSION = 2;
    private static final String TAG = "MiDBAdapter";

    public MiDBAdapter(Context context) {
        this.context = context;
        miDBHelper = new MiDBHelper(context, NOMBRE_BASE, null, VERSION);
    }

    public void open(){
        db = miDBHelper.getWritableDatabase();
    }

    public void insertarEventoAsiMov(String eventoMovil, Double latitud, Double longitud, Double radio, Date fechaIni, Time horaIni, Date fechaFin, Time horaFin, int cantMarcacionesDia,
                                     String observacion, int estado, int baja_logica, int agrupador, int userRegId, Date fechaReg, int userModId, Date fechaMod){
        try {
            Log.i(TAG, "Insertando registro de Evento");
            ContentValues cv = new ContentValues();
            cv.put("evento_movil", eventoMovil);
            cv.put("latitud", latitud);
            cv.put("longitud", longitud);
            cv.put("radio", radio);
            cv.put("fecha_ini", String.valueOf(fechaIni));
            cv.put("hora_ini", String.valueOf(horaIni));
            cv.put("fecha_fin", String.valueOf(fechaFin));
            cv.put("hora_fin", String.valueOf(horaFin));
            cv.put("hora_fin", String.valueOf(horaFin));
            cv.put("cant_marcaciones_dia", cantMarcacionesDia);
            cv.put("observacion", observacion);
            cv.put("estado", estado);
            cv.put("baja_logica", baja_logica);
            cv.put("agrupador", agrupador);
            cv.put("user_reg_id", userRegId);
            cv.put("fecha_reg", String.valueOf(fechaReg));
            cv.put("user_mod_id", userModId);
            cv.put("fecha_mod", String.valueOf(fechaMod));
            db.insert("empleados", null, cv);
            Log.i(TAG, "Registro insertado");
        }catch (Exception ex){
            Log.e(TAG, "Error al insertar registro" + ex.getMessage());
        }
    }

    public ArrayList<String> seleccionarEventosAsiMov(){
        try {
            Log.i(TAG, "Seleccionar registros");
            ArrayList<String> eventos = new ArrayList<>();
            Cursor cursor = db.query("eventosasimov", null, null, null, null, null, "evento_movil");
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    eventos.add(cursor.getString(1));
                } while (cursor.moveToNext());
            }
            Log.i(TAG, "Registros obtenidos");
            return eventos;
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
            db.delete("eventosasmov", "id="+id, null);
        }catch(Exception ex){
            Log.e(TAG, "Error al eliminar registros" + ex.getMessage());
        }
    }

    public void update(EventosAsiMov emp){
        try {
            ContentValues values = new ContentValues();
            values.put("evento_movil", emp.getEventoMovil());
            values.put("latitud", emp.getLatitud());
            values.put("longitud", emp.getLongitud());
            values.put("radio", emp.getRadio());
            values.put("fecha_ini", String.valueOf(emp.getFechaIni()));
            values.put("fecha_fin", String.valueOf(emp.getFechaFin()));
            values.put("cant_marcaciones_dia", emp.getCantMarcacionesDia());
            values.put("observacion", emp.getObservacion());
            //values.put("area", emp.getAreea());
            db.update("eventosasimov", values, "id=?",new String[]{String.valueOf(emp.getId())});
        }catch(Exception ex){
            Log.e(TAG, "Error al actualizar registros" + ex.getMessage());
        }
    }

    public class MiDBHelper extends SQLiteOpenHelper {

        public MiDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
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

}
