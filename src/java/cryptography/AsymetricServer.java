/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cryptography;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author imape
 */
public class AsymetricServer {

    private static final Logger LOGGER = Logger.getLogger(AsymetricServer.class.getName());
    
    public static String decryptData(String password) {
        LOGGER.info("AsymetricServer: Decrypting data");
        byte[] decryptedData = null;
        String passwordReceived = null;
        try {
            // Load Private Key
            InputStream fis = AsymetricServer.class.getResourceAsStream("privateKey.der");
            byte[] privateKeyBytes = new byte[fis.available()];
            fis.read(privateKeyBytes);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

            // Convert hex string to bytes
            byte[] encryptedData = dehexadecimal(password);

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            decryptedData = cipher.doFinal(encryptedData);
            passwordReceived = new String(decryptedData);
            System.out.println("Received from client: " + new String(decryptedData));

        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return passwordReceived;
    }

    public static String hashText(String text) {
        LOGGER.info("AsymetricServer: Hashing text");
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = sha256.digest(text.getBytes());

            // Convert the byte array to a hexadecimal representation
            StringBuilder hexStringBuilder = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = String.format("%02X", b);
                hexStringBuilder.append(hex);
            }

            return hexStringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.severe("AsymetricServer: Exception during hashing text: "+e.getMessage());
            return null;
        }
    }

    public static byte[] fileReader(String path) {
        LOGGER.info("AsymetricServer: Reading data");
        byte ret[] = null;
        File file = new File(path);
        try {
            ret = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            LOGGER.severe("AsymetricServer: Exception during reading data: "+e.getMessage());
        }
        return ret;
    }

    public static byte[] dehexadecimal(String hexString) {
        LOGGER.info("AsymetricServer: Undoing hexadecimal");
        int len = hexString.length();
        byte[] result = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            result[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return result;
    }
}
