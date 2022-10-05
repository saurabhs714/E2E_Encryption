package com.chat.client;

import java.security.*;
import java.util.Base64 ;;
import javax.crypto.Cipher ;
import java.lang.Exception ;

public class RSAKeyUtil {

    private static RSAKeyUtil class_instance = null;

    static int RSA_KEY_LENGTH = 4096;
    static String ALGORITHM_NAME = "RSA" ;
    static String PADDING_SCHEME = "OAEPWITHSHA-512ANDMGF1PADDING" ;
    static String MODE_OF_OPERATION = "ECB" ; // This essentially means none behind the scene
    static KeyPair rsaKeyPair;

    static {
        // Generate Key Pairs
        KeyPairGenerator rsaKeyGen = null;
        try {
            rsaKeyGen = KeyPairGenerator.getInstance(ALGORITHM_NAME);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        rsaKeyGen.initialize(RSA_KEY_LENGTH) ;
        rsaKeyPair = rsaKeyGen.generateKeyPair();
    }

    public static RSAKeyUtil getInstance()
    {
        if (class_instance == null)
            class_instance = new RSAKeyUtil();

        return class_instance;
    }

    public String getRSAEncryptMsg(String message) throws Exception {
        Cipher c = Cipher.getInstance(ALGORITHM_NAME + "/" + MODE_OF_OPERATION + "/" + PADDING_SCHEME) ;
        c.init(Cipher.ENCRYPT_MODE, rsaKeyPair.getPublic()) ;
        byte[] cipherTextArray = c.doFinal(message.getBytes()) ;

        return Base64.getEncoder().encodeToString(cipherTextArray) ;
    }


    public String getRSADecryptMsg(byte[] encryptedMessage) throws Exception {
        Cipher c = Cipher.getInstance(ALGORITHM_NAME + "/" + MODE_OF_OPERATION + "/" + PADDING_SCHEME) ;
        c.init(Cipher.DECRYPT_MODE, rsaKeyPair.getPrivate());
        byte[] plainText = c.doFinal(encryptedMessage);

        return new String(plainText) ;
    }
}
