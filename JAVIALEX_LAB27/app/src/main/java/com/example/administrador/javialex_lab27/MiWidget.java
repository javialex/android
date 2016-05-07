package com.example.administrador.javialex_lab27;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

/**
 * Created by Administrador on 30/04/2016.
 */
public class MiWidget extends AppWidgetProvider {
    public static String SP_NOMBRE = "SP_NOMBRE";
    public static int REQUEST_CODE = 1;
    public final static String SP_WIDGET_NOMBRE = "SP_WIDGET_NOMBRE";
    public final static String SP_WIDGET_APELLIDO = "SP_WIDGET_APELLIDO";
    public final static String NAME_SPACE = "com.example.administrador.javialex_lab27.ACTUALIZAR_WIDGET";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (int i = 0; i < appWidgetIds.length; i++) {
            int windgetId = appWidgetIds[i];
            actualizarWidgets(context, appWidgetManager, windgetId);
        }
    }

    public static void actualizarWidgets(Context context, AppWidgetManager appWidgetManager, int widgetId) {
        SharedPreferences pref = context.getSharedPreferences(SP_NOMBRE, Context.MODE_PRIVATE);
        //Para acceder a las propiedades de los Views es necesario usar remoteviews
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.mi_widget);
        //Le pasamos una llave unica, el nombre del paquete actual
        Intent intent = new Intent(NAME_SPACE);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
        //Un intent que está pendiente, esperando
        //ctrl + p para ver lo que se necesita de parametro
        //ctrl + q para ver detalle
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //En vez de OnClickListener
        views.setOnClickPendingIntent(R.id.btnActualizar, pendingIntent);
        String nombre = pref.getString(SP_WIDGET_NOMBRE, "");
        String apellido = pref.getString(SP_WIDGET_APELLIDO, "");
        views.setTextViewText(R.id.txvNombre, nombre + System.currentTimeMillis());
        views.setTextViewText(R.id.txvApellido, apellido + System.currentTimeMillis());
        appWidgetManager.updateAppWidget(widgetId, views);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction().equals(NAME_SPACE)) {
            int widgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
            AppWidgetManager widgetManager = AppWidgetManager.getInstance(context);
            //Si el id obtenido es diferente al id inválido
            if (widgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
                actualizarWidgets(context, widgetManager, widgetId);
            }
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {

        SharedPreferences prefs = context.getSharedPreferences(SP_NOMBRE, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(SP_WIDGET_NOMBRE);
        editor.remove(SP_WIDGET_APELLIDO);
        editor.commit();
        super.onDeleted(context, appWidgetIds);
    }
}
