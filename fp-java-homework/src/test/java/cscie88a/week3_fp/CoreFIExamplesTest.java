package cscie88a.week3_fp;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class CoreFIExamplesTest {

    @Test
    public void testCalculations() {
        // can be implemented as any FI - via anonymous class
        Function<String, Integer> calcFnSafe = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                if (s != null)
                    return s.length();
                else
                    return 0;
            }
        };

        Function<String, Integer> calcFn1 = inputString -> inputString.length();
        Function<String, Integer> calcFn_plus10 = inputString -> inputString.length() + 10;
        Function<String, Integer> calcFn_double = inputString -> inputString.length() * 2;

        String inputString = "class";
        CoreFIExamples.calculateLength(calcFn1, inputString);
        CoreFIExamples.calculateLength(calcFn_plus10, inputString);
        CoreFIExamples.calculateLength(calcFn_double, inputString);

        // we could also provide an in-line lambda as implementation:
        CoreFIExamples.calculateLength(
                inputStringArg -> inputStringArg.length() + 100,
                inputString);
    }

    @Test
    public void testUseFunctionToTrain() {
        // we can create a concrete instance as an implementation of the ITrainableFP interface:
        CatFP sneaky = new CatFP("Sneaky");
        sneaky.setGoodMood(true);

        // implementation of the Function<> interface - as an anonymous class
        // and use external state for trick name
        String currentTrickName = "jump";
        Function<ITrainableFP, ActionResult> myTrainingFunction1 = new Function<ITrainableFP, ActionResult>() {
            @Override
            public ActionResult apply(ITrainableFP trainingSubject) {
                return trainingSubject.doTrick(currentTrickName);
            }
        };

        // implementation of the Function<> interface - as a lambda function
        Function<ITrainableFP, ActionResult> myTrainingFunction2 = trainingSubject -> {
            return trainingSubject.doTrick("some trick");
        };

        // implementation of the Function<> interface - as a yet another lambda function
        // always returns FAILURE
        Function<ITrainableFP, ActionResult> myTrainingFunction3 = trainingSubject -> {
            return ActionResult.FAILURE;
        };

        // now we can see how we can use different training functions in the same
        // service method:
        ActionResult result1 = CoreFIExamples.useFunctionToTrain(myTrainingFunction1, sneaky);
        // Sneaky is in a good mood - training should succeed
        assertEquals(ActionResult.SUCCESS, result1);
        ActionResult result2 = CoreFIExamples.useFunctionToTrain(myTrainingFunction2, sneaky);
        assertEquals(ActionResult.SUCCESS, result2);
        ActionResult result3 = CoreFIExamples.useFunctionToTrain(myTrainingFunction3, sneaky);
        assertEquals(ActionResult.FAILURE, result3);

    }

    @Test
    public void testSupplierAndConsumer_anonymous() {
        Supplier<AbstractAnimalFP> catSupplier = new Supplier<AbstractAnimalFP>() {
            @Override
            public AbstractAnimalFP get() {
                CatFP newCat = new CatFP("SuppliedCat");
                newCat.setHasCurrentShots(true);
                System.out.println("I've supplied a new cat: " + newCat);
                return newCat;
            }
        };

        Consumer<AbstractAnimalFP> animalConsumer = new Consumer<AbstractAnimalFP>() {
            @Override
            public void accept(AbstractAnimalFP abstractAnimalFP) {
                System.out.println("I'm accepting this animal: " + abstractAnimalFP);
            }
        };

        CoreFIExamples.chainSupplierAndConsumer(catSupplier, animalConsumer);
    }

    @Test
    public void testSupplierAndConsumer() {
        Random random = new Random();
        Supplier<AbstractAnimalFP> catSupplier = () -> {
            boolean hasCurrentShots = random.nextBoolean();
            CatFP newCat = new CatFP("SuppliedCat");
            newCat.setHasCurrentShots(hasCurrentShots);
            return newCat;
        };

        Consumer<AbstractAnimalFP> animalConsumer = animal -> {
            if (animal.isHasCurrentShots()) {
                System.out.println("We can accept this animal");
            } else {
                System.out.println("We can NOT accept this animal");
            }
        };

        CoreFIExamples.chainSupplierAndConsumer(catSupplier, animalConsumer);
    }

    /******* the following unit test is to be implemented in the HW3 **********/
    @Test
    public void testSupplierAndConsumerForAdoption() {
        Random random = new Random();
        Supplier<AbstractAnimalFP> catAndDogSupplier = () -> {
            boolean isCat = random.nextBoolean();
            if (isCat) {
                CatFP newCat = new CatFP("SupplierCat");
                newCat.setGoodMood(random.nextBoolean());
                return newCat;
            } else {
                DogFP newDog = new DogFP("SupplierDog");
                return newDog;
            }
        };

        Consumer<AbstractAnimalFP> consumerForTraining = animal -> {
            ActionResult result = animal.doTrick("any trick");
            if (result == ActionResult.SUCCESS) {
                System.out.println("I will accept this animal for training");
            } else {
                System.out.println("I will NOT accept this animal for trianing");
            }
        };

        CoreFIExamples.chainSupplierAndConsumer(catAndDogSupplier, consumerForTraining);
    }
}