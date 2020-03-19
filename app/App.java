package app;

import app.blackjack.BlackJack;

public class App {
    public static void main(String[] args) throws Exception {
        BlackJack game = new BlackJack(100, 1);
        game.playGame();
    }
}