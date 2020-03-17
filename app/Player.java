package app;

import java.util.ArrayList;

public class Player {

    private Integer sum;
    private ArrayList<PlayingCard> hand;
    private Integer coins;
    private String name;
    private Integer bettingCoins;
    private Boolean stay;


    public Player(Integer coins, String name){
        this.sum = 0;
        this.hand = new ArrayList<PlayingCard>();
        this.coins = coins;
        this.name = name;
        this.stay = false;
        this.bettingCoins = 0;
    }

    public void addCard(PlayingCard card){
        this.sum += card.getValue();
        this.hand.add(card);
    }

    public void clearHand(){
        this.sum = 0;
        this.hand.clear();
        this.stay = false;
        this.bettingCoins = 0;
    }

    public boolean isBust(){
        return sum > 21;
    }

    public boolean isTwentyOne(){
        return sum == 21;
    }
    
    public void addCoins(Integer coins){
        this.coins += coins;
    }

    public void removeCoins(Integer coins){
        this.coins -= coins;
    }

    public Integer getCoins(){
        return this.coins;
    }

    public String getName(){
        return this.name;
    }

    public void stayHand(){
        this.stay = true;
    }

    public void setBettingCoins(Integer bettingCoins){
        if(bettingCoins > this.coins){
            throw new ArithmeticException("You can't bet more coins than you have!");
        }
        this.bettingCoins = bettingCoins;
    }
}