package cscie88a.week2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AllAnimalTests {

	private Cat sneaky;
	private Dog bolt;
	private Hedgehog sonic;
	private Toy squeakyToy;
	private Toy notSqueakyToy;

	@BeforeEach
	void setUp() throws Exception {
		sneaky = new Cat("Sneaky", "blue", "gray");
		bolt = new Dog("Bolt", "brown", "whity");
		sonic = new Hedgehog("Sonic", "black", "gray");
		squeakyToy = new Toy(true, false, false);
		notSqueakyToy = new Toy(false, false, false);

	}

	@Test
	public void testTakeMedicine() {
		assertEquals(ActionResult.SUCCESS, bolt.takeMedicine(true));
		assertEquals(ActionResult.FAILURE, bolt.takeMedicine(false));
		assertEquals(ActionResult.FAILURE, sneaky.takeMedicine(true));
		assertEquals(ActionResult.FAILURE, sneaky.takeMedicine(false));
	}

	@Test
	public void testPlayWithToy() {
		assertEquals(ActionResult.SUCCESS, sneaky.playWithToy(squeakyToy));
		assertEquals(ActionResult.FAILURE, sneaky.playWithToy(notSqueakyToy));
		assertEquals(ActionResult.SUCCESS, bolt.playWithToy(squeakyToy));
		assertEquals(ActionResult.SUCCESS, bolt.playWithToy(notSqueakyToy));
	}

	@Test
	public void testSayHi() {
		String humanName = "Marina";
		sneaky.sayHiToHuman(humanName);
		bolt.sayHiToHuman(humanName);
	}

	@Test
	public void testWhoAreYou() {
		System.out.println(sneaky.whoAreYou());
		System.out.println(bolt.whoAreYou());
	}

	@Test
	public void testAbstractClassCreation() {
		// demo error creating AbstractAnimal directly
		// will NOT compile
		//AbstractAnimal unknownAnimal = new AbstractAnimal();
	}
	
	@Test
	public void testDoTrick() {
		ActionResult result = sneaky.doTrick("sit");
		assertEquals(ActionResult.FAILURE, result);

		result = bolt.doTrick("sit");
		assertEquals(ActionResult.SUCCESS, result);
	}

	@Test
	public void testDoTrickForTreat() {
		ActionResult result = sneaky.doTrickForTreat("sit", "yummyTreat");
		assertEquals(ActionResult.FAILURE, result);

		result = bolt.doTrickForTreat("sit", "yummyTreat");
		assertEquals(ActionResult.SUCCESS, result);
	}

	@Test 
	public void testPlayWithMe() {
		// unfriendly cat will not play with anyone
		sneaky.setFriendly(false);
		ActionResult result = sneaky.playWithMe(bolt);
		assertEquals(ActionResult.FAILURE, result);
		
		// a friendly cat will play with others
		sneaky.setFriendly(true);
		result = sneaky.playWithMe(bolt);
		assertEquals(ActionResult.SUCCESS, result);
		
		// dog will play with anybody, always
		result = bolt.playWithMe(sneaky);
		assertEquals(ActionResult.SUCCESS, result);
	}

	@Test
	public void testPlayWithMeForHedgehog() {
		ActionResult result = sonic.playWithMe(bolt);
		assertEquals(ActionResult.FAILURE, result);

		result = sonic.playWithMe(sneaky);
		assertEquals(ActionResult.FAILURE, result);
	}
}
