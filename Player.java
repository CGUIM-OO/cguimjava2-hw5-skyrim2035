import java.util.ArrayList;

public class Player extends Person {
	private String name;  // 玩家姓名
	private int chips;  // 玩家有的籌碼
	private int bet;  // 玩家此局下注的籌碼
	private int sum = 0;
	
	public Player(String name, int chips) {
		this.name = name;
		this.chips = chips;
	}
	
	// 下注，回傳預計下注的籌碼
	// 基本下注：一次1元
	// 要檢查是否還有錢，沒錢了就不能再繼續下注
	public int makeBet() {
		bet = 1;
		if(bet > chips) {
			bet = -1;
		}
		return bet;
	}
	
	// 玩家籌碼變動
	public void increaseChips (int diff) {
		chips += diff;
	}
	
	// 是否要牌，是回傳true，不再要牌則回傳false
	// 基本參考條件：16點以下要牌，17點以上不要牌
	// 提示：用oneRoundCard來算
	@Override
	public boolean hit_me(Table table) {
		// int sum = 0;
		for(int i = 0; i < getOneRoundCard().size(); i++) {
			int temp = getOneRoundCard().get(i).getRank();
			if(temp == 11 || temp == 12 || temp == 13) {
				temp = 10;
			}
			sum += temp;
		}
		
		if(sum <= 16)
			return true;
		else
			return false;
	}
	
	public String getName() {
		return name;
	}
	
	public int getCurrentChips() {
		return chips;
	}
	
	// 玩家 Say Hello
	public void sayHello() {
		System.out.println("Hello, I am " + name + ".");
		System.out.println("I have " + chips + " chips.");
	}
}
