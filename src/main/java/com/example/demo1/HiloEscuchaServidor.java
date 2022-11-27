package com.example.demo1;

import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.util.Date;


public class HiloEscuchaServidor extends Thread{

    @FXML
    TextArea textArea;
    @FXML
    ListView listView;
    BufferedReader bufferedReader;

    String[]conectados;
    public HiloEscuchaServidor(BufferedReader bf,TextArea textArea , ListView lstView){
        this.bufferedReader=bf;
        this.textArea=textArea;
        this.listView =lstView;
    }

    @Override
    public void run(){

        String res;
        try {
            while ((res=bufferedReader.readLine())!=null){
                System.out.println("respuesta "+res);

                String[] tag=res.split("-");
                if(tag[0].equals("LOGIN")){
                    textArea.appendText("NightBot: Bienvenido al chat "+tag[1]+" !!\n");
                }
                if(tag[0].equals("LOGOFF")){
                    textArea.appendText("NightBot: "+tag[1]+" nos ha dejado ...\n");

                }
                if(tag[0].equals("LISTADO")){
                    Platform.runLater(
                            () -> {
                                listView.getItems().clear();
                            }
                    );

                    //agregar listado a la lista
                    String[]conectados=res.split("-");
                    //para cambiar ui desde otro thread
                        Platform.runLater(
                                () -> {
                                    for (int i=1; i<conectados.length;i++) {
                                    listView.getItems().add(conectados[i]);
                                    }
                                }
                        );

                }
                if(tag[0].equals("MENSAJE")){
                    Platform.runLater(
                            () -> {
                                textArea.appendText(tag[1]+":"+tag[2]+"\n");
                            }
                    );


                }
            }
        }catch (Exception e){

        }
    }
    public void procesarLogin(){

    }
    public void procesarLogOff(){

    }
    public void procesarListado(){

    }
    public void procesarMensaje(){

    }
    public String getFecha(){
        String h,m,s;
        Date fecha= new Date();
        h=parseHora(fecha.getHours());
        m=parseHora(fecha.getMinutes());
        s=parseHora(fecha.getSeconds());

        return "["+h+":"+m+":"+s+"]";
    }

    public String parseHora(int num){
        String numero;
        if(num<10){
            numero= "0"+num;
        }else{
            numero= String.valueOf(num);
        }
        return numero;
    }

}
