package com.ingenieriiajhr.data;

import com.ingenieriiajhr.electric.R;

import java.util.ArrayList;

public class DataInstalacionElectrica {


    public DataInstalacionElectrica(){

    }

    public ArrayList<String> textOpt(){
        ArrayList<String> options = new ArrayList<String>();
        options.add("Tipos de instalaciones eléctricas");
        options.add("Instalaciones eléctricas residenciales");
        return options;
    }

    public ArrayList<Integer> numberOpt(){
        ArrayList<Integer> options = new ArrayList<>();
        options.add(R.mipmap.flash);
        options.add(R.mipmap.flash);
        return options;
    }

    public ArrayList<String> namePdf(){
        ArrayList<String> namePdfs = new ArrayList<>();
        namePdfs.add("tiposinstalacionelectricas.pdf");
        namePdfs.add("electricaresidencial.pdf");
        return namePdfs;
    }

}
