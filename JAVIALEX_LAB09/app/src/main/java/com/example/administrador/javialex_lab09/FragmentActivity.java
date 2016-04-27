package com.example.administrador.javialex_lab09;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {
    View btnFrag1;
    Button btnFrag2;
    Button btnFrag3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        btnFrag1 = findViewById(R.id.btnPrimerFragment);
        btnFrag2 = (Button) findViewById(R.id.btnSegundoFragment);
        btnFrag3 = (Button) findViewById(R.id.btnTercerFragment);
        btnFrag1.setOnClickListener(this);
        btnFrag2.setOnClickListener(this);
        btnFrag3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()) {
            case R.id.btnPrimerFragment:
                fragment = new PrimerFragment();
                break;
            case R.id.btnSegundoFragment:
                fragment = new SegundoFragment();
                break;
            case R.id.btnTercerFragment:
                fragment = new TercerFragment();
                break;
        }
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frmPrincipal,fragment).commit();
    }
}
