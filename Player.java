public class Player{

	private double balance;
	private Hand playerHand;
	
	public Player(double balance){
		this.balance=balance;
		playerHand = new Hand();
	}

	public void deal(Card c){
		this.playerHand.addCard(c);
	}

	public Card[] discard(){
		int amt=(int)(Math.random()*5);
		int[] pos = new int[amt];
		for(int i=0; i<amt; i++) {
			pos[i]=(int)(Math.random()*4);
			for(int t = i; t>=0; t--) {
				if(i!=t) {
					while(pos[t]==pos[i]) {
						pos[i]=(int)(Math.random()*4);
					}
				}
			}
		}
		Card[] remove = new Card[amt];
		for(int i=0; i<amt; i++) {
			remove[i] = playerHand.getCard(pos[i]);
			playerHand.removeCard(pos[i]);
		}
		return remove;
	}
	
	public double wager(double min){
		if(balance<min)
			return -1;
		double amt = Math.random()*(balance-min)+min;
		if(amt==-1)
			return -1;	
		balance-=amt;
		return amt;
	}

	public Hand showHand(){
		return this.playerHand;
	}

	public double getBalance(){
		return this.balance;
	}

	public void winnings(double amount){
		this.balance+=amount;
		playerHand = new Hand();
	}
}
