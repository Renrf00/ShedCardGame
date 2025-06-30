package Creating_a_deck;

import java.util.*;

public class Deck {

    static Random random = new Random();

    public static ArrayList<Card.Suit> getSuitsList() {
        ArrayList<Card.Suit> suitsList = new ArrayList<Card.Suit>(4);
        suitsList.add(Card.Suit.SPADES);
        suitsList.add(Card.Suit.CLUBS);
        suitsList.add(Card.Suit.HEARTS);
        suitsList.add(Card.Suit.DIAMONDS);
        return suitsList;
    }

    public static ArrayList<Card.Rank> getRanksList() {
        ArrayList<Card.Rank> ranksList = new ArrayList<Card.Rank>(13);
        ranksList.add(Card.Rank.TWO);
        ranksList.add(Card.Rank.THREE);
        ranksList.add(Card.Rank.FOUR);
        ranksList.add(Card.Rank.FIVE);
        ranksList.add(Card.Rank.SIX);
        ranksList.add(Card.Rank.SEVEN);
        ranksList.add(Card.Rank.EIGHT);
        ranksList.add(Card.Rank.NINE);
        ranksList.add(Card.Rank.TEN);
        ranksList.add(Card.Rank.JACK);
        ranksList.add(Card.Rank.QUEEN);
        ranksList.add(Card.Rank.KING);
        ranksList.add(Card.Rank.ACE);
        return ranksList;
    }

    public ArrayList<Card.specialAbilities> getSpecialAbilitiesList() {
        ArrayList<Card.specialAbilities> specialAbilitiesList = new ArrayList<>();
        specialAbilitiesList.add(Card.specialAbilities.RESTART_PILE);
        specialAbilitiesList.add(Card.specialAbilities.REVERSED_ORDER);
        specialAbilitiesList.add(Card.specialAbilities.INVISIBLE);
        specialAbilitiesList.add(Card.specialAbilities.DISCARD_PILE);
        return specialAbilitiesList;
    }

    public static void createDeck(Stack deck) {
        ArrayList<Card.Suit> suits = getSuitsList();
        ArrayList<Card.Rank> ranks = getRanksList();

        for(int suit = 0; suit < 4; suit++) {
            for(int rank = 0; rank < 13; rank++) {
                switch (rank) {
                    case 0:
                        deck.push(new Card(suits.get(suit), ranks.get(rank), Card.specialAbilities.RESTART_PILE));
                        break;
                    case 5:
                        deck.push(new Card(suits.get(suit), ranks.get(rank), Card.specialAbilities.REVERSED_ORDER));
                        break;
                    case 7:
                        deck.push(new Card(suits.get(suit), ranks.get(rank), Card.specialAbilities.INVISIBLE));
                        break;
                    case 8:
                        deck.push(new Card(suits.get(suit), ranks.get(rank), Card.specialAbilities.DISCARD_PILE));
                        break;
                    default:
                        deck.push(new Card(suits.get(suit), ranks.get(rank), Card.specialAbilities.NOTHING));
                }
            }
        }
    }

    public static void addCard(Stack<Card> deck, Card card) {
        deck.add(random.nextInt(deck.size() - 1), card);
    }

    public static void addCard(Stack<Card> deck, Card card, int intoPosition) {
        if (intoPosition < 0 || intoPosition >= deck.size()) {
            System.out.println("The cards position is out of bounds, it should be between 0 and " + (deck.size() - 1));
            return;
        }
        deck.add(intoPosition, card);
    }

    public static void addCards(Stack<Card> deck, Stack<Card> deckToAdd, Boolean shuffleCards) {
        if (shuffleCards) {
            for (Card card : deckToAdd) {
                deck.add(random.nextInt(deck.size() - 1), card);
            }
        } else {
            for (Card card : deckToAdd) {
                deckToAdd.push(card);
            }
        }
    }

    public static void deleteCard(Stack<Card> deck, Card cardToDelete) {
        deck.removeIf(card -> card.equals(cardToDelete));
    }

    public static void deleteCards(Stack<Card> deck, Stack<Card> deckToDelete) {
        for (Card card : deck) {
            for (Card cardToDelete : deckToDelete) {
                if (card.equals(cardToDelete)) {
                    deck.remove(card);
                }
            }
        }
    }

    public static void deleteSuit(Stack<Card> deck, Card.Suit suit) {
        deck.removeIf(card -> card.getSuit().equals(suit));
    }

    public static void deleteRank(Stack<Card> deck, Card.Rank rank) {
        deck.removeIf(card -> card.getRank().equals(rank));
    }

