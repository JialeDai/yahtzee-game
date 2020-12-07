package service.db;

import dao.DetailedScoreDao;
import dbUtils.SqliteUtils;
import entity.Buttons;
import entity.Categories;
import entity.Dice;
import entity.Game;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailedScoreStoreService {
    private String name;
    private Map<Categories,Integer> scoreMap;
    private Map<Buttons,Integer> buttonMap;
    private Integer totalRound;
    private Integer availableTimes;
    private Dice[] dices;

    public DetailedScoreStoreService(){}
    public DetailedScoreStoreService(Game game){
        this.name = game.getPlayerName();
        this.scoreMap = game.getData();
        this.buttonMap = game.getButtonConditionMap();
        this.totalRound = game.getTotalRound();
        this.availableTimes = game.getAvailableTimes();
        this.dices = game.getDices();
    }

    public void storeDetailedScore(){
        DetailedScoreDao detailedScoreDao = new DetailedScoreDao(name,scoreMap,buttonMap,totalRound,availableTimes,dices);
        detailedScoreDao.insert();
    }

    public synchronized List<Game> selectAll(){
        ResultSet resultSet = new DetailedScoreDao().selectAll();
        List<Game> result = new ArrayList<>();
        try {
            while(resultSet.next()){
                Map<Categories,Integer> data = new HashMap<>();
                String playName = resultSet.getString("player_name");
                String time = resultSet.getString("time");
                Game tmp = new Game(playName,time);
                result.add(tmp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            SqliteUtils.closeConnection(DetailedScoreDao.resultSet,DetailedScoreDao.pstmt,DetailedScoreDao.conn);
            System.out.println("loadAll connection is closed");
        }
        return result;
    }
}
