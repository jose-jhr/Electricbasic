package com.ingenieriiajhr.data;

import com.ingenieriiajhr.electric.R;

import java.util.ArrayList;

public class DataConceptosBasicos {

    public DataConceptosBasicos() {

    }

    public ArrayList<String> textOpt() {
        ArrayList<String> options = new ArrayList<String>();
        options.add("Ley de ohm");
        options.add("Fuentes de energía");
        options.add("Circuito eléctrico");
        options.add("Norma oficial mexicana NOM-001");
        return options;
    }

    public ArrayList<Integer> numberOpt() {
        ArrayList<Integer> options = new ArrayList<>();
        options.add(R.mipmap.leyohm);
        options.add(R.mipmap.fuentesenergia);
        options.add(R.mipmap.circuit);
        options.add(R.mipmap.norma);
        return options;
    }

    public ArrayList<String> namePdf() {
        ArrayList<String> namePdfs = new ArrayList<>();
        namePdfs.add("leyohm.pdf");
        namePdfs.add("fuentedeenergia.pdf");
        namePdfs.add("circuitoelectrico.pdf");
        namePdfs.add("norma.pdf");
        return namePdfs;
    }


}

