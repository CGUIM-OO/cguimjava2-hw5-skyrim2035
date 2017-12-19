import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> cards;
	private ArrayList<Card> usedCard;
	private ArrayList<Card> openCard;
	public int nUsed;
	
	public Deck(int nDeck) {
		cards = new ArrayList<Card>();
		//1 Deck have 52 cards, https://en.wikipedia.org/wiki/Poker
		//Hint: Use new Card(x,y) and 3 for loops to add card into deck
		//Sample code start
		//Card card=new Card(1,1); ->means new card as clubs ace
		//cards.add(card);
		//Sample code end
		for(int n = 0; n < nDeck; n++) {
			for(Card.Suit i : Card.Suit.values()) {
				for(int j = 1; j <= 13; j++) {
					Card card = new Card(i,j);
					cards.add(card);
				}
			}
		}
		shuffle();
	}	
	
	public void printDeck() {
		//Hint: print all items in ArrayList<Card> cards, 
		for(int i = 0; i < cards.size(); i++) {
			getAllCards().get(i).printCard();
		}
	}
	
	public ArrayList<Card> getAllCards() {
		return cards;
	}
	
	public ArrayList<Card> getOpenedCard() {
		return openCard;
	}
	
	public void shuffle() {
		Random rnd = new Random();  
		  
		for(int i = 0; i < 52; i++) {
			int j = rnd.nextInt(i + 1);
			cards.add(cards.get(j));
			cards.remove(j);
		}

		usedCard = new ArrayList<Card>();
		openCard = new ArrayList<Card>();
		nUsed = 0;
	}
	
	public Card getOneCard(boolean isOpened) {
		Card tempCard = cards.get(nUsed);
		usedCard.add(tempCard);
		if(isOpened) {
			openCard.add(tempCard);
		}
		nUsed++;
		if(nUsed >= cards.size()) {
			shuffle();
		}
		return tempCard;
	}
}