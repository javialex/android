package bo.miteleferico.asistenciamovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Administrador on 06/05/2016.
 */
public class MainAsiMovActivity extends AppCompatActivity {
    private LoginAsiMovActivity loginAsiManActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_asi_mov);
        loginAsiManActivity = (LoginAsiMovActivity) findViewById(R.id.viewLoguin);
        loginAsiManActivity.setOnClickPersonalizado(new OnClickLoguin() {
            @Override
            public void validarDatos(String user, String pass) {
                Toast toast = Toast.makeText(getApplicationContext(), user, Toast.LENGTH_SHORT);
                if (user.equalsIgnoreCase("") || pass.equalsIgnoreCase("")) {
                    loginAsiManActivity.setMensaje("Debe introducir ambos valores necesariamente");
                    toast.show();
                } else {
                    loginAsiManActivity.setMensaje("Los datos introducidos son: " + user + " - " + pass);
                    Intent i = new Intent(MainAsiMovActivity.this, MenuAsiMovActivity.class);
                    MainAsiMovActivity.this.startActivity(i);
                }
            }
        });

    }
}
