package service.server;

import entity.Buttons;
import entity.Categories;
import entity.Dice;
import entity.Game;
import server.SaveServer;
import service.db.DetailedScoreStoreService;
import service.db.LoadOneRecordService;
import service.db.UpdateOneRecordService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SaveServerService implements Runnable{
    private static final int PORT = 8000;
    private static ObjectOutputStream outputToClient;
    private static ObjectInputStream inputFromClient;

    public static void listen(){
        try {
            //Create a server socket
            ServerSocket serverSocket = new ServerSocket(PORT);
            SaveServer.wordsBox.append("\nServer started at "+ new Date()+"\n");

            while(true){
                System.out.println("saveServerService is Listening");

                //Listen for a new connection request
                Socket socket = serverSocket.accept();

                // Create an input stream from the socket
                inputFromClient = new ObjectInputStream(socket.getInputStream());

                // create an out stream from socket
                outputToClient = new ObjectOutputStream(socket.getOutputStream());

                //Read from input
                List<Object> object = (List<Object>)inputFromClient.readObject();
                System.out.println(object);

                SaveServer.wordsBox.append("The client host name is "+socket.getInetAddress()+
                        " and port number is "+socket.getPort()+"\n");
                SaveServer.wordsBox.append("data received from client is "+object+"\n");
                if(object.get(0).equals("Save")){
                    String name = (String)object.get(1);
                    Map<Categories,Integer> map = (Map<Categories,Integer>) object.get(2);
                    Map<Buttons,Integer> buttonsIntegerMap = (Map<Buttons,Integer>)object.get(3);
                    Game game = new Game(name,map,buttonsIntegerMap);
                    game.setTotalRound((Integer) object.get(4));
                    game.setAvailableTimes((Integer) object.get(5));
                    game.setDices((Dice[]) object.get(6));
                    new DetailedScoreStoreService(game).storeDetailedScore();
                }else if(object.get(0).equals("LoadAll")){
                    List<Game> list = new DetailedScoreStoreService().selectAll();
                    outputToClient.writeObject(list);
                }else if(object.get(0).equals("LoadOne")){
                    List<Game> list = new LoadOneRecordService(object.get(1).toString(),object.get(2).toString()).LoadOneRecord();
                    outputToClient.writeObject(list);
                }else if(object.get(0).equals("Update")){
                    String name = (String)object.get(1);
                    Map<Categories,Integer> map = (Map<Categories,Integer>) object.get(2);
                    Map<Buttons,Integer> buttonsIntegerMap = (Map<Buttons,Integer>)object.get(3);
                    Integer totalRound = (Integer)object.get(4);
                    Integer availableTimes = (Integer)object.get(5);
                    Game game = new Game(name,map,buttonsIntegerMap);
                    game.setTotalRound(totalRound);
                    game.setAvailableTimes(availableTimes);
                    game.setTime((String)object.get(7));
                    game.setDices((Dice[]) object.get(8));
                    new UpdateOneRecordService((String)object.get(6),(String)object.get(7)).UpdateOneRecord(game);
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        listen();
    }
}
