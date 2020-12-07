package entity;

import javafx.util.Pair;

import java.util.*;

public class Score {
    private static Dice[] oneRoll;
    private Map<Categories,Integer> helperScoreMap;
    public static Map<Categories,Integer> finalScoreMap = new HashMap<>();
    public static Integer yahtzeeCount=0;

    static {
        finalScoreMap.put(Categories.aces,Categories.aces.value);
        finalScoreMap.put(Categories.twos,Categories.twos.value);
        finalScoreMap.put(Categories.threes,Categories.threes.value);
        finalScoreMap.put(Categories.fours,Categories.fours.value);
        finalScoreMap.put(Categories.fives,Categories.fives.value);
        finalScoreMap.put(Categories.sixes,Categories.sixes.value);
        finalScoreMap.put(Categories.scoreSubtotal,Categories.twos.value);
        finalScoreMap.put(Categories.bonus,Categories.bonus.value);
        finalScoreMap.put(Categories.upperSectionGrandTotal,Categories.upperSectionGrandTotal.value);
        finalScoreMap.put(Categories.threeOfAKind,Categories.threeOfAKind.value);
        finalScoreMap.put(Categories.fourOfAKind,Categories.fourOfAKind.value);
        finalScoreMap.put(Categories.fullHouse,Categories.fullHouse.value);
        finalScoreMap.put(Categories.smallStraight,Categories.smallStraight.value);
        finalScoreMap.put(Categories.largeStraight,Categories.largeStraight.value);
        finalScoreMap.put(Categories.yahtzee,Categories.yahtzee.value);
        finalScoreMap.put(Categories.yahtzeeBonus,Categories.yahtzeeBonus.value);
        finalScoreMap.put(Categories.totalOfLowerSection,Categories.totalOfLowerSection.value);
        finalScoreMap.put(Categories.lowerSectionGrandTotal,Categories.lowerSectionGrandTotal.value);
        finalScoreMap.put(Categories.chance,Categories.chance.value);
    }

    public Score(){
        oneRoll = new Dice[5];
        helperScoreMap = new HashMap<>();
        helperScoreMap.put(Categories.aces,Categories.aces.value);
        helperScoreMap.put(Categories.twos,Categories.twos.value);
        helperScoreMap.put(Categories.threes,Categories.threes.value);
        helperScoreMap.put(Categories.fours,Categories.fours.value);
        helperScoreMap.put(Categories.fives,Categories.fives.value);
        helperScoreMap.put(Categories.sixes,Categories.sixes.value);
        helperScoreMap.put(Categories.scoreSubtotal,Categories.twos.value);
        helperScoreMap.put(Categories.bonus,Categories.bonus.value);
        helperScoreMap.put(Categories.upperSectionGrandTotal,Categories.upperSectionGrandTotal.value);
        helperScoreMap.put(Categories.threeOfAKind,Categories.threeOfAKind.value);
        helperScoreMap.put(Categories.fourOfAKind,Categories.fourOfAKind.value);
        helperScoreMap.put(Categories.fullHouse,Categories.fullHouse.value);
        helperScoreMap.put(Categories.smallStraight,Categories.smallStraight.value);
        helperScoreMap.put(Categories.largeStraight,Categories.largeStraight.value);
        helperScoreMap.put(Categories.yahtzee,Categories.yahtzee.value);
        helperScoreMap.put(Categories.yahtzeeBonus,Categories.yahtzeeBonus.value);
        helperScoreMap.put(Categories.totalOfLowerSection,Categories.totalOfLowerSection.value);
        helperScoreMap.put(Categories.lowerSectionGrandTotal,Categories.lowerSectionGrandTotal.value);
        helperScoreMap.put(Categories.chance,Categories.chance.value);
    }

    public Score(Dice[] dices){
        this();
        if(dices.length == 5){
            this.oneRoll = dices;
        }else {
            throw new IllegalArgumentException("the size of parameter array should be 5");
        }
    }

    public Map<Categories,Integer> calculateScore(){
        setAces(oneRoll);
        setTwos(oneRoll);
        setThrees(oneRoll);
        setFours(oneRoll);
        setFives(oneRoll);
        setSixes(oneRoll);
        setThreeOfAKind(oneRoll);
        setFourOfAKind(oneRoll);
        setFullHouse(oneRoll);
        setSmallStraight(oneRoll);
        setLargeStraight(oneRoll);
        setYahtzee(oneRoll);
        setChance(oneRoll);
        setYahtzeeBonus(oneRoll);
        return helperScoreMap;
    }

    private void setAces(Dice[] dices){
        int result = 0;
        for(int i=0;i<dices.length;i++){
            if(dices[i].diceValue==1){
                result+=dices[i].diceValue;
            }
        }
        helperScoreMap.put(Categories.aces,result);
    }

