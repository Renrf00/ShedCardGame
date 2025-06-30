package Creating_a_deck;

public class Card extends Deck {
    public enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }
    public enum Rank {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
    }
    public enum specialAbilities {
        NOTHING, RESTART_PILE, REVERSED_ORDER, INVISIBLE, DISCARD_PILE
    }
    private Suit suit;
    private Rank rank;
    private specialAbilities specialAbilities;
    public Card(Suit suit, Rank rank, specialAbilities specialAbilities) {
        setSuit(suit);
        setRank(rank);
        setSpecialAbilities(specialAbilities);
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }
    public specialAbilities getSpecialAbilities() {
        return specialAbilities;
    }
    public void setSpecialAbilities(specialAbilities specialAbilities) {
        this.specialAbilities = specialAbilities;
    }
}
