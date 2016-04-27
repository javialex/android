package com.example.android.javialex_lab03;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private Camera camera;
    private boolean isEncendido;
    private boolean isFlashcamera;
    private Camera.Parameters param;
    private ToggleButton tbtFlashCamera;
    private final String TAG = "LAB03";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate iniciado exitosamente!");
        setContentView(R.layout.activity_main);
        isFlashcamera = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        if(!isFlashcamera){
            /*Se crea un dialogo acá*/
            AlertDialog alert = new AlertDialog.Builder(this).create();
            alert.setTitle("Error");
            alert.setMessage("Comprate otro celular");
            alert.setButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    /*Destruye la actividad*/
                    finish();
                }
            });
            alert.show();
        }
        getCamera();
        tbtFlashCamera = (ToggleButton) findViewById(R.id.tbtSwitch);
        tbtFlashCamera.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    encenderLuz();
                }else{
                    apagarLuz();
                }
            }
        });
    }
    private void getCamera(){
        if(camera==null){
            try{
                camera  = Camera.open();
                param = camera.getParameters();
            }catch(Exception ex){
                /*Android ya cuenta con un api para el manejo de logs*/
                Log.e(TAG,ex.getMessage());
            }
        }
    }
    private void encenderLuz(){
        /*Verificamos si la camara está encendida*/
        if(!isEncendido){
            if(camera==null || param == null){
                return;
            }
            param = camera.getParameters();
            /*Volvemos a poner a la cámara con los parámetros requeridos para encenderse*/
            param.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(param);
            camera.startPreview();
            isEncendido=true;
        }
    }
    private void apagarLuz(){
        /*Verificamos si la camara está encendida*/
        if(isEncendido){
            if(camera==null || param == null){
                return;
            }
            param = camera.getParameters();
            /*Volvemos a poner a la cámara con los parámetros requeridos para encenderse*/
            param.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(param);
            camera.stopPreview();
            isEncendido=false;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        apagarLuz();
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*En caso de que no se haya inicializado la cámara, la volvemos a inicializar*/
        getCamera();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(camera!=null){
            /*Terminamos la cámara*/
            camera.release();
            apagarLuz();
        }
    }
}
