package bo.miteleferico.asistenciamovil;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrador on 06/05/2016.
 */
public class LoginAsiMovActivity extends LinearLayout {
    private EditText etxUsuario;
    private EditText etxPassword;
    private Button btnIniciarSesion;
    private TextView txvResultado;
    private OnClickLoguin listener;

    public LoginAsiMovActivity(Context context) {
        super(context);
        Init();
    }

    public LoginAsiMovActivity(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init();
    }

    private void Init() {
        String service = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(service);
        layoutInflater.inflate(R.layout.activity_login_asi_mov, this, true);
        etxUsuario = (EditText) findViewById(R.id.etxUsuario);
        etxPassword = (EditText) findViewById(R.id.etxPassword);
        btnIniciarSesion = (Button) findViewById(R.id.btnIniciarSesion);
        txvResultado = (TextView) findViewById(R.id.txvResultado);
        asignarEventos();
    }

    public void setOnClickPersonalizado(OnClickLoguin l) {
        listener = l;
    }

    public void setMensaje(String mensaje) {
        txvResultado.setText(mensaje);
    }

    private void asignarEventos() {
        btnIniciarSesion.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.validarDatos(etxUsuario.getText().toString(), etxPassword.getText().toString());
            }
        });
    }
}
