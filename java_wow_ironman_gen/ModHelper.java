package java_wow_ironman_gen;

public class ModHelper {
    // Keep all remainders positive.
    public static int mod(int n, int modulus) {
        int result = n % modulus;
        if (result < 0) {
            result += modulus;
        }
        return result;
    }
}
