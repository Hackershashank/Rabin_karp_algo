import java.util.*;

public class Rabin_karp {
    static int mod = 1000000007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Taking inputs for text and pattern
        String pattern = sc.next();
        String text = sc.next();

        int n = pattern.length();
        int m = text.length();

        // Precompute powers of p
        long p = 31;
        long[] power = new long[m];
        power[0] = 1;
        for (int i = 1; i < m; i++) {
            power[i] = (power[i - 1] % mod * p % mod) % mod;
        }

        // Compute hash values for all prefixes of t
        long[] hashKeysOfPrefixOfT = new long[m + 1];
        for (int i = 0; i < m; i++) {
            hashKeysOfPrefixOfT[i + 1] = (hashKeysOfPrefixOfT[i] % mod + ((text.charAt(i) - 'a' + 1) * power[i]) % mod) % mod;
        }

        // Compute hash value for s
        long hash_pattern = 0;
        for (int i = 0; i < n; i++) {
            hash_pattern += ((pattern.charAt(i) - 'a' + 1) % mod * power[i] % mod) % mod;
        }

        // Search for the pattern in the text
        for (int i = 0; i + n - 1 < m; i++) {
            long cur_hash = (hashKeysOfPrefixOfT[i + n] - hashKeysOfPrefixOfT[i] + mod) % mod;
            if (cur_hash == (hash_pattern % mod * power[i] % mod) % mod) {
                System.out.println("Found at index: " + i);
            }
        }
    }
}
