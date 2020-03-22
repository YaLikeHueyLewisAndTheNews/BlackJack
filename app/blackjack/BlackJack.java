package app.blackjack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Random;

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

    public void setStartingCoins(Integer coins){
        this.startCoins = coins;
    }

    public void setNumOfPlayers(Integer numOfPlayers){
        this.numOfPlayers = numOfPlayers;
    }

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
            clearPlayers();
        }while(!playersInGame.isEmpty());
    }

    private void clearPlayers(){
        for(Player p : playersInGame){
            if(p.getCoins() == 0){
                playersInGame.remove(p);
            }else{
                p.clearHand();
            }
        }
    }

    private void handleRound(){

        playersInRound = new HashSet<Player>(playersInGame);
        handleBets();
        distributeStartingCards();
        checkPlayerHands();
        handlePlayersHitOrStay();


        if(!playersInRound.isEmpty()){
            Player highestHand = getPlayerWithHighestHand();
            handlePlayerThatWon(highestHand);
        }

    }

    private Player getPlayerWithHighestHand(){

        Player highestHand = dealer;

        for(Player p : playersInRound){
            if(p.getSum() > highestHand.getSum()){
                highestHand = p;
            }
        }

        return highestHand;
    }

    private void handlePlayerThatLost(Player p){
        this.playersInRound.remove(p);
        System.out.println(p.getName() + " has busted with " + p.getSum() + "! Your hand was: \n" + p.getHand());
    }

    private void handlePlayerThatWon(Player p){
        this.playersInRound.clear();
        System.out.println(p.getName() + " has won! Your hand was: \n" + p.getHand());
        System.out.println("You win " + bettingPot + " coins.");
        p.addCoins(bettingPot);
    }

    private void handlePlayersHitOrStay(){
        for(Player p : playersInRound){

            String input;

            do{
                input = "";
                try{
                    input = ReadInputUtility.getUserInput(p.getName() + ": Current hand: \n" + p.getHand() + "\n" + "Hit or Stay?");
                    if(input.equalsIgnoreCase("hit")){
                        p.addCard(deck.drawCard());
                        if(p.isBust()){
                            handlePlayerThatLost(p);
                            break;
                        }else if(p.isTwentyOne()){
                            handlePlayerThatWon(p);
                            break;
                        }
                    }else if(!input.equalsIgnoreCase("stay")){
                        throw new InputMismatchException("Please enter hit or stay.");
                    }
                }catch(InputMismatchException i){
                    System.out.println(i.getMessage());
                }catch(Exception e){
                    System.out.println("Ops! Something went wrong. Please try again.");
                }

            }while(!input.equalsIgnoreCase("stay"));  

        }
    }

    private void checkPlayerHands(){
        for(Player p : playersInRound){
            if(p.isBust()){
                handlePlayerThatLost(p);
            }else if(p.isTwentyOne()){
                handlePlayerThatWon(p);
                break;
            }
        }
    }

    public void distributeStartingCards(){
        for(Player p : playersInRound){
            p.addCards(deck.drawCards(2));
        }
        dealer.addCards(deck.drawCards(2));
    }

    private void handleBets(){
        Queue<Player> playersBetting = new Queue<Player>(playersInRound);
        Random r = new Random();
        do{
            Player p = playersBetting.peek();
            try{

                Integer bet = Integer.valueOf(ReadInputUtility.getUserInput(p.getName() + " has " + p.getCoins() + " coins. Please enter the amount of coins you would like to bet:"));

                //If a player chooses not to bet, they are removed from the round
                if(bet == 0){
                    this.playersInRound.remove(p);
                }else{
                    p.betCoins(bet);
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

        Integer dealerBet = r.nextInt(dealer.getCoins()/10);
        dealer.betCoins(dealerBet);
        bettingPot += dealerBet;
    }



}