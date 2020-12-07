package gui;

import entity.*;
import service.client.LoadAllRecordClientService;
import service.client.SaveClientService;
import service.client.UpdateOneRecordClientService;
import service.db.StoreTotalScoreService;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import javax.swing.*;


public class YahtzeeFrame extends JFrame {
	public  static Map<Buttons,Integer> buttonConditionMap = new HashMap<>();
	private static Boolean update = false;
	public static String loadPlayerName;
	public static String loadTime;

	private Boolean isSpecialRule = false;
	private static Integer totalOfLowerSection =0;
	private static Integer subTotal = 0;
	private static int availableTimes = 3;
	private static int totalRound = 13;
	private Dice[] currentRoll;
	static final int WIDTH=10;
	private JFrame messageFrame = new JFrame();
	public static List<Game> receivedAllRecord;

	private static JLabel turnLabel = new JLabel("Turn:");
	private static JLabel rollLabel = new JLabel("Roll:");

	private JPanel mainPanel;
	private JPanel playerNamePanel;
	private JPanel upperSectionPanel;
	private JPanel lowerSectionPanel;
	private JPanel leftVertexPanel;
	private JPanel rightVertexPanel;
	private JPanel controlPanel;

	private static Boolean acesTextFieldLock;
	private static Boolean twosTextFieldLock;
	private static Boolean threesTextFiledLock;
	private static Boolean foursTextFieldLock;
	private static Boolean fivesTextFieldLock;
	private static Boolean sixesTextFieldLock;
	private Boolean scoreSubtotalTextFieldLock;
	private Boolean bonusTextFieldLock;
	private Boolean upperSectionGrandTotalTextFieldLock;
	private static Boolean threeOfAKindTextFieldLock;
	private static Boolean fourOfAKindTextFieldLock;
	private static Boolean fullHouseTextFieldLock;
	private static Boolean smallStraightTextFieldLock;
	private static Boolean largeStraightTextFieldLock;
	private static Boolean yahtzeeTextFieldLock;
	private static Boolean yahtzeeBonusTextFieldLock;
	private Boolean totalOfLowerSectionTextFieldLock;
	private Boolean lowerSectionGrandTotalTextFieldLock;
	private static Boolean chanceTextFieldLock;

	private static JTextField acesTextField;
	private static JTextField twosTextField;
	private static JTextField threesTextFiled;
	private static JTextField foursTextField;
	private static JTextField fivesTextField;
	private static JTextField sixesTextField;
	private static JTextField scoreSubtotalTextField;
	private static JTextField bonusTextField;
	private static JTextField upperSectionGrandTotalTextField;
	private static JTextField threeOfAKindTextField;
	private static JTextField fourOfAKindTextField;
	private static JTextField fullHouseTextField;
	private static JTextField smallStraightTextField;
	private static JTextField largeStraightTextField;
	private static JTextField yahtzeeTextField;
	private static JTextField yahtzeeBonusTextField;
	private static JTextField totalOfLowerSectionTextField;
	private static JTextField lowerSectionGrandTotalTextField;
	private static JTextField chanceTextField;

	private JCheckBox dice1CheckBox;
	private JCheckBox dice2CheckBox;
	private JCheckBox dice3CheckBox;
	private JCheckBox dice4CheckBox;
	private JCheckBox dice5CheckBox;

	private static ImagePanel dice1ImagePanel;
	private static ImagePanel dice2ImagePanel;
	private static ImagePanel dice3ImagePanel;
	private static ImagePanel dice4ImagePanel;
	private static ImagePanel dice5ImagePanel;

	private static JButton acesButton;
	private static JButton twosButton;
	private static JButton threesButton;
	private static JButton foursButton;
	private static JButton fivesButton;
	private static JButton sixesButton;
	private static JButton threeOfAKindButton;
	private static JButton fourOfAKindButton;
	private static JButton fullHouseButton;
	private static JButton smallStraightButton;
	private static JButton largeStraightButton;
	private static JButton yahtzeeButton;
	private static JButton rollButton;
	private static JButton chanceButton;

	private static JTextField playerNameTextField;
	
	public YahtzeeFrame() {
		initialize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(550,1000);
		createMenu();
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		mainPanel.add(getPlayerNamePanel());
		mainPanel.add(getControlPanel());
		add(mainPanel);
	}

	static {
		buttonConditionMap.put(Buttons.acesBtn,Buttons.acesBtn.isClick);
		buttonConditionMap.put(Buttons.twosBtn,Buttons.twosBtn.isClick);
		buttonConditionMap.put(Buttons.threesBtn,Buttons.threesBtn.isClick);
		buttonConditionMap.put(Buttons.foursBtn,Buttons.foursBtn.isClick);
		buttonConditionMap.put(Buttons.fivesBtn,Buttons.fivesBtn.isClick);
		buttonConditionMap.put(Buttons.sixesBtn,Buttons.sixesBtn.isClick);
		buttonConditionMap.put(Buttons.threeOfAKindBtn,Buttons.threeOfAKindBtn.isClick);
		buttonConditionMap.put(Buttons.fourOfAKindBtn,Buttons.fourOfAKindBtn.isClick);
		buttonConditionMap.put(Buttons.fullHouseBtn,Buttons.fullHouseBtn.isClick);
		buttonConditionMap.put(Buttons.smallStraightBtn,Buttons.smallStraightBtn.isClick);
		buttonConditionMap.put(Buttons.largeStraightBtn,Buttons.largeStraightBtn.isClick);
		buttonConditionMap.put(Buttons.yahtzeeBtn,Buttons.yahtzeeBtn.isClick);
		buttonConditionMap.put(Buttons.chanceBtn,Buttons.chanceBtn.isClick);
	}

