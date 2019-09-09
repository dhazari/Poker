public class Player2{

	private double balance;
	private Hand playerHand;
	
	//initialize your fields in the constructor
	public Player2(double balance){
		this.balance=balance;
		playerHand = new Hand();
	}

	public void deal(Card c){
		this.playerHand.addCard(c);
	}

	/*Returns an array of Cards that the Player wishes to discard.
	//The game engine will call deal() on this player for each card
	//that exists in the return value. MS2 Instructions: Print the hand to
	//the terminal using System.out.println and ask the user which cards 
	//they would like to discard. The user will first the number of cards they
    //wish to discard, followed by the indices, one at a time, of
	//the card(s) they would like to discard, 
	//Return an array with the appropriate Card objects
	//that have been discarded, and remove the Card objects from this
	//player's hand. Use IO.readInt() for all inputs. In cases of error
	//re-ask the user for input.
	//
	// Example call to discard():
	//
	// This is your hand:
	// 0: Ace of Hearts
	// 1: 2 of Diamonds
	// 2: 5 of Hearts
	// 3: Jack of Spades
	// 4: Ace of Clubs
	// How many cards would you like to discard?
	// 2
	// 1
	// 2
	//
	// The resultant array will contain the 2 of Diamonds and the 5 of hearts in that order
	// This player's hand will now only have 3 cards*/
	public Card[] discard(){
		playerHand.sortByValue();
		System.out.println("this is your hand: ");
		playerHand.printHand();
		System.out.println("How many cards would you like to discard?");
		int amt=IO.readInt();
		while(amt>5) {
			IO.reportBadInput();
			System.out.println("You can only discard up to 5 cards. Try Again");
			amt=IO.readInt();
		}
		int[] pos = new int[amt];
		for(int i=0; i<amt; i++) {
			pos[i]=IO.readInt();
			while(pos[i]>4) {
				IO.reportBadInput();
				System.out.println("This position does not exist. Try again.");
				pos[i]=IO.readInt();
			}
			if(i>0) {
				for(int t=i-1; t>=0; t--) {
					while(pos[i]==pos[t]) {
						IO.reportBadInput();
						System.out.println("You have already chosen to discard this card. Try again.");
						pos[t]=IO.readInt();
					}
				}
			}
		}
		for(int i=0; i<amt; i++) {
			for(int t=i+1; t<amt; t++) {
				while(pos[i]==pos[t]) {
					IO.reportBadInput();
					System.out.println("You have already chosen to discard this card. Try again.");
					pos[t]=IO.readInt();
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

	/*Returns the amount that this player would like to wager, returns
	//-1.0 to fold hand. Any non zero wager should immediately be deducted
	//from this player's balance. This player's balance can never be below
	// 0 at any time. This player's wager must be >= to the parameter min
	// MS2 Instructions: Show the user the minimum bet via the terminal 
	//(System.out.println), and ask the user for their wager. Use
	//IO.readDouble() for input. In cases of error re-ask the user for 
	//input.
	// 
	// Example call to wager()
	//
	// How much do you want to wager?
	// 200
	//
	// This will result in this player's balance reduced by 200*/
	
	public double wager(double min){
		System.out.println("The minimun amout you can bet is "+min+". If your balance is less than the minimum, then you will fold.");
		if(balance<min)
			return -1;
		System.out.println("How much do you want to wager? Enter -1 if you wish to fold.");
		double amt = IO.readDouble();
		if(amt==-1)
			return -1;
		while(amt<min || (balance-amt)<0 || amt>balance) {
			IO.reportBadInput();
			System.out.println("You cannot wager this amount. Try Again.");
			amt=IO.readDouble();
		}	
		balance-=amt;
		return amt;
	}

	//Returns this player's hand
	public Hand showHand(){
		return this.playerHand;
	}

	//Returns this player's current balance
	public double getBalance(){
		return this.balance;
	}

	/*Increase player's balance by the amount specified in the parameter,
	//then reset player's hand in preparation for next round. Amount will
	//be 0 if player has lost hand*/
	public void winnings(double amount){
		this.balance+=amount;
		playerHand = new Hand();
	}
}

