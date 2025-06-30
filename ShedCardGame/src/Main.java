import Creating_a_deck.Card;
import Creating_a_deck.Deck;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Stack<Card> discardPile = new Stack<>();
        Stack<Card> drawPile = new Stack<>();
        Stack<Card> hand = new Stack<>();
        Deck.createDeck(drawPile);
        Deck.createDeck(drawPile);
        Deck.shuffleDeck(drawPile);
    }
}