	public static void setLoadData(Game game){
		new Score().resetHelperScoreMap();
		availableTimes = game.getAvailableTimes();
		totalRound = game.getTotalRound();
		acesTextFieldLock = true;
		twosTextFieldLock = true;
		threesTextFiledLock = true;
		foursTextFieldLock = true;
		fivesTextFieldLock = true;
		sixesTextFieldLock = true;
		threeOfAKindTextFieldLock = true;
		fourOfAKindTextFieldLock = true;
		fullHouseTextFieldLock = true;
		smallStraightTextFieldLock = true;
		largeStraightTextFieldLock = true;
		yahtzeeTextFieldLock = true;
		chanceTextFieldLock = true;
		update = true;
		String playerName = game.getPlayerName();
		Map<Categories,Integer> map = game.getData();
		loadPlayerName = playerName;
		loadTime = game.getTime();
		playerNameTextField.setText(playerName);
		acesTextField.setText(map.get(Categories.aces).toString());
		twosTextField.setText(map.get(Categories.twos).toString());
		threesTextFiled.setText(map.get(Categories.threes).toString());
		foursTextField.setText(map.get(Categories.fours).toString());
		fivesTextField.setText(map.get(Categories.fives).toString());
		sixesTextField.setText(map.get(Categories.sixes).toString());

		bonusTextField.setText(map.get(Categories.bonus)==0?"("+(map.get(Categories.scoreSubtotal)-63)+")":"35");
		upperSectionGrandTotalTextField.setText(String.valueOf(map.get(Categories.scoreSubtotal)+map.get(Categories.bonus)));
		threeOfAKindTextField.setText(map.get(Categories.threeOfAKind).toString());
		fourOfAKindTextField.setText(map.get(Categories.fourOfAKind).toString());
		fullHouseTextField.setText(map.get(Categories.fullHouse).toString());
		smallStraightTextField.setText(map.get(Categories.smallStraight).toString());
		largeStraightTextField.setText(map.get(Categories.largeStraight).toString());
		yahtzeeTextField.setText(map.get(Categories.yahtzee).toString());
		yahtzeeBonusTextField.setText(map.get(Categories.yahtzeeBonus).toString());
		totalOfLowerSectionTextField.setText(map.get(Categories.totalOfLowerSection).toString());
		lowerSectionGrandTotalTextField.setText(map.get(Categories.lowerSectionGrandTotal).toString());
		chanceTextField.setText(map.get(Categories.chance).toString());
		turnLabel.setText("Turn:\t"+String.valueOf(13-game.getTotalRound()));
		rollLabel.setText("Roll:\t"+String.valueOf(3-game.getAvailableTimes()));
		Score.finalScoreMap.put(Categories.upperSectionGrandTotal,Integer.valueOf(upperSectionGrandTotalTextField.getText()));
		Score.finalScoreMap.put(Categories.totalOfLowerSection,Integer.valueOf(totalOfLowerSectionTextField.getText()));
		subTotal = Integer.valueOf(acesTextField.getText())+Integer.valueOf(twosTextField.getText())+Integer.valueOf(threesTextFiled.getText())+Integer.valueOf(foursTextField.getText())+Integer.valueOf(fivesTextField.getText())+Integer.valueOf(sixesTextField.getText());
		Score.finalScoreMap.put(Categories.scoreSubtotal,subTotal);
		scoreSubtotalTextField.setText(map.get(Categories.scoreSubtotal).toString());
		totalOfLowerSection = Integer.valueOf(threeOfAKindTextField.getText())+Integer.valueOf(fourOfAKindTextField.getText())+Integer.valueOf(fullHouseTextField.getText())+Integer.valueOf(smallStraightTextField.getText())+Integer.valueOf(largeStraightTextField.getText())+Integer.valueOf(yahtzeeTextField.getText())+Integer.valueOf(chanceTextField.getText());
	}

	public static void setLoadButton(Game game){
		Map<Buttons,Integer> buttonConditions = game.getButtonConditionMap();
		Map<Categories,Integer> map = new Score().getHelperScoreMap();
		if(buttonConditions.get(Buttons.acesBtn)==0){
			acesButton.setEnabled(true);
		}else {
			acesButton.setEnabled(false);
			acesTextFieldLock = false;
			map.put(Categories.aces,Integer.valueOf(acesTextField.getText()));
		}
		if(buttonConditions.get(Buttons.twosBtn)==0){
			twosButton.setEnabled(true);
		}else {
			twosButton.setEnabled(false);
			twosTextFieldLock = false;
			map.put(Categories.twos,Integer.valueOf(twosTextField.getText()));
		}
		if(buttonConditions.get(Buttons.threesBtn)==0){
			threesButton.setEnabled(true);
		}else {
			threesButton.setEnabled(false);
			threesTextFiledLock = false;
			map.put(Categories.threes,Integer.valueOf(threesTextFiled.getText()));
		}
		if(buttonConditions.get(Buttons.foursBtn)==0){
			foursButton.setEnabled(true);
		}else {
			foursButton.setEnabled(false);
			foursTextFieldLock = false;
			map.put(Categories.fours,Integer.valueOf(foursTextField.getText()));
		}
		if(buttonConditions.get(Buttons.fivesBtn)==0){
			fivesButton.setEnabled(true);
		}else{
			fivesButton.setEnabled(false);
			fivesTextFieldLock = false;
			map.put(Categories.fives,Integer.valueOf(fivesTextField.getText()));
		}
		if(buttonConditions.get(Buttons.sixesBtn)==0){
			sixesButton.setEnabled(true);
		}else{
			sixesButton.setEnabled(false);
			sixesTextFieldLock = false;
			map.put(Categories.sixes,Integer.valueOf(sixesTextField.getText()));
		}
		if(buttonConditions.get(Buttons.threeOfAKindBtn)==0){
			threeOfAKindButton.setEnabled(true);
		}else {
			threeOfAKindButton.setEnabled(false);
			threeOfAKindTextFieldLock = false;
			map.put(Categories.threeOfAKind,Integer.valueOf(threeOfAKindTextField.getText()));
		}
		if(buttonConditions.get(Buttons.fourOfAKindBtn)==0){
			fourOfAKindButton.setEnabled(true);
		}else {
			fourOfAKindButton.setEnabled(false);
			fourOfAKindTextFieldLock = false;
			map.put(Categories.fourOfAKind,Integer.valueOf(fourOfAKindTextField.getText()));
		}
		if(buttonConditions.get(Buttons.fullHouseBtn)==0){
			fullHouseButton.setEnabled(true);
		}else {
			fullHouseButton.setEnabled(false);
			fullHouseTextFieldLock = false;
			map.put(Categories.fullHouse,Integer.valueOf(fullHouseTextField.getText()));
		}
		if(buttonConditions.get(Buttons.smallStraightBtn)==0){
			smallStraightButton.setEnabled(true);
		}else {
			smallStraightButton.setEnabled(false);
			smallStraightTextFieldLock = false;
			map.put(Categories.smallStraight,Integer.valueOf(smallStraightTextField.getText()));
		}
		if(buttonConditions.get(Buttons.largeStraightBtn)==0){
			largeStraightButton.setEnabled(true);
		}else {
			largeStraightButton.setEnabled(false);
			largeStraightTextFieldLock = false;
			map.put(Categories.largeStraight,Integer.valueOf(largeStraightTextField.getText()));
		}
		if(buttonConditions.get(Buttons.yahtzeeBtn)==0){
			yahtzeeButton.setEnabled(true);
		}else{
			yahtzeeButton.setEnabled(false);
			yahtzeeTextField.setEnabled(false);
			yahtzeeBonusTextFieldLock = false;
			map.put(Categories.yahtzee,Integer.valueOf(yahtzeeTextField.getText()));
			map.put(Categories.yahtzeeBonus,Integer.valueOf(yahtzeeBonusTextField.getText()));
		}
		if(buttonConditions.get(Buttons.chanceBtn)==0){
			chanceButton.setEnabled(true);
		}else {
			chanceButton.setEnabled(false);
			chanceTextFieldLock = false;
			map.put(Categories.chance,Integer.valueOf(chanceTextField.getText()));
		}
		map.put(Categories.scoreSubtotal,Integer.valueOf(acesTextField.getText())+Integer.valueOf(twosTextField.getText())+Integer.valueOf(threesTextFiled.getText())+Integer.valueOf(foursTextField.getText())+Integer.valueOf(fivesTextField.getText())+Integer.valueOf(sixesTextField.getText()));
		map.put(Categories.lowerSectionGrandTotal,map.get(Categories.scoreSubtotal)+map.get(Categories.bonus));
		map.put(Categories.totalOfLowerSection,map.get(Categories.threeOfAKind)+map.get(Categories.fourOfAKind)+map.get(Categories.fullHouse)+map.get(Categories.smallStraight)+map.get(Categories.largeStraight)+map.get(Categories.yahtzee)+map.get(Categories.yahtzeeBonus));
		map.put(Categories.upperSectionGrandTotal,map.get(Categories.lowerSectionGrandTotal)+map.get(Categories.totalOfLowerSection));
	}

