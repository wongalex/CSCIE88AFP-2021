package cscie88a.week3_fp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnimalManagerMethodRefTests {

	/**
	 * use static method reference as a Lambda function
	 */
	@Test
	public void testTrainToRun_static_method(){
		ITrainableFP lambdaMethodRef = AbstractAnimalFP::doTrickStatic;
		ActionResult result = AnimalManagerFP.trainToRun(lambdaMethodRef);
		assertEquals(ActionResult.FAILURE, result);
	}

	/**
	 * use instance method reference as a Lambda function
	 */
	@Test
	public void testTrainToRun_instance_method(){
		CatFP sneaky = new CatFP("Sneaky");
		sneaky.setGoodMood(true);
		ITrainableFP lambdaMethodRef = sneaky::doTrick;
		ActionResult result = AnimalManagerFP.trainToRun(lambdaMethodRef);
		assertEquals(ActionResult.SUCCESS, result);

		sneaky.setGoodMood(false);
		result = AnimalManagerFP.trainToRun(lambdaMethodRef);
		assertEquals(ActionResult.FAILURE, result);
	}

}
