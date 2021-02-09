package cscie88a.week3_fp;

@FunctionalInterface
public interface ITrainableFP {

	/**
	 * The only abstract method !
	 * @param trickName
	 * @return
	 */
	public ActionResult doTrick(String trickName);

	default public ActionResult doTrickForTreat(String trickName, String treatName) {
		System.out.println("I love the " + treatName + 
			" and will happily do the trick [" + trickName + "] !!");
		return ActionResult.SUCCESS;
	}
}