	public static void setLoadDices(Game game){
		Dice[] dices = game.getDices();
		setLoadDice(dice1ImagePanel,dices[0]);
		setLoadDice(dice2ImagePanel,dices[1]);
		setLoadDice(dice3ImagePanel,dices[2]);
		setLoadDice(dice4ImagePanel,dices[3]);
		setLoadDice(dice5ImagePanel,dices[4]);
	}

	private static void setLoadDice(ImagePanel imagePanel,Dice dice){
		switch (dice){
			case ONE:
				imagePanel.setImage("die1.png");
				break;
			case TWO:
				imagePanel.setImage("die2.png");
				break;
			case THREE:
				imagePanel.setImage("die3.png");
				break;
			case FOUR:
				imagePanel.setImage("die4.png");
				break;
			case FIVE:
				imagePanel.setImage("die5.png");
				break;
			case SIX:
				imagePanel.setImage("die6.png");
				break;
		}
		imagePanel.scaleImage(0.5);
	}

	public void initialize(){
		turnLabel.setText("Turn:\t0");
		rollLabel.setText("Roll:\t0");
		currentRoll = new Dice[]{Dice.ONE,Dice.ONE,Dice.ONE,Dice.ONE,Dice.ONE};
		dice1ImagePanel = new ImagePanel("die1.png");
		dice1ImagePanel.scaleImage(0.5);
		dice2ImagePanel = new ImagePanel("die1.png");
		dice2ImagePanel.scaleImage(0.5);
		dice3ImagePanel = new ImagePanel("die1.png");
		dice3ImagePanel.scaleImage(0.5);
		dice4ImagePanel = new ImagePanel("die1.png");
		dice4ImagePanel.scaleImage(0.5);
		dice5ImagePanel = new ImagePanel("die1.png");
		dice5ImagePanel.scaleImage(0.5);

		dice1CheckBox = new JCheckBox("keep");
		dice2CheckBox = new JCheckBox("keep");
		dice3CheckBox = new JCheckBox("keep");
		dice4CheckBox = new JCheckBox("keep");
		dice5CheckBox = new JCheckBox("keep");

		dice1CheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		dice2CheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		dice3CheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		dice4CheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		dice5CheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);

		turnLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		rollLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		acesTextField = new JTextField(WIDTH);
		twosTextField = new JTextField(WIDTH);
		threesTextFiled = new JTextField(WIDTH);
		foursTextField = new JTextField(WIDTH);
		fivesTextField = new JTextField(WIDTH);
		sixesTextField = new JTextField(WIDTH);
		scoreSubtotalTextField = new JTextField(WIDTH);
		bonusTextField = new JTextField(WIDTH);
		upperSectionGrandTotalTextField = new JTextField(WIDTH);
		threeOfAKindTextField = new JTextField(WIDTH);
		fourOfAKindTextField = new JTextField(WIDTH);
		fullHouseTextField = new JTextField(WIDTH);
		smallStraightTextField = new JTextField(WIDTH);
		largeStraightTextField = new JTextField(WIDTH);
		yahtzeeTextField = new JTextField(WIDTH);
		yahtzeeBonusTextField = new JTextField(WIDTH);
		totalOfLowerSectionTextField = new JTextField(WIDTH);
		lowerSectionGrandTotalTextField = new JTextField(WIDTH);
		chanceTextField = new JTextField(WIDTH);

