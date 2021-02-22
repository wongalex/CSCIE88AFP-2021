package cscie88a.week2;

public class Dog extends AbstractAnimal implements ITrainable{

	public Dog() {
	}

	public Dog(String name, String eyeColor, String bodyColor) {
		super(name, eyeColor, bodyColor);
	}

	@Override
	public void sayHiToHuman(String humanName) {
		System.out.println(name + " says: Hi, " + humanName + "!!! I LOVE you!");
	}

	@Override
	public ActionResult playWithMe(AbstractAnimal aFriend) {
		// I am happy to play with anyone!
		System.out.println(name + " says: I'm playing with " + aFriend.getName());
		return ActionResult.SUCCESS;
	}	
	
	@Override
	public ActionResult doTrick(String trickName) {
		System.out.println(name + " says: I LOVE doing tricks! I'm doing " + trickName + " now!");
		return ActionResult.SUCCESS;
	}

	@Override
	public ActionResult playWithToy(Toy toy) {
		System.out.println("I love the toy! Yeah!!!");
		toy.doFunStuff();
		return ActionResult.SUCCESS;
	}

}
