public class Card {
	public enum Suit {Club, Diamond, Heart, Spade}
	private Suit suit; 
	private int rank; //1~13
	
	/**
	 * @param s suit
	 * @param r rank
	 */
	public Card(Suit s, int r) {
		suit = s;
		rank = r;
	}	
	
	public void printCard() {
		//Hint: print (System.out.println) card as suit,rank, for example: print 1,1 as Clubs Ace
		switch(getSuit()) {
		case Club:
			System.out.print("Clubs ");
			break;
		case Diamond:
			System.out.print("Diamonds ");
			break;
		case Heart:
			System.out.print("Hearts ");
			break;
		case Spade:
			System.out.print("Spades ");
			break;
		default:
			System.out.print("N/A ");
		}
		
		switch(getRank()) {
		case 1:
			System.out.println("Ace");
			break;
		case 2:
			System.out.println("Two");
			break;
		case 3:
			System.out.println("Three");
			break;
		case 4:
			System.out.println("Four");
			break;
		case 5:
			System.out.println("Five");
			break;
		case 6:
			System.out.println("Six");
			break;
		case 7:
			System.out.println("Seven");
			break;
		case 8:
			System.out.println("Eight");
			break;
		case 9:
			System.out.println("Nine");
			break;
		case 10:
			System.out.println("Ten");
			break;
		case 11:
			System.out.println("Jack");
			break;
		case 12:
			System.out.println("Queen");
			break;
		case 13:
			System.out.println("King");
			break;
		default:
			System.out.println("N/A");
		}

	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public int getRank() {
		return rank;
	}
}