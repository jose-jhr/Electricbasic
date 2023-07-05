package com.ingenieriiajhr.data;

import com.ingenieriiajhr.electric.R;

import java.util.ArrayList;

public class DataOptions {

    public DataOptions() {

    }

    public ArrayList<String> textOpt() {
        ArrayList<String> options = new ArrayList<String>();
        options.add("Conceptos básicos");
        options.add("Instalaciones eléctricas");
        options.add("Calculadora");
        options.add("Medición de parámetros eléctricos");
        options.add("Sistema fotovoltaico");
        options.add("Prácticas y pruebas");
        return options;
    }

    public ArrayList<Integer> numberOpt() {
        ArrayList<Integer> options = new ArrayList<>();
        options.add(R.mipmap.conseptos);
        options.add(R.mipmap.house);
        options.add(R.mipmap.leyohm);
        options.add(R.mipmap.ammeter);
        options.add(R.mipmap.fotovoltaica);
        options.add(R.mipmap.electricista);
        return options;
    }


}

