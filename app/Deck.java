package app;

import java.util.ArrayList;
import java.util.Collections;

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
 

    public String toString(){

        String cardString = "";

        for(PlayingCard card : cards){
            cardString += card.toString() + "\n";
        }

        return cardString;
    }
}