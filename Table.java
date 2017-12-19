import java.util.ArrayList;

public class Table {
	final static int MAXPLAYER = 4;  // 最多一張牌桌能坐幾個人
	private Deck deck;
	private Player[] players;
	private Dealer dealer;
	private int[] pos_betArray;
	
	private ArrayList<Card> tempCards;

	public Table(int nDeck) {
		deck = new Deck(nDeck);
		players = new Player[MAXPLAYER];
		pos_betArray = new int[MAXPLAYER];
		tempCards = new ArrayList<Card>();
	}
	
	public void set_player(int pos, Player p) {
		players[pos] = p;
	}
	
	public Player[] get_player() {
		return players;
	}
	
	public void set_dealer(Dealer d) {
		dealer = d;
	}
	
	public Card get_face_up_card_of_dealer() {
		return dealer.getOneRoundCard().get(1);
	}
	
	private void ask_each_player_about_bets() {
		for (int i = 0; i < players.length; i++) {
			if (players[i] == null) 
				continue;
			players[i].sayHello();
			pos_betArray[i] = players[i].makeBet();
			
			// 判斷玩家是否有足夠的籌碼，如果沒有則印出訊息且踢出遊戲。
			if (pos_betArray[i] == -1) {
				System.out.println(players[i].getName() + " don't have enough chips!");
				players[i] = null;
			}
		}
	}
	
	private void distribute_cards_to_dealer_and_players() {
		for (int i = 0; i < players.length; i++) {
			if (players[i] == null) 
				continue;
			tempCards.clear();
			tempCards.add(deck.getOneCard(true));
			tempCards.add(deck.getOneCard(true));
			tempCards.add(deck.getOneCard(false));
			players[i].setOneRoundCard(new ArrayList(tempCards));
		}
		tempCards.clear();
		tempCards.add(deck.getOneCard(true));
		dealer.setOneRoundCard(new ArrayList(tempCards));
		
		System.out.print("Dealer's face up card is ");
		dealer.getOneRoundCard().get(0).printCard();
	}
	
	private void ask_each_player_about_hits() {
		for (int i = 0; i < players.length; i++) {
			if (players[i] == null) 
				continue;
			boolean hit = false;
			tempCards.clear();
			tempCards.addAll(players[i].getOneRoundCard());
			do {
				if (hit && !(players[i].getTotalValue() > 21)) {
					hit = players[i].hit_me(this);
					tempCards.add(deck.getOneCard(true));
					players[i].setOneRoundCard(new ArrayList(tempCards));
					System.out.println("Hit! " + players[i].getName() + "'s Cards now:");
					players[i].printAllCard();
				} else {
					System.out.println(players[i].getName()+", Pass hit!");
					players[i].printAllCard();
				}
			} while (hit);
		}
	}
	
	private void ask_dealer_about_hits() {
		boolean hit = false;
		tempCards.clear();
		tempCards.addAll(dealer.getOneRoundCard());
		do {
			hit = dealer.hit_me(this);
			if (hit) {
				tempCards.add(deck.getOneCard(true));
				dealer.setOneRoundCard(new ArrayList(tempCards));
			}
		} while (hit);
		System.out.println("Dealer's hit is over!");
	}
	
	private void calculate_chips() {
		System.out.println("Dealer's card value is " + dealer.getTotalValue() + " ,Cards:");
		dealer.printAllCard();
		for (int i = 0; i < players.length; i++) {
			if (players[i] == null) 
				continue;
			System.out.print(players[i].getName() + " card value is " + players[i].getTotalValue());
			if (dealer.getTotalValue() > 21) {
				if (players[i].getTotalValue() > 21) {
					System.out.println(", chips have no change! The Chips now is: " + players[i].getCurrentChips());
				} else {
					players[i].increaseChips(pos_betArray[i]);
					System.out.println(", Get " + pos_betArray[i] + 
							" Chips, the Chips now is: " + players[i].getCurrentChips());
				}
			} else {
				if (players[i].getTotalValue() > 21 || players[i].getTotalValue() < dealer.getTotalValue()) {
					players[i].increaseChips(-pos_betArray[i]);
					System.out.println(", Lose " + pos_betArray[i] + 
							" Chips, the Chips now is: " + players[i].getCurrentChips());
				} else if (players[i].getTotalValue() == dealer.getTotalValue()) {
					System.out.println(", chips have no change! The Chips now is: " + players[i].getCurrentChips());
				} else {
					players[i].increaseChips(pos_betArray[i]);
					System.out.println(", Get " + pos_betArray[i] + 
							" Chips, the Chips now is: " + players[i].getCurrentChips());
				}
			}
		}
	}
	
	public int[] get_palyers_bet() {
		return pos_betArray;
	}
	
	public void play() {
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}
}
