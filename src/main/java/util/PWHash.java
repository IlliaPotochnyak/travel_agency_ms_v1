package util;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PWHash {
    private static final String salt = "1234";
    private static final int iterations = 10;
    private static final int keyLength = 64;
    private char[] passwordChars;
    private static final byte[] saltBytes = salt.getBytes();

    public static String hashPassword( final String password ) {

        try {
            char[] passwordChars = password.toCharArray();
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
            PBEKeySpec spec = new PBEKeySpec( passwordChars, saltBytes, iterations, keyLength );
            SecretKey key = skf.generateSecret( spec );
            byte[] hashedBytes = key.getEncoded( );
            String hashedString = Hex.encodeHexString(hashedBytes);
            System.out.println(hashedString);
            return hashedString;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException( e );
        }
    }
}