    private void setTwos(Dice[] dices){
        int result = 0;
        for(int i=0;i<dices.length;i++){
            if(dices[i].diceValue==2){
                result+=dices[i].diceValue;
            }
        }
        helperScoreMap.put(Categories.twos,result);
    }

    private void setThrees(Dice[] dices){
        int result = 0;
        for(int i=0;i<dices.length;i++){
            if(dices[i].diceValue==3){
                result+=dices[i].diceValue;
            }
        }
        helperScoreMap.put(Categories.threes,result);
    }

    private void setFours(Dice[] dices){
        int result = 0;
        for(int i=0;i<dices.length;i++){
            if(dices[i].diceValue==4){
                result+=dices[i].diceValue;
            }
        }
        helperScoreMap.put(Categories.fours,result);
    }

    private void setFives(Dice[] dices){
        int result = 0;
        for(int i=0;i<dices.length;i++){
            if(dices[i].diceValue==5){
                result+=dices[i].diceValue;
            }
        }
        helperScoreMap.put(Categories.fives,result);
    }

    private void setSixes(Dice[] dices){
        int result = 0;
        for(int i=0;i<dices.length;i++){
            if(dices[i].diceValue==6){
                result+=dices[i].diceValue;
            }
        }
        helperScoreMap.put(Categories.sixes,result);
    }

    private void setThreeOfAKind(Dice[] dices){
        for(Dice each:dices){
            System.out.println(each.diceValue);
        }
        if(countForAKind(dices)>=3)  helperScoreMap.put(Categories.threeOfAKind,sumDice(dices));
    }

    private void setFourOfAKind(Dice[] dices){
        if(countForAKind(dices)>=4)  helperScoreMap.put(Categories.fourOfAKind,sumDice(dices));
    }

    private void setFullHouse(Dice[] dices){
        if(checkFullHouse(dices)) helperScoreMap.put(Categories.fullHouse,25);
    }

    private void setSmallStraight(Dice[] dices){
        if(checkSmallStraight(dices)) helperScoreMap.put(Categories.smallStraight,30);
    }

    private void setLargeStraight(Dice[] dices){
        if(checkLargeStraight(dices)) helperScoreMap.put(Categories.largeStraight,40);
    }

    private void setYahtzee(Dice[] dices){
        if(checkYahtzee(dices)&&yahtzeeCount==0) helperScoreMap.put(Categories.yahtzee,50);
    }

    private void setChance(Dice[] dices){
        helperScoreMap.put(Categories.chance,sumDice(dices));
    }

    private void setYahtzeeBonus(Dice[] dices){
        if(checkYahtzee(dices) && helperScoreMap.get(Categories.yahtzee)==50) helperScoreMap.put(Categories.yahtzeeBonus,100);
        else if(checkYahtzee(dices) && helperScoreMap.get(Categories.yahtzee)==0)helperScoreMap.put(Categories.yahtzeeBonus,0);
        else helperScoreMap.put(Categories.yahtzeeBonus,0);
    }

//    private void setTotalOfLowerSection(Dice[] dices){
//        int total = helperScoreMap.get(Categories.threeOfAKind)+helperScoreMap.get(Categories.fourOfAKind)+helperScoreMap.get(Categories.fullHouse)
//                +helperScoreMap.get(Categories.smallStraight)+helperScoreMap.get(Categories.largeStraight)+helperScoreMap.get(Categories.yahtzee)+helperScoreMap.get(Categories.yahtzeeBonus);
//        helperScoreMap.put(Categories.totalOfLowerSection,total);
//    }

    private int countForAKind(Dice[] dices){
        Map<Dice,Integer> helpMap = new HashMap<>();
        int num = -1;
        for(int i=0;i<dices.length;i++){
            if(helpMap.containsKey(dices[i])){
                helpMap.put(dices[i],helpMap.get(dices[i])+1);
            }else{
                helpMap.put(dices[i],1);
            }
        }
        for(Dice each:helpMap.keySet()){
            helpMap.get(each);
            if(helpMap.get(each)>num){
                num=helpMap.get(each);
            }
        }
        return num;
    }

    public Boolean checkUpperSection(Dice[] dices,int i){
        Set<Integer> set = new HashSet<>();
        for(Dice each:dices){
            set.add(each.diceValue);
        }
        if (set.contains(i)) return true;
        return false;
    }

