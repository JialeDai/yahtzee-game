package entity;

public enum Dice {
    ONE(1),TWO(2),THREE(3),FOUR(4),FIVE(5),SIX(6);

    public final int diceValue;

    private Dice(int diceValue){
        this.diceValue = diceValue;
    }
}
