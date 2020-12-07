package entity;

public enum Buttons {
    acesBtn(0),twosBtn(0),threesBtn(0),foursBtn(0),fivesBtn(0),sixesBtn(0),
    threeOfAKindBtn(0),fourOfAKindBtn(0),fullHouseBtn(0),smallStraightBtn(0),largeStraightBtn(0),yahtzeeBtn(0),chanceBtn(0);

    public Integer isClick;

    private Buttons(Integer isClick){
        this.isClick = isClick;
    }
}
