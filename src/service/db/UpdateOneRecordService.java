package service.db;

import dao.DetailedScoreDao;
import dbUtils.SqliteUtils;
import entity.Buttons;
import entity.Categories;
import entity.Game;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateOneRecordService {
    private String playName;
    private String time;

    public UpdateOneRecordService(String playName, String time){
        this.playName = playName;
        this.time = time;
    }

    public synchronized void UpdateOneRecord(Game game){
      try{ DetailedScoreDao scoreDao = new DetailedScoreDao(playName,game.getData(),game.getButtonConditionMap(), game.getTotalRound(), game.getAvailableTimes(),game.getDices());
          scoreDao.updateOne(playName,time);
      }finally {
          SqliteUtils.closeConnection(DetailedScoreDao.resultSet,DetailedScoreDao.pstmt,DetailedScoreDao.conn);
          System.out.println("UpdateOne Connection is closed");
      }
    }
}
