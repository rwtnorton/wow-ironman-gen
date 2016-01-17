package wow_ironman_gen;

public class ModHelper {
    // Keep all remainders non-negative.
    public static int mod(int n, int modulus) {
        int result = n % modulus;
        if (result < 0) {
            result += modulus;
        }
        return result;
    }
}
