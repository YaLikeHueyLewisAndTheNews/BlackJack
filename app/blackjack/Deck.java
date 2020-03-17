package app.blackjack;

import java.util.*;

public class Deck {

    private ArrayList<PlayingCard> cards = new ArrayList<PlayingCard>();

    public Deck(){
        generateCards();
    }

    public void generateCards(){

        cards.clear();

        for(SuitType x : SuitType.values()){
            for(RankType y : RankType.values()){
                cards.add(
                    new PlayingCard(x, y)
                );
            }
        }

        Collections.shuffle(cards);
    }

    public PlayingCard drawCard(){
        return cards.remove(0);
    }
 
    public List<PlayingCard> drawCards(Integer num){

        ArrayList<PlayingCard> cardsToReturn = new ArrayList<PlayingCard>();

        for(Integer i = 0; i < num; i++){
            cardsToReturn.add(this.cards.remove(0));
        }

        return cardsToReturn;
    }

    public String toString(){

        String cardString = "";

        for(PlayingCard card : cards){
            cardString += card.toString() + "\n";
        }

        return cardString;
    }
}