		acesTextField.setEnabled(false);
		twosTextField.setEnabled(false);
		threesTextFiled.setEnabled(false);
		foursTextField.setEnabled(false);
		fivesTextField.setEnabled(false);
		sixesTextField.setEnabled(false);
		scoreSubtotalTextField.setEnabled(false);
		bonusTextField.setEnabled(false);
		upperSectionGrandTotalTextField.setEnabled(false);
		threeOfAKindTextField.setEnabled(false);
		fourOfAKindTextField.setEnabled(false);
		fullHouseTextField.setEnabled(false);
		smallStraightTextField.setEnabled(false);
		largeStraightTextField.setEnabled(false);
		yahtzeeTextField.setEnabled(false);
		yahtzeeBonusTextField.setEnabled(false);
		totalOfLowerSectionTextField.setEnabled(false);
		lowerSectionGrandTotalTextField.setEnabled(false);
		chanceTextField.setEnabled(false);

		//ture stands for textFiled can be edited
		acesTextFieldLock = true;
		twosTextFieldLock = true;
		threesTextFiledLock = true;
		foursTextFieldLock = true;
		fivesTextFieldLock = true;
		sixesTextFieldLock = true;
		scoreSubtotalTextFieldLock = true;
		bonusTextFieldLock = true;
		upperSectionGrandTotalTextFieldLock = true;
		threeOfAKindTextFieldLock = true;
		fourOfAKindTextFieldLock = true;
		fullHouseTextFieldLock = true;
		smallStraightTextFieldLock = true;
		largeStraightTextFieldLock = true;
		yahtzeeTextFieldLock = true;
		yahtzeeBonusTextFieldLock = true;
		totalOfLowerSectionTextFieldLock = true;
		lowerSectionGrandTotalTextFieldLock = true;
		chanceTextFieldLock = true;

		acesButton = new JButton("Aces");
		twosButton = new JButton("Twos");
		threesButton = new JButton("Threes");
		foursButton = new JButton("Fours");
		fivesButton = new JButton("Fives");
		sixesButton = new JButton("Sixes");
		rollButton = new JButton("roll");
		threeOfAKindButton = new JButton("3 of a kind");
		fourOfAKindButton = new JButton("4 of a kind");
		fullHouseButton = new JButton("Full House");
		smallStraightButton = new JButton("Small Straight");
		largeStraightButton = new JButton("Large Straight");
		yahtzeeButton = new JButton("Yahtzee");
		chanceButton = new JButton("Chance");

		rollButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		acesButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(acesButton.isEnabled()){
					if(click(acesButton,acesTextField,Categories.aces)) acesTextFieldLock = false;
					buttonConditionMap.put(Buttons.acesBtn,1);
					setBonusTextField();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});

		twosButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(twosButton.isEnabled()){
					if(click(twosButton,twosTextField,Categories.twos)) twosTextFieldLock = false;
					setBonusTextField();
					Score.finalScoreMap.put(Categories.aces,Integer.valueOf(acesTextField.getText()));
					buttonConditionMap.put(Buttons.twosBtn,1);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});

		threesButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(threesButton.isEnabled()){
					if(click(threesButton,threesTextFiled,Categories.threes)) threesTextFiledLock = false;
					setBonusTextField();
					buttonConditionMap.put(Buttons.threesBtn,1);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});

		foursButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(foursButton.isEnabled()){
					if(click(foursButton,foursTextField,Categories.fours)) foursTextFieldLock = false;
					setBonusTextField();
					buttonConditionMap.put(Buttons.foursBtn,1);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});

		fivesButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(fivesButton.isEnabled()){
					if(click(fivesButton,fivesTextField,Categories.fives)) fivesTextFieldLock = false;
					setBonusTextField();
					buttonConditionMap.put(Buttons.fivesBtn,1);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});

		sixesButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(sixesButton.isEnabled()){
					if(click(sixesButton,sixesTextField,Categories.sixes)) sixesTextFieldLock=false;
					setBonusTextField();
					buttonConditionMap.put(Buttons.sixesBtn,1);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});

		threeOfAKindButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(threeOfAKindButton.isEnabled()){
					if(click(threeOfAKindButton,threeOfAKindTextField,Categories.threeOfAKind)) threeOfAKindTextFieldLock = false;
					buttonConditionMap.put(Buttons.threeOfAKindBtn,1);
					setGrandTotalTextField();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});

		fourOfAKindButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(fourOfAKindButton.isEnabled()){
					if(click(fourOfAKindButton,fourOfAKindTextField,Categories.fourOfAKind)) fourOfAKindTextFieldLock = false;
					buttonConditionMap.put(Buttons.fourOfAKindBtn,1);
					setGrandTotalTextField();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});

		fullHouseButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(fullHouseButton.isEnabled()){
					if(click(fullHouseButton,fullHouseTextField,Categories.fullHouse)) fullHouseTextFieldLock = false;
					buttonConditionMap.put(Buttons.fullHouseBtn,1);
					setGrandTotalTextField();
					if(!isSpecialRule) {
						if(largeStraightTextFieldLock) largeStraightTextField.setText(new Score(currentRoll).calculateScore().get(Categories.largeStraight).toString());
						if(smallStraightTextFieldLock) smallStraightTextField.setText(new Score(currentRoll).calculateScore().get(Categories.smallStraight).toString());
						if(fullHouseTextFieldLock) fullHouseTextField.setText(new Score(currentRoll).calculateScore().get(Categories.fullHouse).toString());
					}
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});

		smallStraightButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(smallStraightButton.isEnabled()){
					if(click(smallStraightButton,smallStraightTextField,Categories.smallStraight)) smallStraightTextFieldLock = false;
					buttonConditionMap.put(Buttons.smallStraightBtn,1);
					setGrandTotalTextField();
					if(!isSpecialRule) {
						if(largeStraightTextFieldLock) largeStraightTextField.setText(new Score(currentRoll).calculateScore().get(Categories.largeStraight).toString());
						if(smallStraightTextFieldLock) smallStraightTextField.setText(new Score(currentRoll).calculateScore().get(Categories.smallStraight).toString());
						if(fullHouseTextFieldLock) fullHouseTextField.setText(new Score(currentRoll).calculateScore().get(Categories.fullHouse).toString());
					}
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});

		largeStraightButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(largeStraightButton.isEnabled()){
					if(click(largeStraightButton,largeStraightTextField,Categories.largeStraight)) largeStraightTextFieldLock = false;
					buttonConditionMap.put(Buttons.largeStraightBtn,1);
					setGrandTotalTextField();
					if(!isSpecialRule) {
						if(largeStraightTextFieldLock) largeStraightTextField.setText(new Score(currentRoll).calculateScore().get(Categories.largeStraight).toString());
						if(smallStraightTextFieldLock) smallStraightTextField.setText(new Score(currentRoll).calculateScore().get(Categories.smallStraight).toString());
						if(fullHouseTextFieldLock) fullHouseTextField.setText(new Score(currentRoll).calculateScore().get(Categories.fullHouse).toString());
					}
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});

		yahtzeeButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(yahtzeeButton.isEnabled()){
					if(click(yahtzeeButton,yahtzeeTextField,Categories.yahtzee)) yahtzeeTextFieldLock = false;
					buttonConditionMap.put(Buttons.yahtzeeBtn,1);
					Score.yahtzeeCount++;
					setGrandTotalTextField();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});

		chanceButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(chanceButton.isEnabled()){
					if(click(chanceButton,chanceTextField,Categories.chance)) chanceTextFieldLock = false;
					buttonConditionMap.put(Buttons.chanceBtn,1);
					setGrandTotalTextField();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});

		rollButton.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				if(availableTimes>0){
					if(!dice1CheckBox.isSelected()) currentRoll[0]=dice1ImagePanel.roll();
					if(!dice2CheckBox.isSelected()) currentRoll[1]=dice2ImagePanel.roll();
					if(!dice3CheckBox.isSelected()) currentRoll[2]=dice3ImagePanel.roll();
					if(!dice4CheckBox.isSelected()) currentRoll[3]=dice4ImagePanel.roll();
					if(!dice5CheckBox.isSelected()) currentRoll[4]=dice5ImagePanel.roll();
					displayScore(new Score(currentRoll).calculateScore());
					setYahtzeeBonusTextField();
					if(isSpecialRule){
						//TODO special rule
						if(fullHouseTextFieldLock) fullHouseTextField.setText("25");
						if(smallStraightTextFieldLock) smallStraightTextField.setText("30");
						if(largeStraightTextFieldLock) largeStraightTextField.setText("40");
					}
					availableTimes--;
					rollLabel.setText("Roll:\t"+String.valueOf(3-availableTimes));
				}else {
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame,"You must select a score category before rolling again");
				}
				hideKeep();
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		hideKeep();
	}

	private void resetDice(){
		dice1ImagePanel.setImage("die1.png");
		dice1ImagePanel.scaleImage(0.5);
		dice2ImagePanel.setImage("die1.png");
		dice2ImagePanel.scaleImage(0.5);
		dice3ImagePanel.setImage("die1.png");
		dice3ImagePanel.scaleImage(0.5);
		dice4ImagePanel.setImage("die1.png");
		dice4ImagePanel.scaleImage(0.5);
		dice5ImagePanel.setImage("die1.png");
		dice5ImagePanel.scaleImage(0.5);
	}

	private Boolean click(JButton jButton,JTextField jTextField,Categories categories){
		if(jTextField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(messageFrame,"Please roll the dices first");
			return false;
		} else if(!jTextField.getText().isEmpty()&&availableTimes!=3 && !isSpecialRule){
			availableTimes = 3;
			if(jButton==acesButton||jButton==twosButton||jButton==threesButton||jButton==foursButton||jButton==fivesButton||jButton==sixesButton){
				subTotal+=Integer.parseInt(jTextField.getText());
				scoreSubtotalTextField.setText(subTotal.toString());
				Score.finalScoreMap.put(Categories.scoreSubtotal,subTotal);
				totalRound --;
				turnLabel.setText("Turn:\t"+String.valueOf(13-totalRound));
			}
			if(!isSpecialRule&&(jButton==threeOfAKindButton||jButton==fourOfAKindButton||jButton==fullHouseButton||jButton==smallStraightButton||jButton==largeStraightButton||jButton==yahtzeeButton||jButton==chanceButton)){
				totalOfLowerSection+=Integer.parseInt((jTextField.getText()));
				totalOfLowerSectionTextField.setText(totalOfLowerSection.toString());
				Score.finalScoreMap.put(Categories.totalOfLowerSection,totalOfLowerSection);
				totalRound --;
				turnLabel.setText("Turn:\t"+String.valueOf(13-totalRound));
			}
			jButton.setEnabled(false);
		}else if(availableTimes==3 && !isSpecialRule&&jButton.isEnabled()){
			JOptionPane.showMessageDialog(messageFrame,"you must roll again");
		}else if(isSpecialRule){
			Boolean specialRuleAcesCheck = new Score().checkUpperSection(currentRoll,1)&&acesTextFieldLock;
			Boolean specialRuleTwosCheck = new Score().checkUpperSection(currentRoll,2)&&twosTextFieldLock;
			Boolean specialRuleThreesCheck = new Score().checkUpperSection(currentRoll,3)&&threesTextFiledLock;
			Boolean specialRuleFoursCheck = new Score().checkUpperSection(currentRoll,4)&&foursTextFieldLock;
			Boolean specialRuleFivesCheck = new Score().checkUpperSection(currentRoll,5)&&fivesTextFieldLock;
			Boolean specialRuleSixesCheck = new Score().checkUpperSection(currentRoll,6)&&sixesTextFieldLock;
			// upper section have corresponding category have not been used, lower section are not allowed to be selected
			if(specialRuleAcesCheck||specialRuleTwosCheck||specialRuleThreesCheck||specialRuleFoursCheck||specialRuleFivesCheck||specialRuleSixesCheck){
				if(jButton==threeOfAKindButton||jButton==fourOfAKindButton||jButton==fullHouseButton||jButton==smallStraightButton||jButton==largeStraightButton) {
					JOptionPane.showMessageDialog(messageFrame, "Special Rule Applied, there is a category in upper section unused, please select it!");
				}else if(Integer.valueOf(jTextField.getText())==0){
					JOptionPane.showMessageDialog(messageFrame,"There is a corresponding category still unused");
				}else{
					//select upper section category
					availableTimes =3;
					subTotal+=Integer.parseInt(jTextField.getText());
					scoreSubtotalTextField.setText(subTotal.toString());
					Score.finalScoreMap.put(Categories.scoreSubtotal,subTotal);
					totalRound --;
					turnLabel.setText("Turn:\t"+String.valueOf(13-totalRound));
					isSpecialRule=false;
					jButton.setEnabled(false);
				}
			}else{ //no corresponding category in the upper section
				if(jButton==acesButton||jButton==twosButton||jButton==threesButton||jButton==foursButton||jButton==fivesButton||jButton==sixesButton) {
					JOptionPane.showMessageDialog(messageFrame, "Special Rule Applied, there is no corresponding category in upper section, please select in lower section!");
				}
				totalOfLowerSection+=Integer.parseInt((jTextField.getText()));
				totalOfLowerSectionTextField.setText(totalOfLowerSection.toString());
				Score.finalScoreMap.put(Categories.totalOfLowerSection,totalOfLowerSection);
				totalRound --;
				turnLabel.setText("Turn:\t"+String.valueOf(13-totalRound));
				isSpecialRule=false;
				jButton.setEnabled(false);
			}

		}
		resetCheckBox();
		resetDice();
		Score.finalScoreMap.put(categories,Integer.valueOf(jTextField.getText()));
		checkIfFinishGame();
		hideKeep();
		rollLabel.setText("Roll:\t1");
		return true;
	}

	private void resetCheckBox(){
		dice1CheckBox.setSelected(false);
		dice2CheckBox.setSelected(false);
		dice3CheckBox.setSelected(false);
		dice4CheckBox.setSelected(false);
		dice5CheckBox.setSelected(false);
	}

	private void checkIfFinishGame(){
		if(totalRound==0) {
			rollButton.setEnabled(false);
			JOptionPane.showMessageDialog(messageFrame,"Game finished, total score is: "+ Score.getGrandTotal()+" Thanks for playing");
			StoreTotalScoreService storeTotalScoreService = new StoreTotalScoreService(playerNameTextField.getText(),Score.getGrandTotal());
			storeTotalScoreService.storeTotalScore();
		}
	}
