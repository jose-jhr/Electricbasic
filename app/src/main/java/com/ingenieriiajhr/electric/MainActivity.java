package com.ingenieriiajhr.electric;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.ingenieriiajhr.animation.RotateView;
import com.ingenieriiajhr.data.DataOptions;
import com.ingenieriiajhr.electric.basicos.ConceptosBasicos;
import com.ingenieriiajhr.electric.calculadora.Calculadroa;
import com.ingenieriiajhr.electric.conexionbluetooth.BluetoothConnect;
import com.ingenieriiajhr.electric.databinding.ActivityMainBinding;
import com.ingenieriiajhr.electric.instalacioneselectricas.InstalacionesElectrica;
import com.ingenieriiajhr.gridview.OptAdapter;
import com.rajat.pdfviewer.PdfViewerActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    private RotateView rotateView;

    //data show gridview
    private DataOptions dataOptions = new DataOptions();

    private OptAdapter optionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.color_base));

        //option adapter
        optionsAdapter = new OptAdapter(this, dataOptions.textOpt(), dataOptions.numberOpt());

        //rotate logo
        rotateLogo();

        //adapter grid list
        binding.gridViewSelect.setAdapter(optionsAdapter);

        //expanded grid view
        binding.gridViewSelect.setExpanded(true);

        binding.imgLogo.setOnClickListener(this);

        //select elements ops
        elementsOpsClick();

    }

    private void elementsOpsClick() {
        binding.gridViewSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        startActivities(new Intent[]{new Intent(getApplicationContext(), ConceptosBasicos.class)});
                        break;
                    case 1:
                        startActivity(new Intent(getApplicationContext(), InstalacionesElectrica.class));
                        break;
                    case 2:
                        alertSelectView();
                        break;

                    case 3:
                        startActivities(new Intent[]{PdfViewerActivity.Companion.launchPdfFromPath(getApplicationContext(), "medicionparametros.pdf", "Medicion de parametros electricos", "assets", false, true)});
                        break;

                    case 4:
                        startActivities(new Intent[]{PdfViewerActivity.Companion.launchPdfFromPath(getApplicationContext(), "fotovoltaico.pdf", "Medicion de parametros electricos", "assets", false, true)});
                        break;

                    case 5:
                        alertSelectPractic();
                        break;
                }
            }
        });
    }

    private void alertSelectPractic() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.select_calculate);
        dialog.setTitle("Practicas");
        Button btnCalculadora = dialog.findViewById(R.id.btnCalculadora);
        Button btnDocument = dialog.findViewById(R.id.btnDocument);

        btnCalculadora.setText("Conexión a módulo");
        btnDocument.setText("Documento");


        btnCalculadora.setOnClickListener(view -> {
            dialog.dismiss();
            startActivity(new Intent(this, BluetoothConnect.class));
        });

        btnDocument.setOnClickListener(view -> {
            dialog.dismiss();
            calculateDocumentOpen("practicas.pdf", "practicas");
        });

        dialog.show();
    }

    private void alertSelectView() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.select_calculate);
        dialog.setTitle("Calculator");
        Button btnCalculadora = dialog.findViewById(R.id.btnCalculadora);
        Button btnDocument = dialog.findViewById(R.id.btnDocument);

        btnCalculadora.setOnClickListener(view -> {
            dialog.dismiss();
            startActivity(new Intent(this, Calculadroa.class));
        });

        btnDocument.setOnClickListener(view -> {
            dialog.dismiss();
            calculateDocumentOpen("calculadora.pdf", "calculadora");
        });

        dialog.show();


    }


    /**
     * open document calculate
     */
    private void calculateDocumentOpen(String path, String tittle) {
        startActivities(new Intent[]{PdfViewerActivity.Companion.launchPdfFromPath(getApplicationContext(), path, tittle, "assets", false, true)});
    }


    /**
     * rotate logo
     */
    private void rotateLogo() {
        //init objecti animation
        rotateView = new RotateView(this, binding.imgLogo);
        rotateView.rotateView();
    }


    @Override
    public void onClick(View view) {
        if (binding.imgLogo == view) {
            rotateView.rotateView();
        }
    }
}