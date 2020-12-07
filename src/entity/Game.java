package entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Game implements Serializable {
    private String playerName;
    private Map<Categories,Integer> data;
    private String time;
    private Map<Buttons,Integer> buttonConditionMap;
    private Integer totalRound;
    private Integer availableTimes;
    private Dice[] dices;

    public Game(String playerName, Map<Categories, Integer> data, String time) {
        this.playerName = playerName;
        this.data = data;
        this.time = time;
    }

    public Game(String playerName, String time) {
        this.playerName = playerName;
        this.time = time;
    }

    public Game(String playerName, Map<Categories, Integer> data,Map<Buttons,Integer> buttonConditionMap) {
        this.playerName = playerName;
        this.data = data;
        this.buttonConditionMap = buttonConditionMap;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Map<Categories,Integer> getData() {
        return data;
    }

    public void setData(Map<Categories,Integer> data) {
        this.data = data;
    }

    public Map<Buttons, Integer> getButtonConditionMap() {
        return buttonConditionMap;
    }

    public void setButtonConditionMap(Map<Buttons, Integer> buttonConditionMap) {
        this.buttonConditionMap = buttonConditionMap;
    }

    public Integer getTotalRound() {
        return totalRound;
    }

    public void setTotalRound(Integer totalRound) {
        this.totalRound = totalRound;
    }

    public Integer getAvailableTimes() {
        return availableTimes;
    }

    public void setAvailableTimes(Integer availableTimes) {
        this.availableTimes = availableTimes;
    }

    public Dice[] getDices() {
        return dices;
    }

    public void setDices(Dice[] dices) {
        this.dices = dices;
    }
}
