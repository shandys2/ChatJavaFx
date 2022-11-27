package com.example.demo1;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.io.*;
import java.net.Socket;

public class ClienteController {

    @FXML
    TextArea textArea;
    @FXML
    TextField textMensaje;
    @FXML
    Button buttonEnviar;
    @FXML
    ListView listaConectados;

    Socket socketChat;
   // String user="DANI";
    InputStream inputStreamChat;
    BufferedReader bufferedReaderChat;
    OutputStream outputStreamChat;
    BufferedWriter bufferedWriterChat;
    static HiloEscuchaServidor hiloEscuchaServidorChat;


    @FXML
    public void initialize() throws IOException {
        iniciarSockets();

    }
    public static void shutdown() {
        // cleanup code here...
        System.out.println("Stop");
        Platform.exit();
        System.exit(0);
    }
    @FXML
    protected void onEnviar() {

        String mensaje="";
        try {
            mensaje= textMensaje.getText();
            System.out.println(mensaje);
            bufferedWriterChat.write(mensaje);
            bufferedWriterChat.newLine();
            bufferedWriterChat.flush();
            textMensaje.setText("");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void handleButtonAction(KeyEvent event) {
        String codigo= String.valueOf(event.getCode());
        if(codigo.equals("ENTER")){
          onEnviar();
      }
    }

    public void iniciarSockets() throws IOException {

        socketChat=new Socket("localhost",5555);
        //Inicializacion de los streams del chat
        outputStreamChat= socketChat.getOutputStream();
        bufferedWriterChat = new BufferedWriter(new OutputStreamWriter(outputStreamChat));
        inputStreamChat= socketChat.getInputStream();
        bufferedReaderChat= new BufferedReader(new InputStreamReader(inputStreamChat));
        //Inicializacion del hilo de escucha del servidor
        hiloEscuchaServidorChat= new HiloEscuchaServidor(bufferedReaderChat,textArea ,listaConectados);
        hiloEscuchaServidorChat.start();

        //le metemos el username
        bufferedWriterChat.write(Cliente.nickName);
        bufferedWriterChat.newLine();
        bufferedWriterChat.flush();


    }

}