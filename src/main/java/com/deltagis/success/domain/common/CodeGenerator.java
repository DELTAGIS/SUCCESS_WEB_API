package com.deltagis.success.domain.common;


import java.security.SecureRandom;
import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

public class CodeGenerator {
    public static final String letters = "abcdefghijklmnopqrstuvwxyz"
            + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static final String ALLOWED_CHARS = "0123456789" + letters;

    public static final int NUMBER_OF_CODEPOINTS = ALLOWED_CHARS.length();

    public static final int CODESIZE = 11;

    private static final Pattern CODE_PATTERN = Pattern.compile("^[a-zA-Z]{1}[a-zA-Z0-9]{10}$");

    /**
     * 192 bit, must be dividable by 3 to avoid padding "=".
     */
    private static final int URL_RANDOM_TOKEN_LENGTH = 24;

    /**
     * Generates a UID according to the following rules:
     * <ul>
     * <li>Alphanumeric characters only.</li>
     * <li>Exactly 11 characters long.</li>
     * <li>First character is alphabetic.</li>
     * </ul>
     *
     * @return a UID.
     */
    public static String generateUid() {
        return generateCode(CODESIZE);
    }

    /**
     * Generates a pseudo random string with alphanumeric characters.
     *
     * @param codeSize the number of characters in the code.
     * @return the code.
     */
    public static String generateCode(int codeSize) {
        ThreadLocalRandom r = ThreadLocalRandom.current();

        char[] randomChars = new char[codeSize];

        // First char should be a letter
        randomChars[0] = letters.charAt(r.nextInt(letters.length()));

        for (int i = 1; i < codeSize; ++i) {
            randomChars[i] = ALLOWED_CHARS.charAt(r.nextInt(NUMBER_OF_CODEPOINTS));
        }

        return new String(randomChars);
    }

    /**
     * Generates a cryptographically strong random token encoded in Base64
     *
     * @param lengthInBytes length in bytes of the token
     * @return a Base64 encoded string of the token
     */
    public static String getRandomSecureToken(int lengthInBytes) {
        SecureRandom sr = new SecureRandom();
        byte[] tokenBytes = new byte[lengthInBytes];
        sr.nextBytes(tokenBytes);

        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        return encoder.encodeToString(tokenBytes);
    }

    /**
     * Tests whether the given code is a valid UID.
     *
     * @param code the code to validate.
     * @return true if the code is valid.
     */
    public static boolean isValidUid(String code) {
        return code != null && CODE_PATTERN.matcher(code).matches();
    }

    /**
     * Generates a random 32 character token to be used in URLs.
     *
     * @return a token.
     */
    public static String getRandomUrlToken() {
        SecureRandom sr = new SecureRandom();
        byte[] tokenBytes = new byte[URL_RANDOM_TOKEN_LENGTH];
        sr.nextBytes(tokenBytes);

        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }
}
