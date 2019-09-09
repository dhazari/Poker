
/**
 * An object of type Hand represents a hand of cards.  The
 * cards belong to the class Card.  A hand is empty when it
 * is created, and any number of cards can be added to it.
 */


public class Hand {

   private Card[] hand;   // The cards in the hand.
   private int count;

   /**
    * Create a hand that is initially empty.
    */
   public Hand() {
      hand = new Card[5];
	  count = 0;
   }

   /**
    * Remove all cards from the hand, leaving it empty.
    */
   public void clear() {
      for(int i=0 ; i<hand.length; i++){ hand[i] = null; }
	  count = 0;
   }

   /**
    * Add a card to the hand.  It is added at the end of the current hand.
    * @param c the non-null card to be added.
    * @throws NullPointerException if the parameter c is null.
    */
   public void addCard(Card c) {

	  for(int i=0 ; i<hand.length; i++){
		if (hand[i] == null){
			hand[i] = c;
			count = count + 1;
			break;
		}
	 }


   }

   /**
    * Remove a card from the hand, if present.
    * @param c the card to be removed.  If c is null or if the card is not in 
    * the hand, then nothing is done.
    */
 public void removeCard(Card c) {

  for(int i=0 ; i<hand.length; i++){ 
    if (hand[i]!=null && hand[i].equals(c)){
      hand[i] = null;
      count = count-1;
    }
  }

   }
   
   /**
    * Remove the card in a specified position from the hand.
    * @param position the position of the card that is to be removed, where
    * positions are starting from zero.
    * @throws IllegalArgumentException if the position does not exist in
    * the hand, that is if the position is less than 0 or greater than
    * or equal to the number of cards in the hand.
    */
   public void removeCard(int position) {
      if (position < 0 || position >= hand.length)
         throw new IllegalArgumentException("Position does not exist in hand: "
               + position);
      hand[position] = null;
      count --;
   }
   /**
    * Returns the number of cards in the hand.
    */
   public int getCardCount() {
      return count;
   }

   /**
    * Gets the card in a specified position in the hand.  (Note that this card
    * is not removed from the hand!)
    * @param position the position of the card that is to be returned
    * @throws IllegalArgumentException if position does not exist in the hand
    */
   public Card getCard(int position) {
      if (position < 0 || position >= hand.length)
         throw new IllegalArgumentException("Position does not exist in hand: "
               + position);
       return hand[position];
   }

   /**
    * Sorts the cards in the hand so that cards of the same suit are
    * grouped together, and within a suit the cards are sorted by value.
    * Note that aces are considered to have the lowest value, 1.
    */
   public void sortBySuit() {
	  int size = count;
	  int nonnull = 0;
	  int index = 0;

      Card[] newHand = new Card[5];
      while (size > 0) {
		 if (hand[nonnull] == null) { nonnull = nonnull+1; continue;}
         int pos = nonnull;  // Position of minimal card.
         Card c = hand[nonnull];  // Minimal card.

         for (int i = nonnull+1; i < hand.length; i++) {
            Card c1 = hand[i];
			if (c1 != null){
				if ( c1.getSuit() < c.getSuit() ||
						(c1.getSuit() == c.getSuit() && c1.getValue() < c.getValue()) ) {
					pos = i;
					c = c1;
				}
			}
         }
         hand[pos] = null;
		 size = size - 1;
         newHand[index++] = c;
		 nonnull = 0;
      }
      hand = newHand;
   }

   /**
    * Sorts the cards in the hand so that cards of the same value are
    * grouped together.  Cards with the same value are sorted by suit.
    * Note that aces are considered to have the lowest value, 1.
    */
   public void sortByValue() {
	  int size = count;
	  int nonnull = 0;
	  int index = 0;

      Card[] newHand = new Card[5];
      while (size > 0) {
		 if (hand[nonnull] == null) { nonnull = nonnull+1; continue;}
         int pos = nonnull;  // Position of minimal card.
         Card c = hand[nonnull];  // Minimal card.

         for (int i = nonnull+1; i < hand.length; i++) {

			Card c1 = hand[i];
            if (c1 != null){
				if ( c1.getValue() < c.getValue() ||
						(c1.getValue() == c.getValue() && c1.getSuit() < c.getSuit()) ) {
					pos = i;
					c = c1;
				}
			}
         }
         hand[pos] = null;
		 size = size - 1;
         newHand[index++] = c;
		 nonnull = 0;
      }
      hand = newHand;
   }

