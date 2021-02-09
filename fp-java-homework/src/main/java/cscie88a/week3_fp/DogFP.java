package cscie88a.week3_fp;

public class DogFP extends AbstractAnimalFP implements ITrainableFP{

    // dogs are always of type DOG
    public DogFP(String name){
        super(AnimalType.DOG, name);
    }
    // we will leave all default functionality as is

    @Override
    public ActionResult doTrick(String trickName) {
        System.out.println(getName() + " says: I LOVE doing tricks! I'm doing " + trickName + " now!");
        return ActionResult.SUCCESS;
    }

}
