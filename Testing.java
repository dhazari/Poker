public class Testing{
	public static void main(String[]args){
		Hand hand1 = new Hand();
		Hand hand2 = new Hand();

		hand1.addCard(new Card(3,Card.HEARTS));
		hand1.addCard(new Card(2,Card.HEARTS));
		hand1.addCard(new Card(5,Card.HEARTS));
		hand1.addCard(new Card(6,Card.HEARTS));
		hand1.addCard(new Card(4,Card.HEARTS));

		hand2.addCard(new Card(5,Card.HEARTS));
		hand2.addCard(new Card(4,Card.HEARTS));
		hand2.addCard(new Card(3,Card.HEARTS));
		hand2.addCard(new Card(2,Card.HEARTS));
		hand2.addCard(new Card(1,Card.HEARTS));

		System.out.println(hand1.numPairs());
		System.out.println(hand2.numPairs());
		System.out.println(hand1.hasTriplet());
		System.out.println(hand2.hasTriplet());
		System.out.println(hand1.hasFlush());
		System.out.println(hand2.hasFlush());
		System.out.println(hand1.hasStraight());
		System.out.println(hand2.hasStraight());
		System.out.println(hand1.hasFullHouse());
		System.out.println(hand2.hasFullHouse());
		System.out.println(hand1.hasFourOfAKind());
		System.out.println(hand2.hasFourOfAKind());
		System.out.println(hand1.highestValue());
		System.out.println(hand2.highestValue());
		System.out.println(hand1.highestDuplicate());
		System.out.println(hand1.greatestDuplicate());
		System.out.println(hand2.highestDuplicate());
		System.out.println(hand2.compareTo(hand1));
	}
}