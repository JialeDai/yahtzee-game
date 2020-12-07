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

public class LoadOneRecordService {
    private String playName;
    private String time;

    public LoadOneRecordService(String playName, String time){
        this.playName = playName;
        this.time = time;
    }

    public List<Game> LoadOneRecord(){
        DetailedScoreDao detailedScoreDao = new DetailedScoreDao();
        ResultSet resultSet = detailedScoreDao.selectOne(playName,time);
        List<Game> games = null;
        try {
            String name = resultSet.getString("player_name");
            Map<Categories,Integer> map = new HashMap<>();
            Map<Buttons,Integer> buttons = new HashMap<>();
            map.put(Categories.aces,resultSet.getInt("aces"));
            map.put(Categories.twos,resultSet.getInt("twos"));
            map.put(Categories.threes,resultSet.getInt("threes"));
            map.put(Categories.fours,resultSet.getInt("fours"));
            map.put(Categories.fives,resultSet.getInt("fives"));
            map.put(Categories.sixes,resultSet.getInt("sixes"));
            map.put(Categories.scoreSubtotal,resultSet.getInt("score_subtotal"));
            map.put(Categories.bonus,resultSet.getInt("bonus"));
            map.put(Categories.threeOfAKind,resultSet.getInt("three_of_a_kind"));
            map.put(Categories.fourOfAKind,resultSet.getInt("four_of_a_kind"));
            map.put(Categories.fullHouse,resultSet.getInt("full_house"));
            map.put(Categories.smallStraight,resultSet.getInt("small_straight"));
            map.put(Categories.largeStraight,resultSet.getInt("large_straight"));
            map.put(Categories.yahtzee,resultSet.getInt("yahtzee"));
            map.put(Categories.yahtzeeBonus,resultSet.getInt("yahtzee_bonus"));
            map.put(Categories.totalOfLowerSection,resultSet.getInt("total_of_lower_section"));
            map.put(Categories.lowerSectionGrandTotal,resultSet.getInt("grand_total"));
            map.put(Categories.upperSectionGrandTotal,resultSet.getInt("grand_total"));
            map.put(Categories.chance,resultSet.getInt("chance"));

           // TODO add buttons
            buttons.put(Buttons.acesBtn,resultSet.getInt("aces_btn"));
            buttons.put(Buttons.twosBtn,resultSet.getInt("twos_btn"));
            buttons.put(Buttons.threesBtn,resultSet.getInt("threes_btn"));
            buttons.put(Buttons.foursBtn,resultSet.getInt("fours_btn"));
            buttons.put(Buttons.fivesBtn,resultSet.getInt("fives_btn"));
            buttons.put(Buttons.sixesBtn,resultSet.getInt("sixes_btn"));
            buttons.put(Buttons.threeOfAKindBtn,resultSet.getInt("three_of_a_kind_btn"));
            buttons.put(Buttons.fourOfAKindBtn,resultSet.getInt("four_of_a_kind_btn"));
            buttons.put(Buttons.fullHouseBtn,resultSet.getInt("full_house_btn"));
            buttons.put(Buttons.smallStraightBtn,resultSet.getInt("small_straight_btn"));
            buttons.put(Buttons.largeStraightBtn,resultSet.getInt("large_straight_btn"));
            buttons.put(Buttons.yahtzeeBtn,resultSet.getInt("yahtzee_btn"));
            buttons.put(Buttons.chanceBtn,resultSet.getInt("chance_btn"));
            Game game = new Game(name,map,buttons);
            game.setTotalRound(resultSet.getInt("total_round"));
            game.setAvailableTimes(resultSet.getInt("available_times"));
            game.setTime(resultSet.getString("time"));
            Map<Integer,Dice> helperMap = new HashMap<>();
            helperMap.put(1,Dice.ONE);
            helperMap.put(2,Dice.TWO);
            helperMap.put(3,Dice.THREE);
            helperMap.put(4,Dice.FOUR);
            helperMap.put(5,Dice.FIVE);
            helperMap.put(6,Dice.SIX);
            Dice[] dices = new Dice[]{helperMap.get(resultSet.getInt("dice_0")),helperMap.get(resultSet.getInt("dice_1")),helperMap.get(resultSet.getInt("dice_2")),helperMap.get(resultSet.getInt("dice_3")),helperMap.get(resultSet.getInt("dice_4"))};
            game.setDices(dices);
            games = new ArrayList<>();
            games.add(game);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            SqliteUtils.closeConnection(DetailedScoreDao.resultSet,DetailedScoreDao.pstmt,DetailedScoreDao.conn);
            System.out.println("loadOne connection is closed");
        }
        return games;
    }
}