    public Boolean checkFullHouse(Dice[] dices){
        Boolean result = false;
        Map<Dice,Integer> helperMap = new HashMap<>();
        for(Dice each:dices){
            if(helperMap.keySet().contains(each)){
                helperMap.put(each,helperMap.get(each)+1);
            }else{
                helperMap.put(each,1);
            }
        }
        if(helperMap.keySet().size()==2){
            for(Dice each:dices){
                if(helperMap.keySet().contains(each) && helperMap.get(each)<4) result = true;
                else result = false;
            }
        }
        return result;
    }

    public Boolean checkSmallStraight(Dice[] dices){
        Integer[] arr = toArray(dices);
        Arrays.sort(arr);
        int count = 0;
        for(int i=0;i<arr.length-1;i++){
            if(arr[i+1]-arr[i]>1){
                count =0;
            }else if(arr[i+1]-arr[i]==1){
                count++;
            }
        }
        return count==3?true:false;
    }

    public Boolean checkLargeStraight(Dice[] dices){
        Integer[] arr = toArray(dices);
        Arrays.sort(arr);
        int count = 0;
        for(int i=0;i<arr.length-1;i++){
            if(arr[i+1]-arr[i]>1){
                count=0;
            }else if(arr[i+1]-arr[i]==1){
                count++;
            }
        }
        return count==4?true:false;
    }

    public Boolean checkYahtzee(Dice[] dices){
        Set<Integer> helpSet = new HashSet<>();
        for(int i=0;i<dices.length;i++){
            helpSet.add(dices[i].diceValue);
        }
        return helpSet.size()==1?true:false;
    }

    private Integer[] toArray(Dice[] dices){
        Integer[] result = new Integer[5];
        for(int i=0;i<dices.length;i++){
            result[i] = dices[i].diceValue;
        }
        return result;
    }

    private Integer sumDice(Dice[] dices){
        Integer result = 0;
        for(int i=0;i<dices.length;i++){
            result+=dices[i].diceValue;
        }
        return result;
    }

    public static Integer getGrandTotal(){
        return finalScoreMap.get(Categories.upperSectionGrandTotal)+finalScoreMap.get(Categories.totalOfLowerSection);
    }

    public Map<Categories, Integer> getHelperScoreMap() {
        return helperScoreMap;
    }

    public void setHelperScoreMap(Map<Categories, Integer> helperScoreMap) {
        this.helperScoreMap = helperScoreMap;
    }

    public void resetHelperScoreMap(){
        helperScoreMap.put(Categories.aces,0);
        helperScoreMap.put(Categories.twos,0);
        helperScoreMap.put(Categories.threes,0);
        helperScoreMap.put(Categories.fours,0);
        helperScoreMap.put(Categories.fives,0);
        helperScoreMap.put(Categories.sixes,0);
        helperScoreMap.put(Categories.scoreSubtotal,0);
        helperScoreMap.put(Categories.bonus,0);
        helperScoreMap.put(Categories.upperSectionGrandTotal,0);
        helperScoreMap.put(Categories.threeOfAKind,0);
        helperScoreMap.put(Categories.fourOfAKind,0);
        helperScoreMap.put(Categories.fullHouse,0);
        helperScoreMap.put(Categories.smallStraight,0);
        helperScoreMap.put(Categories.largeStraight,0);
        helperScoreMap.put(Categories.yahtzee,0);
        helperScoreMap.put(Categories.yahtzeeBonus,0);
        helperScoreMap.put(Categories.totalOfLowerSection,0);
        helperScoreMap.put(Categories.lowerSectionGrandTotal,0);
        helperScoreMap.put(Categories.chance,0);
    }

    public static void resetFinalScoreMap(){
        finalScoreMap.put(Categories.aces,0);
        finalScoreMap.put(Categories.twos,0);
        finalScoreMap.put(Categories.threes,0);
        finalScoreMap.put(Categories.fours,0);
        finalScoreMap.put(Categories.fives,0);
        finalScoreMap.put(Categories.sixes,0);
        finalScoreMap.put(Categories.scoreSubtotal,0);
        finalScoreMap.put(Categories.bonus,0);
        finalScoreMap.put(Categories.upperSectionGrandTotal,0);
        finalScoreMap.put(Categories.threeOfAKind,0);
        finalScoreMap.put(Categories.fourOfAKind,0);
        finalScoreMap.put(Categories.fullHouse,0);
        finalScoreMap.put(Categories.smallStraight,0);
        finalScoreMap.put(Categories.largeStraight,0);
        finalScoreMap.put(Categories.yahtzee,0);
        finalScoreMap.put(Categories.yahtzeeBonus,0);
        finalScoreMap.put(Categories.totalOfLowerSection,0);
        finalScoreMap.put(Categories.lowerSectionGrandTotal,0);
        finalScoreMap.put(Categories.chance,0);
    }
}