    public static void shuffleDeck(Stack<Card> deck) {
        Stack<Card> temporaryDeck = new Stack<>();
        int index;

        while (!deck.isEmpty()) {
            index = random.nextInt(deck.size() - 1);
            temporaryDeck.push(deck.get(index));
            deck.remove(index);
        }
        deck = temporaryDeck;
    }

    //  not finished
//    public static void ReorderDeck(Stack<Card> deck) {
//        ArrayList<Card.Suit> suitsList = getSuitsList();
//        ArrayList<Card.Rank> ranksList = getRanksList();
//
//        for (Card card: deck) {
//            switch (card.getSuit()) {
//                case SPADES:
//
//            }
//        }
//    }

    //  not finished
//    public static void ReorderDeck(Stack<Card> deck, ArrayList<Card.Suit> suitsList, ArrayList<Card.Rank> ranksList, String prioritizeSuitOrRank) {
//        Stack<Card> temporaryDeck = new Stack<>();
//        Map<String, Stack<Card>> mainSort = new HashMap<>();
//        Stack<Card> secondarySort = new Stack<>();
//
//        if (prioritizeSuitOrRank.equals("Suit")) {
//            mainSort = separateBySuit(deck);
//            for (String suitString: mainSort.keySet()) {
//                secondarySort = mainSort.get(suitString);
//                mainSort.get(suitString).clear();
//                for (Card card: secondarySort) {
    //  Continue from here
//                }
//            }
//        }
//        else if (prioritizeSuitOrRank.equals("Rank")) {
//
//        } else {
//            System.out.println("Input error: " + prioritizeSuitOrRank + ", should be either 'Suit' or 'Rank'");
//        }
//    }

    public static Map<String, Stack<Card>> separateBySuit(Stack<Card> deck) {
        Map<String, Stack<Card>> separatedDeck = new HashMap<>();
        for (Card card : deck) {
            separatedDeck.putIfAbsent(card.getSuit().toString(), new Stack<>());
            addCard(separatedDeck.get(card.getSuit().toString()), card);
        }
        return separatedDeck;
    }

    public static Map<String, Stack<Card>> separateByRank(Stack<Card> deck) {
        Map<String, Stack<Card>> separatedDeck = new HashMap<>();
        for (Card card : deck) {
            separatedDeck.putIfAbsent(card.getRank().toString(), new Stack<>());
            addCard(separatedDeck.get(card.getRank().toString()), card);
        }
        return separatedDeck;
    }

    public static Card getTopCard(Stack<Card> deck) {
        return deck.pop();
    }

    public static Stack<Card> getXTopCards(Stack<Card> deck, int numberOfCards) {
        if (numberOfCards <= 0 || numberOfCards > deck.size()) {
            System.out.println("Invalid number of Cards: " + numberOfCards);
            return null;
        }
        Stack<Card> temporaryDeck = new Stack<>();
        for (int i = 0; i < numberOfCards; i++) {
            temporaryDeck.addLast(deck.pop());
        }
        return temporaryDeck;
    }

    public static Card getBottomCard(Stack<Card> deck) {
        Card bottomCard = deck.getLast();
        deck.removeLast();
        return bottomCard;
    }

    public static Stack<Card> getXBottomCards(Stack<Card> deck, int numberOfCards) {
        if (numberOfCards <= 0 || numberOfCards > deck.size()) {
            System.out.println("Invalid number of Cards: " + numberOfCards);
            return null;
        }
        Stack<Card> temporaryDeck = new Stack<>();
        for (int i = 0; i < numberOfCards; i++) {
            temporaryDeck.addLast(deck.getLast());
            deck.removeLast();
        }
        return temporaryDeck;
    }

    public static Card getRandomCard(Stack<Card> deck) {
        int index = random.nextInt(deck.size() - 1);
        Card randomCard = deck.get(random.nextInt(index));
        deck.remove(index);
        return randomCard;
    }

    public static Stack<Card> getRandomCards(Stack<Card> deck, int numberOfCards) {
        if (numberOfCards <= 0 || numberOfCards > deck.size()) {
            System.out.println("Invalid number of Cards: " + numberOfCards);
            return null;
        }
        Stack<Card> temporaryDeck = new Stack<>();
        int index;
        for (int i = 0; i < numberOfCards; i++) {
            index  = random.nextInt(deck.size() - 1);
            temporaryDeck.addLast(deck.get(index));
            deck.remove(index);
        }
        return temporaryDeck;
    }

}
