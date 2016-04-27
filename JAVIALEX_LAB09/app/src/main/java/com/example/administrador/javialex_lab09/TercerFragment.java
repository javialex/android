package com.example.administrador.javialex_lab09;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class TercerFragment extends Fragment{
    private CuadradoView cdvwCuadrado1;

    public TercerFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View containerView = inflater.inflate(R.layout.fragment_primer,container,false);
        cdvwCuadrado1 = (CuadradoView) containerView.findViewById(R.id.cdvwCuadrado1);
        cdvwCuadrado1.setColorCuadrado(Color.GREEN);
        cdvwCuadrado1.setColorLabel(Color.GRAY);
        cdvwCuadrado1.setStringLabel("HACER CLICK AQUI");
        cdvwCuadrado1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cdvwCuadrado1.isOnTrue()) {
                    cdvwCuadrado1.setColorCuadrado(Color.BLUE);
                    cdvwCuadrado1.setColorLabel(Color.YELLOW);
                    cdvwCuadrado1.setStringLabel("Okasdokas!!!!");
                    cdvwCuadrado1.setOnTrue(false);
                } else {
                    cdvwCuadrado1.setColorCuadrado(Color.GREEN);
                    cdvwCuadrado1.setColorLabel(Color.BLACK);
                    cdvwCuadrado1.setStringLabel("Okassss!!!!");
                    cdvwCuadrado1.setOnTrue(true);
                }


            }
        });
        return containerView;
    }
}
