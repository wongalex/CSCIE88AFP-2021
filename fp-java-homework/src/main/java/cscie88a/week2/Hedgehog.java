package cscie88a.week2;

public class Hedgehog extends AbstractAnimal implements ITrainable {

    public Hedgehog(){}

    public Hedgehog(String name, String eyeColor, String bodyColor) {
        super(name, eyeColor, bodyColor);
    }

    @Override
    public void sayHiToHuman(String humanName) {
        System.out.println("Hello " + humanName + "! Be careful with me I'm spiky!");
    }

    @Override
    public ActionResult playWithMe(AbstractAnimal aFriend) {
        System.out.println("I love making friends, but I can't play with " + aFriend.name + " because of my spikes...");
        return ActionResult.FAILURE;
    }

    @Override
    public ActionResult playWithToy(Toy toy) {
        System.out.println("Yay a toy! I can play with this without hurting it!");
        return ActionResult.SUCCESS;
    }

    @Override
    public ActionResult doTrick(String trickName) {
        System.out.println("I wish I can do " + trickName + " but I am too sleepy right now...");
        return ActionResult.FAILURE;
    }
}
