package service.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SaveClientService implements Runnable{
    private static final int PORT = 8000;
    private static final String HOST = "localhost";
    private Socket socket;
    private Object object;

    public SaveClientService(Object object){
        this.object = object;
        try {
            socket = new Socket(HOST,PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(){
        try {
            ObjectOutputStream sendToServer = new ObjectOutputStream(socket.getOutputStream());
            sendToServer.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("saveClientService is running");
        send();
    }
}
