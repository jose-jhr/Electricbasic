package com.ingenieriiajhr.electric.conexionbluetooth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.ingenieriajhr.blujhr.BluJhr;
import com.ingenieriiajhr.electric.R;

import java.util.ArrayList;
import java.util.List;

public class BluetoothConnect extends AppCompatActivity {
    BluJhr blue;
    TextView terminal,dispositivos;
    Switch switch_intervalometro;
    Button btn_desconectar,btn_conectar,btn_clear;


    ArrayList<String> listDevice = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_connect);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.color_base));

        ids();
        terminal.setMovementMethod(new ScrollingMovementMethod());
        barColorStatus();
        blue = new BluJhr(this);
        blue.onBluetooth();

        btn_conectar.setOnClickListener(v -> {
            alertDevice();
        });
        btn_desconectar.setOnClickListener(v -> {
            blue.closeConnection();
        });



    }

    private void alertDevice() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Dispositivos Bluetooth");
        View inflater = getLayoutInflater().inflate(R.layout.list_view,null);
        dialog.setView(inflater);
        AlertDialog alert = dialog.create();
        alert.show();

        ListView list = inflater.findViewById(R.id.listDeviceBluetooth);
        listDevice = blue.deviceBluetooth();

        if (listDevice.isEmpty()){
            listDevice.add("No hay dispositivos");
        }else{
            Adapter adapter =new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listDevice);
            list.setAdapter((ListAdapter) adapter);
        }
        list.setOnItemClickListener((parent, view, position, id) -> {
            String device = listDevice.get(position);
            blue.connect(device);
            listenerFunctionRx(alert,listDevice.get(position));
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                terminal.setText("");
            }
        });

    }

    private void listenerFunctionRx(AlertDialog alert, String s) {
        blue.setDataLoadFinishedListener(connected -> {
            switch (connected){
                case True:
                    Toast.makeText(this, "Conectado a "+s, Toast.LENGTH_SHORT).show();
                    dispositivos.setText("Conectado a : "+s.substring(0,s.length()-17));
                    printConsole("Bienvenido a Electribasic");
                    alert.dismiss();
                    rxReceive();
                    break;
                case Disconnect:
                    Toast.makeText(this, "Desconectado", Toast.LENGTH_SHORT).show();
                    dispositivos.setText("No conectado");
                    break;
                case Pending:
                    Toast.makeText(this, "Conectando...", Toast.LENGTH_SHORT).show();
                    break;
                case False:
                    Toast.makeText(this, "No se pudo conectar", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    private void rxReceive() {
        blue.loadDateRx(s -> {
            if (switch_intervalometro.isChecked()) {
                if (s.equals("3")){
                    printConsole("Práctica no conectada: revise la alimentación del módulo: revise la conexión BT, reinicie en caso de fallo la conexión");
                }
                if (s.equals("2")){
                    printConsole("Práctica bien conectada");
                }
            }
        });
    }

    private void printConsole(String message) {
        terminal.setText(terminal.getText() + message + "\n");
    }

    private void barColorStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.blue));
        }
    }

    private void ids() {
        terminal = findViewById(R.id.terminal);
        switch_intervalometro = findViewById(R.id.switch_intervalometro);
        btn_desconectar = findViewById(R.id.btn_desconectar);
        btn_conectar = findViewById(R.id.btn_conectar);
        dispositivos = findViewById(R.id.txt_dispositivos);
        btn_clear = findViewById(R.id.clear);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (blue.checkPermissions(requestCode,grantResults)){
            Toast.makeText(this, "Exit", Toast.LENGTH_SHORT).show();
            blue.initializeBluetooth();
        }else{
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.S){
                blue.initializeBluetooth();
            }else{
                Toast.makeText(this, "Algo salio mal", Toast.LENGTH_SHORT).show();
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (!blue.stateBluetoooth() && requestCode == 100){
            blue.initializeBluetooth();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onDestroy() {
        blue.closeConnection();
        finish();
        super.onDestroy();
    }
}