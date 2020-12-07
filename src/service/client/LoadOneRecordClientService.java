package service.client;

import entity.Categories;
import entity.Game;
import gui.YahtzeeFrame;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class LoadOneRecordClientService implements Runnable{
    private static final int PORT = 8000;
    private static final String HOST = "localhost";
    private Socket socket;
    private String name;
    private String time;
    private List<Object> objectList;

    public LoadOneRecordClientService(List<Object> objectList){
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
            List<Game> list = (List<Game>)fromServer.readObject();
            list.get(0).getData().put(Categories.upperSectionGrandTotal,list.get(0).getData().get(Categories.scoreSubtotal)+list.get(0).getData().get(Categories.bonus));
            // pass the select row to yahtzeeFrame
//            new YahtzeeFrame();
            YahtzeeFrame.setLoadData(list.get(0));
            YahtzeeFrame.setLoadButton(list.get(0));
            YahtzeeFrame.setLoadDices(list.get(0));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("loadOneRecordClientService is running");
        send();
    }
}
