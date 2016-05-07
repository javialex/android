package com.example.administrador.javialex_lab30;

import android.content.Intent;

import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by Administrador on 05/05/2016.
 */
public class ActualizarTokenService extends InstanceIDListenerService {
    @Override
    public void onTokenRefresh() {
        Intent intent = new Intent(this, RegistroAppService.class);
        startService(intent);

    }
}
