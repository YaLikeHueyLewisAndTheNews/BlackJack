package app.blackjack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import app.utility.Queue;

public class BlackJack{
    
    private Deck deck;
    private ArrayList<Player> players;
    private HashSet<Player> playersInGame;   //keeps track of players that still have coins to bet with
    private HashSet<Player> playersInRound;   //keeps track of players that still have coins to bet with
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
            players.add(p);
        }
        playersInGame.addAll(players);
    }

    private void initializeDealer(){
        this.dealer = new Player(this.startCoins * 10, DEALER);
    }

    public void startNewGame(){
        this.deck = new Deck();
        players = new ArrayList<Player>(this.numOfPlayers);
        generatePlayers();
        initializeDealer();
    }

    public void playGame(){
        do{
            handleRound();
        }while(!playersInGame.isEmpty());
    }

    private void handleRound(){

        //Start of the round
        playersInRound.addAll(playersInGame);
        handleBets();
        distributeStartingCards();

    }

    public void distributeStartingCards(){
        for(Player p : playersInRound){
            p.addCards(deck.drawCards(2));
        }
        dealer.addCards(deck.drawCards(2));
    }

    private void handleBets(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Queue<Player> playersBetting = new Queue<Player>(playersInRound);
        do{
            Player p = playersBetting.peek();
            System.out.println(p.getName() + " please enter the amount of coins you would like bet:");
            try{

                Integer bet = Integer.valueOf(reader.readLine());

                //If a player chooses not to bet, they are removed from the round
                if(bet == 0){
                    this.playersInRound.remove(p);
                }else{
                    p.setBettingCoins(bet);
                }

                playersBetting.remove();

            }catch(ArithmeticException a){
                System.out.println(a.getMessage());
            }catch(NumberFormatException n){
                System.out.println("Please enter a valid number.");
            }catch(Exception ex){
                System.out.println("Ops! Something went wrong. Please try again.");
            }

        }while(!playersBetting.isEmpty());
    }

    public void setStartingCoins(Integer coins){
        this.startCoins = coins;
    }

    public void setNumOfPlayers(Integer numOfPlayers){
        this.numOfPlayers = numOfPlayers;
    }

}