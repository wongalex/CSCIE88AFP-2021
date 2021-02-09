package cscie88a.week3_fp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnimalManagerFPLabExtraTests {
	private String trickName = "sit";

	/**
	 * the regular implementation way
	 */
	@Test
	public void testTrainForTricks_concrete(){
		CatFP sneaky = new CatFP("Sneaky");
		ActionResult result = AnimalManagerFP.trainForTricks(sneaky, trickName);
		assertEquals(ActionResult.FAILURE, result);
	}

	@Test
	public void testTrainForTricks_anonymous() {
		ActionResult result = AnimalManagerFP.trainForTricks(
				new ITrainableFP() {
					public ActionResult doTrick(String trickName) {
						System.out.println("I always do tricks!");
						return ActionResult.SUCCESS;
					}
				},
				trickName);
		assertEquals(ActionResult.SUCCESS, result);
	}

	/**
	 * the simplest possible Lambda function as an implementation of the ITrainable interface
	 */
	@Test
	public void testTrainForTricks_lambda1(){
		ActionResult result = AnimalManagerFP.trainForTricks(
				trickName -> ActionResult.SUCCESS,
				trickName);
		assertEquals(ActionResult.SUCCESS, result);
	}

	/**
	 * example of a multi-line Lambda function as an implementation of the ITrainable interface
	 */
	@Test
	public void testTrainForTricks_lambda2(){
		ActionResult result = AnimalManagerFP.trainForTricks(
				trickName -> {
						System.out.println("I always do tricks - even in Lambda!");
						return ActionResult.SUCCESS;
				},
				trickName);
		assertEquals(ActionResult.SUCCESS, result);
	}

	/**
	 * example of a more complex Lambda function as an implementation of the ITrainable interface
	 *
	 * Usually you would not do this via Lambdas in this case, as you may want to re-use the logic
	 * multiple times
	 */
	@Test
	public void testTrainForTricks_lambda3(){
		String trickName1 = "jump";
		ActionResult result = AnimalManagerFP.trainForTricks(
				trickName -> {
					if (trickName == null) {
						return ActionResult.FAILURE;
					};
					if (trickName.equalsIgnoreCase("jump"))	{
						System.out.println("I love jumping - I will do it!");
						return ActionResult.SUCCESS;
					} else {
						return ActionResult.FAILURE;
					}
				},
				trickName1);
		assertEquals(ActionResult.SUCCESS, result);
	}

	/**
	 * example of a named reusable Lambda function as an implementation of the ITrainable interface
	 *
	 */
	@Test
	public void testTrainForTricks_lambda4(){
		ITrainableFP namedLamdaOfITrainable = trickName -> {
			if (trickName == null) {
				return ActionResult.FAILURE;
			};
			if (trickName.equalsIgnoreCase("jump"))	{
				System.out.println("I love jumping - I will do it!");
				return ActionResult.SUCCESS;
			} else {
				return ActionResult.FAILURE;
			}
		};

		String trickName1 = "jump";
		ActionResult result = AnimalManagerFP.trainForTricks(
				namedLamdaOfITrainable,
				trickName1);
		assertEquals(ActionResult.SUCCESS, result);

		String trickName2 = "sit";
		result = AnimalManagerFP.trainForTricks(
				namedLamdaOfITrainable,
				trickName2);
		assertEquals(ActionResult.FAILURE, result);
	}

	/**
	 * Example of an anonymous class that overrides more than one method
	 * Lambdas can NOT do that
	 */
	@Test
	public void testTrainForTricks_anonymous_multi_method() {
		ActionResult result = AnimalManagerFP.trainForTricks(
				new ITrainableFP() {
					public ActionResult doTrick(String trickName) {
						System.out.println("I always do tricks!");
						return ActionResult.SUCCESS;
					};

					public String toString() {
						return "Mysterious Animal";
					}
				},
				trickName);
		assertEquals(ActionResult.SUCCESS, result);
	}

}
