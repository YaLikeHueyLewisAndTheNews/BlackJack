package app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class BlackJack{

    private Deck deck;
    private ArrayList<Player> players;
    private HashSet<Player> playersInGame;   //keeps track of players that still have coins to bet with
    private HashSet<Player> playersInRound;  //keeps track of players currently in the round (hit/stay/fold)
    private final static String DEALER = "Dealer";
    private final static String PLAYER = "Player";
    private Player dealer;
    private Integer startCoins;
    private Integer numOfPlayers;


    public BlackJack(Integer startingCoins, Integer numOfPlayers){
        this.startCoins = startingCoins;
        this.numOfPlayers = numOfPlayers;
        startNewGame();
    }

    private void generatePlayers(){
        for(int i = 1; i <= this.numOfPlayers; i++){
            Player p = new Player(this.startCoins, PLAYER + i);
            p.addCard(deck.drawCard());
            p.addCard(deck.drawCard());
            players.add(p);
        }
        playersInGame.addAll(players);
    }

    private void initializeDealer(){
        dealer = new Player(this.startCoins, DEALER);
        dealer.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());
    }

    public void startNewGame(){
        this.deck = new Deck();
        players = new ArrayList<Player>(this.numOfPlayers);
        generatePlayers();
        initializeDealer();
    }

    public void playGame(){
        do{
            handleBets();
        }while(!playersInGame.isEmpty());
    }

    public void handleBets(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    }


}