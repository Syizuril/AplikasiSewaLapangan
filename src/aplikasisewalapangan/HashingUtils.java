/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasisewalapangan;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
 
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Syekh Syihabuddin AU
 */
public class HashingUtils {
    private static final String SHA_1_PRNG = "SHA1PRNG";
 
    public static String generateSaltValue() {
        String saltValue = null;
        try {
            SecureRandom secureRandom = SecureRandom.getInstance(SHA_1_PRNG);
            byte[] bytes = new byte[16];
            // secureRandom is automatically seeded by calling nextBytes
            secureRandom.nextBytes(bytes);
            saltValue = new String(Base64.encodeBase64(bytes));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA1PRNG algorithm could not be found.");
        }
       return saltValue;
    }

    /**
    * Method for hashing the password
    *
    * @param password
    * @param digsestFunction
    * @param saltValue
    * @return hash / salted hash password
    */
    public static String preparePassword(String password,String saltValue) {
        try {
            String digestInput = password;
            if (saltValue != null) {
                digestInput = password + saltValue;
            }
            MessageDigest dgst = MessageDigest.getInstance("SHA-512");
            byte[] byteValue = dgst.digest(digestInput.getBytes());
            password = new String(Base64.encodeBase64(byteValue));

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteValue.length; i++) {
              sb.append(Integer.toString((byteValue[i] & 0xff) + 0x100, 16).substring(1));
            }

            password = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Selected algorithm is not available for hashing");

        }
        return password;
    }
 
}
