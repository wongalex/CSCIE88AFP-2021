package cscie88a.week3_fp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdoptionServiceTest {

	@Test
	public void testMethodReference_static(){
		AdoptionService service = new AdoptionService();
		IAdoptable lambdaMethodRef = AbstractAnimalFP::checkForAdoptionStatusStatic;
		ActionResult result = service.tryToAdopt( lambdaMethodRef );
		assertEquals(ActionResult.SUCCESS, result);
	}

	@Test
	public void testMethodReference_specific_instance(){
		AdoptionService service = new AdoptionService();
		DogFP lilo = new DogFP("Lilo");
		lilo.setHasCurrentShots(true);
		IAdoptable lambdaMethodRef = lilo::checkForAdoptionStatusInstance;

		ActionResult result = service.tryToAdopt( lambdaMethodRef );
		assertEquals(ActionResult.SUCCESS, result);

		lilo.setHasCurrentShots(false);
		result = service.tryToAdopt( lambdaMethodRef );
		assertEquals(ActionResult.FAILURE, result);
	}

	/******* following unit tests are to be implemented in the HW3 **********/

	@Test
	public void testTryToAdopt1(){
		AdoptionService service = new AdoptionService();
		ActionResult result = service.tryToAdopt(
				() -> true
		);
		assertEquals(ActionResult.SUCCESS, result);
	}

	@Test
	public void testTryToAdopt2(){
		AdoptionService service = new AdoptionService();
		ActionResult result = service.tryToAdopt2((healthCheckDone) -> healthCheckDone, true);
		assertEquals(ActionResult.SUCCESS, result);
		result = service.tryToAdopt2((healthCheckDone) -> healthCheckDone, false);
		assertEquals(ActionResult.FAILURE, result);
	}

	@Test
	public void testTryToAdopt1_namedLambda(){
		AdoptionService service = new AdoptionService();
		IAdoptable lambdaForI1 = () -> true;
		ActionResult result = service.tryToAdopt(lambdaForI1);
		assertEquals(ActionResult.SUCCESS, result);
	}

	@Test
	public void testTryToAdopt2_namedLambda(){
		AdoptionService service = new AdoptionService();
		IAdoptable2 lambdaForI2 = healthCheckDone -> healthCheckDone;
		ActionResult result = service.tryToAdopt2(lambdaForI2, true);
		assertEquals(ActionResult.SUCCESS, result);
		result = service.tryToAdopt2(lambdaForI2, false);
		assertEquals(ActionResult.FAILURE, result);
	}

}
