package entity;

public enum Categories {
    aces(0), twos(0), threes(0), fours(0), fives(0),
    sixes(0), scoreSubtotal(0), bonus(0), upperSectionGrandTotal(0),
    threeOfAKind(0), fourOfAKind(0), fullHouse(0), smallStraight(0),
    largeStraight(0), yahtzee(0), yahtzeeBonus(0), totalOfLowerSection(0),
    lowerSectionGrandTotal(0),chance(0);

    public int value;

    private Categories(int value){
        this.value = value;
    }
}
