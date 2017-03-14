import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Anirban on 11/01/2017.
 */
public class ProbabilityEnlightenment {

    int sampleSize = 50;
    Map<Integer, Integer> counterStore = new LinkedHashMap<>();

    public static void main(String[] args) {

        Integer[] sampleSizeArr = new Integer[]{50, 100, 1000, 10000, 100000, 1000000, 10000000};

        System.out.println("sample_size,d_2,d_3,d_4,d_5,d_6,d_7,d_8,d_9");
        for (Integer sampleSize : sampleSizeArr) {
            ProbabilityEnlightenment probabilityEnlightenment = new ProbabilityEnlightenment(sampleSize);
            probabilityEnlightenment.generateMapOfRandomDivisors();
            probabilityEnlightenment.displayOutput();
            System.out.println();
        }
    }

    public ProbabilityEnlightenment(int sampleSize) {
        this.sampleSize = sampleSize;
    }

    public void generateMapOfRandomDivisors() {

        for (int counter = 0; counter < sampleSize; counter++) {
            int randomNumber = generateRandomNumber();
            for (int divisor = 2; divisor <= 100; divisor++) {
                if (randomNumber % divisor == 0) {
                    Integer value = counterStore.get(divisor);
                    if (value == null) {
                        counterStore.put(divisor, 1);
                    } else {
                        counterStore.put(divisor, ++value);
                    }
                }
            }
        }
    }

    public int generateRandomNumber() {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(1000);
    }

    public void displayOutput() {
        DecimalFormat df = new DecimalFormat("#.00");
        df.setRoundingMode(RoundingMode.CEILING);
        System.out.print(sampleSize);
        for (int i = 2; i <= 9 ; i++) {
            Integer value = counterStore.get(i) != null ? counterStore.get(i) : 0;
            double distributionPercent = ((double) value / (double) sampleSize) * 100;
            System.out.print("," + df.format(distributionPercent));
        }
    }
}