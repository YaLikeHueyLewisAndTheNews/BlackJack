package app.blackjack;

import java.util.ArrayList;
import java.util.HashSet;
import app.utility.Queue;
import app.utility.ReadInputUtility;

public class BlackJack{
    
    private Deck deck;
    private ArrayList<Player> players;
    private HashSet<Player> playersInGame;   //keeps track of players that still have coins to bet with
    private HashSet<Player> playersInRound;   //keeps track of players that still have coins to bet with
    public final static String DEALER = "Dealer";
    public final static String PLAYER = "Player";
    private Player dealer;
    private Integer startCoins;
    private Integer numOfPlayers;
    private Integer bettingPot;


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
        bettingPot = 0;
        playersInGame = new HashSet<Player>();
        playersInRound = new HashSet<Player>();
        this.deck = new Deck();
        players = new ArrayList<Player>(this.numOfPlayers);
        generatePlayers();
        initializeDealer();
    }

    public void playGame(){
        do{
            bettingPot = 0;
            handleRound();
            break;
        }while(!playersInGame.isEmpty());
    }

    private void handleRound(){

        //Start of the round
        playersInRound.addAll(playersInGame);
        handleBets();
        distributeStartingCards();
        do{
            handlePhase();
            break;
        }while(!playersInRound.isEmpty());

    }

    private void handlePhase(){

    }

    public void distributeStartingCards(){
        for(Player p : playersInRound){
            p.addCards(deck.drawCards(2));
        }
        dealer.addCards(deck.drawCards(2));
    }

    private void handleBets(){
        Queue<Player> playersBetting = new Queue<Player>(playersInRound);
        do{
            Player p = playersBetting.peek();
            try{

                Integer bet = Integer.valueOf(ReadInputUtility.getUserInput(p.getName() + " please enter the amount of coins you would like to bet:"));

                //If a player chooses not to bet, they are removed from the round
                if(bet == 0){
                    this.playersInRound.remove(p);
                }else{
                    p.setBettingCoins(bet);
                    bettingPot += bet;
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