//setValue for textFiled
	private void displayScore(Map<Categories,Integer> scoreMap){
		try{
			if(acesTextFieldLock) acesTextField.setText(scoreMap.get(Categories.aces).toString());
			if(twosTextFieldLock) twosTextField.setText(scoreMap.get(Categories.twos).toString());
			if(threesTextFiledLock) threesTextFiled.setText(scoreMap.get(Categories.threes).toString());
			if(foursTextFieldLock) foursTextField.setText(scoreMap.get(Categories.fours).toString());
			if(fivesTextFieldLock) fivesTextField.setText(scoreMap.get(Categories.fives).toString());
			if(sixesTextFieldLock) sixesTextField.setText(scoreMap.get(Categories.sixes).toString());

			if(threeOfAKindTextFieldLock) threeOfAKindTextField.setText(scoreMap.get(Categories.threeOfAKind).toString());
			if(fourOfAKindTextFieldLock) fourOfAKindTextField.setText(scoreMap.get(Categories.fourOfAKind).toString());
			if(fullHouseTextFieldLock&&!isSpecialRule) fullHouseTextField.setText(scoreMap.get(Categories.fullHouse).toString());
			if(smallStraightTextFieldLock&&!isSpecialRule) smallStraightTextField.setText(scoreMap.get(Categories.smallStraight).toString());
			if(largeStraightTextFieldLock&&!isSpecialRule) largeStraightTextField.setText(scoreMap.get(Categories.largeStraight).toString());
			if(yahtzeeTextFieldLock) yahtzeeTextField.setText(scoreMap.get(Categories.yahtzee).toString());
			if(chanceTextFieldLock) chanceTextField.setText(scoreMap.get(Categories.chance).toString());
			if(yahtzeeTextFieldLock==false)yahtzeeBonusTextField.setText(scoreMap.get(Categories.yahtzeeBonus).toString());

		}catch (Exception e){
			e.printStackTrace();
		}
	}

	private void setGrandTotalTextField(){
		upperSectionGrandTotalTextField.setText(Score.finalScoreMap.get(Categories.upperSectionGrandTotal).toString());
		lowerSectionGrandTotalTextField.setText(Score.getGrandTotal().toString());
	}

	private void setBonusTextField(){
		if(subTotal<=62&&bonusTextFieldLock){
			bonusTextField.setText("("+String.valueOf(subTotal-63)+")");
			Score.finalScoreMap.put(Categories.scoreSubtotal,subTotal);
			Score.finalScoreMap.put(Categories.upperSectionGrandTotal,subTotal);
			Score.finalScoreMap.put(Categories.bonus,0);
		}else if(subTotal>62 && scoreSubtotalTextFieldLock){
			if(bonusTextFieldLock){
				//TODO change the function upperSectionGrandTotal
//				subTotal+=35;
				Integer upperSectionGrandTotal = subTotal+35;
				Score.finalScoreMap.put(Categories.upperSectionGrandTotal,upperSectionGrandTotal);
				scoreSubtotalTextField.setText(subTotal.toString());
				Score.finalScoreMap.put(Categories.scoreSubtotal,subTotal);
				Score.finalScoreMap.put(Categories.bonus,35);
			}
			bonusTextField.setText("35");
			bonusTextField.setEnabled(false);
//			bonusTextField.setBorder(BorderFactory.createLineBorder(Color.RED,1));
			bonusTextFieldLock=false;
		}
		setGrandTotalTextField();
	}

	private void setYahtzeeBonusTextField(){
		if(new Score().checkYahtzee(currentRoll) && yahtzeeBonusTextFieldLock && yahtzeeTextFieldLock==false && Integer.valueOf(yahtzeeTextField.getText())==50){
			JOptionPane.showMessageDialog(messageFrame,"You get a yahtzee bonus for 100 points!");
			yahtzeeBonusTextField.setText("100");
			Score.finalScoreMap.put(Categories.yahtzeeBonus,100);
			totalOfLowerSection+=100;
			Score.finalScoreMap.put(Categories.totalOfLowerSection,totalOfLowerSection);
			yahtzeeBonusTextFieldLock = false;
			totalOfLowerSectionTextField.setText(totalOfLowerSection.toString());
			// TODO change
			upperSectionGrandTotalTextField.setText(Score.finalScoreMap.get(Categories.upperSectionGrandTotal).toString());
			lowerSectionGrandTotalTextField.setText(Score.getGrandTotal().toString());
		}else if(new Score().checkYahtzee(currentRoll) && yahtzeeBonusTextFieldLock && yahtzeeTextFieldLock==false && Integer.valueOf(yahtzeeTextField.getText())==0){
			//special rule
			// the upper section buttons has all been used
			isSpecialRule=true;
			JOptionPane.showMessageDialog(messageFrame,"special rule applied");
			yahtzeeBonusTextField.setText("0");
		}
	}

	private void hideKeep(){
		if (availableTimes==3){
			dice1CheckBox.setVisible(false);
			dice2CheckBox.setVisible(false);
			dice3CheckBox.setVisible(false);
			dice4CheckBox.setVisible(false);
			dice5CheckBox.setVisible(false);
		}else{
			dice1CheckBox.setVisible(true);
			dice2CheckBox.setVisible(true);
			dice3CheckBox.setVisible(true);
			dice4CheckBox.setVisible(true);
			dice5CheckBox.setVisible(true);
		}
	}

	private void newGame(JTextField jTextField, JButton jButton){
		jTextField.setText("");
		if(jButton!=null) jButton.setEnabled(true);
		Boolean lock = true;
	}

	private void createMenu(){
		JMenuBar jMenuBar = new JMenuBar();
		JMenu gameMenu = new JMenu("Game");
		JMenuItem loadGameMenuItem = new JMenuItem("Load Game");
		JMenuItem saveGameMenuItem = new JMenuItem("Save Game");
		JMenuItem newGameMenuItem = new JMenuItem("New Game");
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		gameMenu.add(newGameMenuItem);
		gameMenu.add(loadGameMenuItem);
		gameMenu.add(saveGameMenuItem);
		gameMenu.add(exitMenuItem);
		jMenuBar.add(gameMenu);
		setJMenuBar(jMenuBar);
		exitMenuItem.addActionListener(e->{System.exit(0);});
		newGameMenuItem.addActionListener(e->{
			subTotal = 0;
			availableTimes =3;
			totalRound = 13;
			playerNameTextField.setText("default player");
			turnLabel.setText("Turn:\t0");
			rollLabel.setText("Roll:\t0");
			resetDice();
			hideKeep();
			currentRoll = new Dice[]{Dice.ONE,Dice.ONE,Dice.ONE,Dice.ONE,Dice.ONE};

			newGame(acesTextField,acesButton);
			acesTextFieldLock = true;
			newGame(twosTextField,twosButton);
			twosTextFieldLock = true;
			newGame(threesTextFiled,threesButton);
			threesTextFiledLock = true;
			newGame(foursTextField,foursButton);
			foursTextFieldLock = true;
			newGame(fivesTextField,fivesButton);
			fivesTextFieldLock = true;
			newGame(sixesTextField,sixesButton);
			sixesTextFieldLock = true;
			newGame(scoreSubtotalTextField,null);
			scoreSubtotalTextFieldLock = true;
			newGame(bonusTextField,null);
			bonusTextFieldLock = true;
			newGame(upperSectionGrandTotalTextField,null);
			upperSectionGrandTotalTextFieldLock = true;
			newGame(threeOfAKindTextField,threeOfAKindButton);
			threeOfAKindTextFieldLock = true;
			newGame(fourOfAKindTextField,fourOfAKindButton);
			fourOfAKindTextFieldLock = true;
			newGame(fullHouseTextField,fourOfAKindButton);
			fullHouseTextFieldLock  = true;
			newGame(smallStraightTextField,smallStraightButton);
			smallStraightTextFieldLock = true;
			newGame(largeStraightTextField,largeStraightButton);
			largeStraightTextFieldLock = true;
			newGame(yahtzeeTextField,yahtzeeButton);
			yahtzeeTextFieldLock = true;
			newGame(chanceTextField,chanceButton);
			chanceTextFieldLock = true;
			newGame(yahtzeeBonusTextField,null);
			yahtzeeTextFieldLock = true;
			newGame(totalOfLowerSectionTextField,null);
			totalOfLowerSectionTextFieldLock = true;
			newGame(lowerSectionGrandTotalTextField,null);
			lowerSectionGrandTotalTextFieldLock = true;
			new Score().resetHelperScoreMap();
			isSpecialRule = false;
			update = false;
			Score.resetFinalScoreMap();
			totalOfLowerSection = 0;
		});
		saveGameMenuItem.addActionListener(e->{
			List<Object> tmp = new ArrayList<>();
			if(!update){
				tmp.add("Save");
				tmp.add(playerNameTextField.getText());
				tmp.add(Score.finalScoreMap);
				tmp.add(buttonConditionMap);
				tmp.add(totalRound);
				tmp.add(availableTimes==3?2:availableTimes);
				tmp.add(currentRoll);
				new Thread(new SaveClientService(tmp)).run();
				loadPlayerName = playerNameTextField.getText();
				SimpleDateFormat sfd = new SimpleDateFormat();
				sfd.applyPattern("yyyy-mm-dd HH:mm:ss");
				loadTime = sfd.format(new Date());
				update = true;
			}else{
				tmp.add("Update");
				tmp.add(loadPlayerName);
				tmp.add(Score.finalScoreMap);
				tmp.add(buttonConditionMap);
				tmp.add(totalRound);
				tmp.add(availableTimes==3?2:availableTimes);
				tmp.add(loadPlayerName);
				tmp.add(loadTime);
				tmp.add(currentRoll);
				new Thread(new UpdateOneRecordClientService(tmp)).run();
			}
		});
		loadGameMenuItem.addActionListener(e->{
			// TODO load game
			List<Object> tmp = new ArrayList<>();
			tmp.add("LoadAll");
			new Thread(new LoadAllRecordClientService(tmp)).run();
			new LoadGameFrame();
		});
	}

	private JPanel getPlayerNamePanel(){
		playerNamePanel = new JPanel();
		JLabel playerNameLabel = new JLabel("Player Name:");
		playerNamePanel.add(playerNameLabel);
		playerNamePanel.add(getPlayerNameTextField());
		playerNameTextField.setText("default player");
		return playerNamePanel;
	}

	private JPanel getUpperSectionPanel(){
		upperSectionPanel = new JPanel();
		upperSectionPanel.setMaximumSize(new Dimension(400,430));
		upperSectionPanel.setBorder(BorderFactory.createTitledBorder("Upper Section"));
		upperSectionPanel.add(createLinePanel(acesButton,acesTextField,140));
		upperSectionPanel.add(createLinePanel(twosButton,twosTextField,138));
		upperSectionPanel.add(createLinePanel(threesButton,threesTextFiled,130));
		upperSectionPanel.add(createLinePanel(foursButton,foursTextField,135));
		upperSectionPanel.add(createLinePanel(fivesButton,fivesTextField,135));
		upperSectionPanel.add(createLinePanel(sixesButton,sixesTextField,135));
		upperSectionPanel.add(createLinePanel(new JLabel("Score Subtotal"),scoreSubtotalTextField,120));
		upperSectionPanel.add(createLinePanel(new JLabel("Bonus"),bonusTextField,175));
		upperSectionPanel.add(createLinePanel(new JLabel("Grand Total"),upperSectionGrandTotalTextField,140));
		return upperSectionPanel;
	}

	private JPanel getLowerSectionPanel(){
		lowerSectionPanel = new JPanel();
		lowerSectionPanel.setMaximumSize(new Dimension(400,470));
		lowerSectionPanel.setBorder(BorderFactory.createTitledBorder("Lower Section"));
		lowerSectionPanel.add(createLinePanel(threeOfAKindButton,threeOfAKindTextField,100));
		lowerSectionPanel.add(createLinePanel(fourOfAKindButton,fourOfAKindTextField,100));
		lowerSectionPanel.add(createLinePanel(fullHouseButton,fullHouseTextField,100));
		lowerSectionPanel.add(createLinePanel(smallStraightButton,smallStraightTextField,78));
		lowerSectionPanel.add(createLinePanel(largeStraightButton,largeStraightTextField,78));
		lowerSectionPanel.add(createLinePanel(yahtzeeButton,yahtzeeTextField,120));
		lowerSectionPanel.add(createLinePanel(chanceButton,chanceTextField,120));
		lowerSectionPanel.add(createLinePanel(new JLabel("Yahtzee Bonus"),yahtzeeBonusTextField,120));
		lowerSectionPanel.add(createLinePanel(new JLabel("Total of lower section"),totalOfLowerSectionTextField,75));
		lowerSectionPanel.add(createLinePanel(new JLabel("Grand Total"),lowerSectionGrandTotalTextField,140));
		return lowerSectionPanel;
	}

	private JPanel getLeftVertexPanel(){
		leftVertexPanel = new JPanel();
		leftVertexPanel.setPreferredSize(new Dimension(350,900));
		leftVertexPanel.setLayout(new BoxLayout(leftVertexPanel,BoxLayout.Y_AXIS));
		leftVertexPanel.add(getUpperSectionPanel());
		leftVertexPanel.add(getLowerSectionPanel());
		return leftVertexPanel;
	}

	private JPanel getRightVertexPanel(){
		rightVertexPanel = new JPanel();
		rightVertexPanel.setMaximumSize(new Dimension(150,900));
		rightVertexPanel.setLayout(new BoxLayout(rightVertexPanel,BoxLayout.Y_AXIS));
		JPanel subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel,BoxLayout.Y_AXIS));
		subPanel.setMaximumSize(new Dimension(140,700));
		subPanel.add(dice1ImagePanel);
		subPanel.add(dice1CheckBox);
		subPanel.add(Box.createVerticalStrut(15));
		subPanel.add(dice2ImagePanel);
		subPanel.add(dice2CheckBox);
		subPanel.add(Box.createVerticalStrut(15));
		subPanel.add(dice3ImagePanel);
		subPanel.add(dice3CheckBox);
		subPanel.add(Box.createVerticalStrut(15));
		subPanel.add(dice4ImagePanel);
		subPanel.add(dice4CheckBox);
		subPanel.add(Box.createVerticalStrut(15));
		subPanel.add(dice5ImagePanel);
		subPanel.add(dice5CheckBox);
		rightVertexPanel.add(subPanel);
		rightVertexPanel.add(rollButton);
		JPanel tmp = new JPanel();
		tmp.setMinimumSize(new Dimension(2,40));
		subPanel.add(tmp);
		rightVertexPanel.add(turnLabel);
		rightVertexPanel.add(rollLabel);
		return rightVertexPanel;
	}

	private JPanel getControlPanel(){
		controlPanel = new JPanel();
		controlPanel.setPreferredSize(new Dimension(600,1000));
		controlPanel.setLayout(new BoxLayout(controlPanel,BoxLayout.X_AXIS));
		controlPanel.add(getLeftVertexPanel());
		controlPanel.add(getRightVertexPanel());
		return controlPanel;
	}

	private JPanel createLinePanel(JComponent a, JComponent b,int gap){
		JPanel jPanel = new JPanel();
		jPanel.add(a);
		jPanel.add(Box.createHorizontalStrut(gap));
		jPanel.add(b);
		return jPanel;
	}

	private JTextField getPlayerNameTextField(){
		playerNameTextField = new JTextField(20);
		return playerNameTextField;
	}

	public static void main(String args[]) {
		YahtzeeFrame yahtzee = new YahtzeeFrame();
		yahtzee.setVisible(true);
		yahtzee.setResizable(false);
	}
}
