package service.client;

import entity.Game;
import gui.YahtzeeFrame;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class LoadAllRecordClientService implements Runnable{
    private static final int PORT = 8000;
    private static final String HOST = "localhost";
    private Socket socket;
    private List<Object> objectList;

    public LoadAllRecordClientService(List<Object> objectList){
        this.objectList = objectList;
        try {
            socket = new Socket(HOST,PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(){
        try {
            ObjectOutputStream sendToServer = new ObjectOutputStream(socket.getOutputStream());
            sendToServer.writeObject(objectList);
            ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());
            YahtzeeFrame.receivedAllRecord = (List<Game>)fromServer.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("loadAllRecordClientService is running");
        send();
    }
}
