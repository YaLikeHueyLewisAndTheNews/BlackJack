package app.blackjack;

public class PlayingCard extends Card{

    private SuitType suitType;
    private RankType rankType;

    public PlayingCard(SuitType suitType, RankType rank){
        super(rank.value);
        this.suitType = suitType;
        this.rankType = rank;
    }
    public PlayingCard(){}

    public PlayingCard withSuit(SuitType suitType){
        this.suitType = suitType;
        return this;
    }

    public PlayingCard withRank(RankType rank){
        this.rankType = rank;
        return this;

    }

    public RankType getRank(){
        return this.rankType;
    }

    public String toString(){
        return this.suitType.toString() + " : " + this.rankType.toString();
    }

}