   public void printHand(){

	   for(int i=0; i<hand.length; i++){
		   if (hand[i] != null){
			   System.out.println(hand[i]);
		   }
	   }
	   System.out.println();
   }

/*---------------------------------------------------------------*/
   public int numPairs(){
	   this.sortByValue();
	   int amt=1;
	   int pairs=0;
	   for(int i=1; i<hand.length; i++){
		   if(hand[i].getValue()==hand[i-1].getValue()){
			   amt++;
		   }
		   else{
		   		if(amt==2||amt==3){
					pairs++;
				}
				else if(amt==4|| amt==5){
					pairs+=2;
				}
				amt=1;
			}
	   }
	   if(amt==2||amt==3){
	   		pairs++;
	   	}
	   	else if(amt==4|| amt==5){
	   		pairs+=2;
		}
	   return pairs;
   }
/*---------------------------------------------------------------*/
	public boolean hasTriplet(){
		this.sortByValue();
		int amt=1;
		for(int i=1; i<hand.length; i++){
			if(hand[i].getValue()==hand[i-1].getValue()){
				amt++;
			}
			else{
				if(amt>=3){
					return true;
				}
				else{
					amt=1;
				}
			}
		}
		if(amt>=3)
			return true;
	   	return false;
	}
/*---------------------------------------------------------------*/
   	public boolean hasFlush(){
	   for(int i=0; i<hand.length-1; i++){
		   if(hand[i].getSuit()!=hand[i+1].getSuit())
		   		return false;
	   }
	   return true;
   }
/*---------------------------------------------------------------*/
   public boolean hasStraight(){
	   	this.sortByValue();
	   	boolean ret=true;
	   	for(int i=1; i<hand.length; i++){
			if(hand[i].getValue()==hand[i-1].getValue()+1)
				ret=true;
			else{
				ret=false;
				break;
			}
		}
		if(ret==false){
			if(hand[0].getValue()==1){
				for(int i=2; i<5; i++){
					if(hand[i].getValue()==hand[i-1].getValue()+1)
						ret=true;
					else{
						ret=false;
						break;
					}
				}
				if(ret==true && hand[4].getValue()==13)
					ret=true;
				else
					ret=false;
			}
		}
		return ret;
	}
/*---------------------------------------------------------------*/
	public boolean hasFullHouse(){
		this.sortByValue();
		if((hand[0].getValue()==hand[1].getValue() && hand[1].getValue()==hand[2].getValue() && hand[3].getValue()==hand[4].getValue()) || (hand[0].getValue()==hand[1].getValue() && hand[2].getValue()==hand[3].getValue() && hand[3].getValue()==hand[4].getValue()))
			return true;
		return false;
	}
/*---------------------------------------------------------------*/
	public boolean hasFourOfAKind(){
		this.sortByValue();
		int amt=1;
		for(int i=1; i<hand.length; i++){
			if(hand[i].getValue()==hand[i-1].getValue()){
				amt++;
			}
			else{
				if(amt>=4){
					return true;
				}
				else{
					amt=1;
				}
			}
		}
		if(amt>=4)
			return true;
	   	return false;
	}
/*---------------------------------------------------------------*/
	public Card highestValue(){
		this.sortByValue();
		if(hand[0].getValue()==1)
			return hand[0];
		return hand[hand.length-1];
	}
/*---------------------------------------------------------------*/
	public Card highestDuplicate(){
		this.sortByValue();
		if(this.hasFourOfAKind()){
			int amt=1;
			for(int i=1; i<hand.length; i++){
				if(hand[i].getValue()==hand[i-1].getValue()){
					amt++;
				}
				else{
					if(amt>=4){
						return hand[i-1];
					}
					else{
						amt=1;
					}
				}
			}
			if(amt>=4)
				return hand[4];
		}
		else if(this.hasFullHouse())
			return this.highestValue();
		else if (this.hasTriplet()){
			int amt=1;
			for(int i=1; i<hand.length; i++){
				if(hand[i].getValue()==hand[i-1].getValue()){
					amt++;
				}
				else{
					if(amt>=3){
						return hand[i-1];
					}
					else{
						amt=1;
					}
				}
			}
			if(amt>=3)
				return hand[4];
		}
		else if (this.numPairs()==2){
			int amt=1;
			int temp=0;
			Card c = new Card();
			for(int i=1; i<hand.length; i++){
				if(hand[i].getValue()==hand[i-1].getValue()){
				 	amt++;
			   }
			   else{
			   		if(amt==2){
						if(hand[i-1].getValue()>temp || hand[i-1].getValue()==1){
							temp=hand[i-1].getValue();
							c=new Card(temp,hand[i-1].getSuit());
							if(temp==1){
								return c;
							}
						}
					}
					amt=1;
				}
			}
			if(amt==2){
				if(hand[4].getValue()>temp || hand[4].getValue()==1){
					temp=hand[4].getValue();
					c=new Card(temp,hand[4].getSuit());
					if(temp==1){
						return c;
					}
				}
			}
			return c;
		}
		else if (this.numPairs()==1){
			int amt=1;
			for(int i=1; i<hand.length; i++){
				 if(hand[i].getValue()==hand[i-1].getValue()){
					   amt++;
				 }
				 else{
			 		if(amt==2){
						return hand[i-1];
					}
					amt=1;
				}
			}
			if(amt==2){
				return hand[4];
			}
		}
		return null;
	}
/*---------------------------------------------------------------*/
	public Card greatestDuplicate(){
		this.sortByValue();
		if(this.hasFourOfAKind()){
			int amt=1;
			for(int i=1; i<hand.length; i++){
				if(hand[i].getValue()==hand[i-1].getValue()){
					amt++;
				}
				else{
					if(amt>=4){
						return hand[i-1];
					}
					else{
						amt=1;
					}
				}
			}
			if(amt>=4)
				return hand[4];
		}
		else if (this.hasTriplet()){
			int amt=1;
			for(int i=1; i<hand.length; i++){
				if(hand[i].getValue()==hand[i-1].getValue()){
					amt++;
				}
				else{
					if(amt>=3){
						return hand[i-1];
					}
					else{
						amt=1;
					}
				}
			}
			if(amt>=3)
				return hand[4];
		}
		else if (this.numPairs()==1){
			int amt=1;
			for(int i=1; i<hand.length; i++){
				 if(hand[i].getValue()==hand[i-1].getValue()){
					   amt++;
				 }
				 else{
			 		if(amt==2){
						return hand[i-1];
					}
					amt=1;
				}
			}
			if(amt==2){
				return hand[4];
			}
		}
		else if (this.numPairs()==2){
			int amt=1;
			int temp=0;
			Card c = new Card();
			for(int i=1; i<hand.length; i++){
				if(hand[i].getValue()==hand[i-1].getValue()){
				 	amt++;
			   }
			   else{
			   		if(amt==2){
						if(hand[i-1].getValue()>temp || hand[i-1].getValue()==1){
							temp=hand[i-1].getValue();
							c=new Card(temp,hand[i-1].getSuit());
							if(temp==1){
								return c;
							}
						}
					}
					amt=1;
				}
			}
			if(amt==2){
				if(hand[4].getValue()>temp || hand[4].getValue()==1){
					temp=hand[4].getValue();
					c=new Card(temp,hand[4].getSuit());
					if(temp==1){
						return c;
					}
				}
			}
			return c;
		}
		return null;
	}

/*---------------------------------------------------------------*/
	public int compareTo(Hand h){
		h.sortByValue();
		this.sortByValue();
		int large=0;
		if((h.hasFlush() && h.hasStraight())|| (this.hasFlush() && this.hasStraight())){
			if((this.hasFlush() && this.hasStraight()) && !(h.hasFlush() && h.hasStraight())){
				return 1;
			}
			else if(!(this.hasFlush() && this.hasStraight()) && (h.hasFlush() && h.hasStraight())){
				return -1;
			}
			else if((this.hasFlush() && this.hasStraight()) && (h.hasFlush() && h.hasStraight())) {
				for(int k=0; k<5; k++){
					if (h.getCard(k).getValue()==this.getCard(k).getValue()){
						large=0;
						continue;
					}
					else if(h.getCard(k).getValue()>this.getCard(k).getValue() || (h.getCard(0).getValue()==1 && h.getCard(4).getValue()==13)){
						if(this.getCard(0).getValue()==1 && this.getCard(4).getValue()==13){
							large=1;
							break;
						}
						large=-1;
						break;
					}
					else{
						large=1;
						break;
					}
				}
				return large;
			}
		}
		else if(h.hasFourOfAKind()|| this.hasFourOfAKind()){
			if(this.hasFourOfAKind() && !h.hasFourOfAKind()){
				return 1;
			}
			else if(!this.hasFourOfAKind() && h.hasFourOfAKind()){
				return -1;
			}
			else if(this.hasFourOfAKind() && h.hasFourOfAKind()){
				int i=h.greatestDuplicate().getValue();
				int j=this.greatestDuplicate().getValue();
				if((i>j && j!=1) || i==1)
					return -1;
				else
					return 1;
			}
		}
		else if(h.hasFullHouse()|| this.hasFullHouse()){
			if(!h.hasFullHouse()&& this.hasFullHouse()){
				return 1;
			}
			else if(h.hasFullHouse()&& !this.hasFullHouse()){
				return -1;
			}
			else if(h.hasFullHouse()&& this.hasFullHouse()){
				int i=h.greatestDuplicate().getValue();
				int j=this.greatestDuplicate().getValue();
				if((i>j && j!=1) || i==1)
					return -1;
				else
					return 1;
			}
		}
		else if(h.hasFlush()|| this.hasFlush()){
			if(!h.hasFlush()&& this.hasFlush()){
				return 1;
			}
			else if(h.hasFlush()&& !this.hasFlush()){
				return -1;
			}
			else if(h.hasFlush()&& this.hasFlush()){
				for(int k=0; k<5; k++){
					if (h.getCard(k).getValue()==this.getCard(k).getValue()){
						large=0;
						continue;
					}
					else if((h.getCard(k).getValue()>this.getCard(k).getValue() && this.getCard(k).getValue()!=1) || h.getCard(k).getValue()==1){
						large=-1;
						break;
					}
					else{
						large=1;
						break;
					}
				}
				return large;
			}
		}
		else if(h.hasStraight()|| this.hasStraight()){
			if(!h.hasStraight()&& this.hasStraight()){
				return 1;
			}
			else if(h.hasStraight()&& !this.hasStraight()){
				return -1;
			}
			else if(h.hasStraight()&& this.hasStraight()){
				for(int k=0; k<5; k++){
					if (h.getCard(k).getValue()==this.getCard(k).getValue()){
						large=0;
						continue;
					}
					else if(h.getCard(k).getValue()>this.getCard(k).getValue() || (h.getCard(0).getValue()==1 && h.getCard(4).getValue()==13)){
						if(this.getCard(0).getValue()==1 && this.getCard(4).getValue()==13){
							large=1;
							break;
						}
						large=-1;
						break;
					}
					else{
						large=1;
						break;
					}
				}
				return large;
			}
		}
		else if(h.hasTriplet()|| this.hasTriplet()){
			if(!h.hasTriplet()&& this.hasTriplet()){
				return 1;
			}
			else if(h.hasTriplet()&& !this.hasTriplet()){
				return -1;
			}
			else if(h.hasTriplet()&& this.hasTriplet()){
				int i=h.greatestDuplicate().getValue();
				int j=this.greatestDuplicate().getValue();
				if((i>j && j!=1) || i==1)
					return -1;
				else
					return 1;
			}
		}
		else if(h.numPairs()==2 || this.numPairs()==2){
			if(h.numPairs()!=2 && this.numPairs()==2){
				return 1;
			}
			else if(h.numPairs()==2 && this.numPairs()!=2){
				return -1;
			}
			else if(h.numPairs()==2 && this.numPairs()==2){
				int i=h.greatestDuplicate().getValue();
				int j=this.greatestDuplicate().getValue();
				if(i==j) {
					int amt=1;
					int i2=i;
					int j2=j;
					for(int t=1; t<h.getCardCount(); t++){
						   if(h.getCard(t).getValue()==h.getCard(t-1).getValue()){
						   		amt++;
						   }
						   else{
						   		if(amt==2){
						   			if(h.getCard(t-1).getValue()==i) {
						   				amt=1;
						   				continue;
						   			}
						   			else {
						   				i2=h.getCard(t-1).getValue();
						   				amt=1;
						   				break;
						   			}
						   		}
						   		amt=1;
						   }
					}
					if(amt==2) {
						i2=h.getCard(4).getValue();
					}
					for(int t=1; t<this.getCardCount(); t++){
						   if(this.getCard(t).getValue()==this.getCard(t-1).getValue()){
						   		amt++;
						   }
						   else{
						   		if(amt==2){
						   			if(this.getCard(t-1).getValue()==j) {
						   				amt=1;
						   				continue;
						   			}
						   			else {
						   				j2=this.getCard(t-1).getValue();
						   				amt=1;
						   				break;
						   			}
						   		}
						   		amt=1;
						   }
					}
					if(amt==2) {
						j2=this.getCard(4).getValue();
					}
					if(i2==j2) {
						int j3=j2;
						int i3=i2;
						for(int t=0; t<5; t++) {
							if(h.getCard(t).getValue()!=i2 && h.getCard(t).getValue()!=i) {
								i3=h.getCard(t).getValue();
								break;
							}
						}
						for(int f=0; f<5; f++) {
							if(this.getCard(f).getValue()!=j2 && this.getCard(f).getValue()!=j) {
								j3=this.getCard(f).getValue();
								break;
							}
						}
						if(i3==j3)
							return 0;
						else if((i3>j3 && j3!=1) || i3==1)
							return -1;
						else
							return 1;
					}
					else if((i2>j2 && j2!=1) || i2==1)
						return -1;
					else
						return 1;
				}
				else if((i>j && j!=1) || i==1)
					return -1;
				else
					return 1;
			}
		}
		else if(h.numPairs()==1 || this.numPairs()==1){
			if(h.numPairs()!=1 && this.numPairs()==1){
				return 1;
			}
			else if(h.numPairs()==1 && this.numPairs()!=1){
				return -1;
			}
			else if(h.numPairs()==1 && this.numPairs()==1){
				int i=h.greatestDuplicate().getValue();
				int j=this.greatestDuplicate().getValue();
				if(i==j){
					int i2=0;
					int j2=0;
					for(int t=0; t<5; t++) {
						if(h.getCard(t).getValue()>i2 && h.getCard(t).getValue()!=i) {
							i2=h.getCard(t).getValue();
							if(i2==1)
								break;
						}
					}
					for(int f=0; f<5; f++) {
						if(this.getCard(f).getValue()>j2 && this.getCard(f).getValue()!=j) {
							j2=this.getCard(f).getValue();
							if(j2==1)
								break;
						}
					}
					if(i2==j2) {
						int i3=0;
						int j3=0;
						for(int t=0; t<5; t++) {
							if(h.getCard(t).getValue()>i3 && h.getCard(t).getValue()!=i && h.getCard(t).getValue()!=i2) {
								i3=h.getCard(t).getValue();
								if(i3==1)
									break;
							}
						}
						for(int f=0; f<5; f++) {
							if(this.getCard(f).getValue()>j3 && this.getCard(f).getValue()!=j && this.getCard(f).getValue()!=j2) {
								j3=this.getCard(f).getValue();
								if(j3==1)
									break;
							}
						}
						if(i3==j3) {
							int i4=0;
							int j4=0;
							for(int t=0; t<5; t++) {
								if(h.getCard(t).getValue()>i4 && h.getCard(t).getValue()!=i && h.getCard(t).getValue()!=i2 && h.getCard(t).getValue()!=i3) {
									i4=h.getCard(t).getValue();
									if(i4==1)
										break;
								}
							}
							for(int f=0; f<5; f++) {
								if(this.getCard(f).getValue()>j4 && this.getCard(f).getValue()!=j && this.getCard(f).getValue()!=j2 && this.getCard(f).getValue()!=j3) {
									j4=this.getCard(f).getValue();
									if(j4==1)
										break;
								}
							}
							if(i4==j4)
								return 0;
							else if((i4>j4 && j4!=1) || i4==1)
								return -1;
							else
								return 1;
						}
						else if((i3>j3 && j3!=1) || i3==1)
							return -1;
						else
							return 1;
					}
					else if((i2>j2 && j2!=1) || i2==1)
						return -1;
					else
						return 1;
				}
				else if((i>j && j!=1) || i==1)
					return -1;
				else
					return 1;
			}
		}
		else{
			int i=h.highestValue().getValue();
			int j=this.highestValue().getValue();
			if(i==j){
				int i2=0;
				int j2=0;
				for(int t=0; t<5; t++) {
					if(h.getCard(t).getValue()>i2 && h.getCard(t).getValue()!=i) {
						i2=h.getCard(t).getValue();
						if(i2==1)
							break;
					}
				}
				for(int f=0; f<5; f++) {
					if(this.getCard(f).getValue()>j2 && this.getCard(f).getValue()!=j) {
						j2=this.getCard(f).getValue();
						if(j2==2)
							break;
					}
				}
				if(i2==j2) {
					int i3=0;
					int j3=0;
					for(int t=0; t<5; t++) {
						if(h.getCard(t).getValue()>i3 && h.getCard(t).getValue()!=i && h.getCard(t).getValue()!=i2) {
							i3=h.getCard(t).getValue();
							if(i3==1)
								break;
						}
					}
					for(int f=0; f<5; f++) {
						if(this.getCard(f).getValue()>j3 && this.getCard(f).getValue()!=j && this.getCard(f).getValue()!=j2) {
							j3=this.getCard(f).getValue();
							if(j3==1)
								break;
						}
					}
					if(i3==j3) {
						int i4=0;
						int j4=0;
						for(int t=0; t<5; t++) {
							if(h.getCard(t).getValue()>i4 && h.getCard(t).getValue()!=i && h.getCard(t).getValue()!=i2 && h.getCard(t).getValue()!=i3) {
								i4=h.getCard(t).getValue();
								if(i4==1)
									break;
							}
						}
						for(int f=0; f<5; f++) {
							if(this.getCard(f).getValue()>j4 && this.getCard(f).getValue()!=j && this.getCard(f).getValue()!=j2 && this.getCard(f).getValue()!=j3) {
								j4=this.getCard(f).getValue();
								if(j4==1)
									break;
							}
						}
						if(i4==j4) {
							int i5=0;
							int j5=0;
							for(int t=0; t<5; t++) {
								if(h.getCard(t).getValue()>i5 && h.getCard(t).getValue()!=i && h.getCard(t).getValue()!=i2 && h.getCard(t).getValue()!=i3 && h.getCard(t).getValue()!=i4) {
									i5=h.getCard(t).getValue();
									if(i5==1)
										break;
								}
							}
							for(int f=0; f<5; f++) {
								if(this.getCard(f).getValue()>j5 && this.getCard(f).getValue()!=j && this.getCard(f).getValue()!=j2 && this.getCard(f).getValue()!=j3 && this.getCard(f).getValue()!=j4) {
									j5=this.getCard(f).getValue();
									if(j5==1)
										break;
								}
							}
							if(i5==j5) 
								return 0;
							else if((i5>j5 && j5!=1) || i5==1)
								return -1;
							else
								return 1;
						}	
						else if((i4>j4 && j4!=1) || i4==1)
							return -1;
						else
							return 1;
					}
					else if((i3>j3 && j3!=1) || i3==1)
						return -1;
					else
						return 1;
				}
				else if((i2>j2 && j2!=1) || i2==1)
					return -1;
				else
					return 1;
			}
			else if((i>j && j!=1) || i==1)
				return -1;
			else
				return 1;
		}
		return 0;
	}
}