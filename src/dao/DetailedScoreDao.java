package dao;

import dbUtils.SqliteUtils;
import entity.Buttons;
import entity.Categories;
import entity.Dice;
import entity.Score;

import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class DetailedScoreDao implements Command{
    public static Connection conn=null;
    public static PreparedStatement pstmt=null;
    public static ResultSet resultSet = null;

    private Integer totalRound;
    private Integer availableTimes;

    private String playerName;
    private Integer aces;
    private Integer twos;
    private Integer threes;
    private Integer fours;
    private Integer fives;
    private Integer sixes;
    private Integer scoreSubtotal;
    private Integer bonus;
    private Integer threeOfAKind;
    private Integer fourOfAKind;
    private Integer fullHouse;
    private Integer smallStraight;
    private Integer largeStraight;
    private Integer yahtzee;
    private Integer chance;
    private Integer yahtzeeBonus;
    private Integer totalOfLowerSection;
    private Integer grandTotal;
    private Map<Categories,Integer> scoreMap;

    private Map<Buttons,Integer> buttonMap;
    private Integer acesBtn;
    private Integer twosBtn;
    private Integer threesBtn;
    private Integer foursBtn;
    private Integer fivesBtn;
    private Integer sixesBtn;
    private Integer threeOfAKindBtn;
    private Integer fourOfAKindBtn;
    private Integer fullHouseBtn;
    private Integer smallStraightBtn;
    private Integer largeStraightBtn;
    private Integer yahtzeeBtn;
    private Integer chanceBtn;
    private Dice[]  dices;
    private Integer dice0;
    private Integer dice1;
    private Integer dice2;
    private Integer dice3;
    private Integer dice4;

    public DetailedScoreDao(String playerName, Map<Categories,Integer> scoreMap, Map<Buttons,Integer> buttonMap, Integer totalRound, Integer availableTimes, Dice[] dices){
        this.totalRound = totalRound;
        this.availableTimes = availableTimes;
        this.playerName = playerName;
        this.scoreMap = scoreMap;
        this.buttonMap = buttonMap;
        this.dices = dices;
        aces = scoreMap.get(Categories.aces);
        twos = scoreMap.get(Categories.twos);
        threes = scoreMap.get(Categories.threes);
        fours = scoreMap.get(Categories.fours);
        fives = scoreMap.get(Categories.fives);
        sixes = scoreMap.get(Categories.sixes);
        scoreSubtotal = scoreMap.get(Categories.scoreSubtotal);
        bonus = scoreMap.get(Categories.bonus);
        threeOfAKind = scoreMap.get(Categories.threeOfAKind);
        fourOfAKind = scoreMap.get(Categories.fourOfAKind);
        fullHouse = scoreMap.get(Categories.fullHouse);
        smallStraight = scoreMap.get(Categories.smallStraight);
        largeStraight = scoreMap.get(Categories.largeStraight);
        yahtzee = scoreMap.get(Categories.yahtzee);
        chance = scoreMap.get(Categories.chance);
        yahtzeeBonus = scoreMap.get(Categories.yahtzeeBonus);
        totalOfLowerSection = scoreMap.get(Categories.totalOfLowerSection);
        grandTotal = totalOfLowerSection+scoreSubtotal+bonus;

        acesBtn = buttonMap.get(Buttons.acesBtn);
        twosBtn = buttonMap.get(Buttons.twosBtn);
        threesBtn = buttonMap.get(Buttons.threesBtn);
        foursBtn = buttonMap.get(Buttons.foursBtn);
        fivesBtn = buttonMap.get(Buttons.fivesBtn);
        sixesBtn = buttonMap.get(Buttons.sixesBtn);
        threeOfAKindBtn = buttonMap.get(Buttons.threeOfAKindBtn);
        fourOfAKindBtn = buttonMap.get(Buttons.fourOfAKindBtn);
        fullHouseBtn = buttonMap.get(Buttons.fullHouseBtn);
        smallStraightBtn = buttonMap.get(Buttons.smallStraightBtn);
        largeStraightBtn = buttonMap.get(Buttons.largeStraightBtn);
        yahtzeeBtn = buttonMap.get(Buttons.yahtzeeBtn);
        chanceBtn = buttonMap.get(Buttons.chanceBtn);

        dice0 = dices[0].diceValue;
        dice1 = dices[1].diceValue;
        dice2 = dices[2].diceValue;
        dice3 = dices[3].diceValue;
        dice4 = dices[4].diceValue;
    }

    public DetailedScoreDao(){}

    @Override
    public void insert() {
        conn = null;
        pstmt = null;

        try {
            conn = SqliteUtils.getConnection();
            String sql = "insert into detailed_record(player_name,aces,twos,threes,fours,fives,sixes,score_subtotal," +
                    "bonus,three_of_a_kind,four_of_a_kind,full_house,small_straight,large_straight,yahtzee,chance," +
                    "yahtzee_bonus,total_of_lower_section,grand_total,aces_btn,twos_btn,threes_btn,fours_btn,fives_btn,sixes_btn," +
                    "three_of_a_kind_btn,four_of_a_kind_btn,full_house_btn,small_straight_btn,large_straight_btn,yahtzee_btn,chance_btn,total_round,available_times,dice_0,dice_1,dice_2,dice_3,dice_4) " +
                    "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,playerName);
            pstmt.setInt(2,aces);
            pstmt.setInt(3,twos);
            pstmt.setInt(4,threes);
            pstmt.setInt(5,fours);
            pstmt.setInt(6,fives);
            pstmt.setInt(7,sixes);
            pstmt.setInt(8,scoreSubtotal);
            pstmt.setInt(9,bonus);
            pstmt.setInt(10,threeOfAKind);
            pstmt.setInt(11,fourOfAKind);
            pstmt.setInt(12,fullHouse);
            pstmt.setInt(13,smallStraight);
            pstmt.setInt(14,largeStraight);
            pstmt.setInt(15,yahtzee);
            pstmt.setInt(16,chance);
            pstmt.setInt(17,yahtzeeBonus);
            pstmt.setInt(18,totalOfLowerSection);
            pstmt.setInt(19,grandTotal);

            pstmt.setInt(20,acesBtn);
            pstmt.setInt(21,twosBtn);
            pstmt.setInt(22,threesBtn);
            pstmt.setInt(23,foursBtn);
            pstmt.setInt(24,fivesBtn);
            pstmt.setInt(25,sixesBtn);
            pstmt.setInt(26,threeOfAKindBtn);
            pstmt.setInt(27,fourOfAKindBtn);
            pstmt.setInt(28,fullHouseBtn);
            pstmt.setInt(29,smallStraightBtn);
            pstmt.setInt(30,largeStraightBtn);
            pstmt.setInt(31,yahtzeeBtn);
            pstmt.setInt(32,chanceBtn);

            pstmt.setInt(33,totalRound);
            pstmt.setInt(34,availableTimes);

            pstmt.setInt(35,dice0);
            pstmt.setInt(36,dice1);
            pstmt.setInt(37,dice2);
            pstmt.setInt(38,dice3);
            pstmt.setInt(39,dice4);

            int cnt = pstmt.executeUpdate();
            System.out.println("insert successfully "+cnt+" lines changed");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updateOne(String name,String time){
        conn = null;
        pstmt = null;
        try{
            conn = SqliteUtils.getConnection();
            String sql =
                    "update detailed_record set time = ?,aces = ?,twos = ?,threes = ?,fours = ?,fives = ?,sixes = ?,score_subtotal = ?,bonus = ?,three_of_a_kind = ?," +
                            "four_of_a_kind = ?,full_house = ?,small_straight = ?,large_straight = ?,yahtzee = ?,chance = ?,yahtzee_bonus = ?,total_of_lower_section = ?," +
                    "grand_total = ?,aces_btn = ?,twos_btn = ?,threes_btn = ?,fours_btn = ?,fives_btn = ?,sixes_btn = ?,three_of_a_kind_btn = ?,four_of_a_kind_btn = ?," +
                            "full_house_btn = ?,small_straight_btn = ?,large_straight_btn = ?,yahtzee_btn = ?,chance_btn = ?,total_round = ?,available_times = ?,dice_0=?,dice_1=?,dice_2=?,dice_3=?,dice_4=?" +
                    "where player_name = ? and time = ?;";
            pstmt = conn.prepareStatement(sql);

            SimpleDateFormat sdf = new SimpleDateFormat();
            sdf.applyPattern("yyyy-mm-dd HH:mm:ss");
            Date date = new Date();
            pstmt.setString(1,sdf.format(date));
            pstmt.setInt(2,aces);
            pstmt.setInt(3,twos);
            pstmt.setInt(4,threes);
            pstmt.setInt(5,fours);
            pstmt.setInt(6,fives);
            pstmt.setInt(7,sixes);
            pstmt.setInt(8,scoreSubtotal);
            pstmt.setInt(9,bonus);
            pstmt.setInt(10,threeOfAKind);
            pstmt.setInt(11,fourOfAKind);
            pstmt.setInt(12,fullHouse);
            pstmt.setInt(13,smallStraight);
            pstmt.setInt(14,largeStraight);
            pstmt.setInt(15,yahtzee);
            pstmt.setInt(16,chance);
            pstmt.setInt(17,yahtzeeBonus);
            pstmt.setInt(18,totalOfLowerSection);
            pstmt.setInt(19,grandTotal);

            pstmt.setInt(20,acesBtn);
            pstmt.setInt(21,twosBtn);
            pstmt.setInt(22,threesBtn);
            pstmt.setInt(23,foursBtn);
            pstmt.setInt(24,fivesBtn);
            pstmt.setInt(25,sixesBtn);
            pstmt.setInt(26,threeOfAKindBtn);
            pstmt.setInt(27,fourOfAKindBtn);
            pstmt.setInt(28,fullHouseBtn);
            pstmt.setInt(29,smallStraightBtn);
            pstmt.setInt(30,largeStraightBtn);
            pstmt.setInt(31,yahtzeeBtn);
            pstmt.setInt(32,chanceBtn);

            pstmt.setInt(33,totalRound);
            pstmt.setInt(34,availableTimes);

            pstmt.setInt(35,dice0);
            pstmt.setInt(36,dice1);
            pstmt.setInt(37,dice2);
            pstmt.setInt(38,dice3);
            pstmt.setInt(39,dice4);

            pstmt.setString(40,name);
            pstmt.setString(41, time);
            int count = pstmt.executeUpdate();
            System.out.println("update successfully"+count+"row");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public ResultSet selectAll() {
        resultSet = null;
        String sql = "select * from detailed_record order by time ;";
        try {
            conn = SqliteUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            resultSet = pstmt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public ResultSet selectOne(String name,String time){
        conn = null;
        pstmt = null;
        resultSet = null;
        String sql = "select * from detailed_record where player_name=? and time=?;";
        try {
            conn = SqliteUtils.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,name);
            pstmt.setString(2,time);
            resultSet = pstmt.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }
}
