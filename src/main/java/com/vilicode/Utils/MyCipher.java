package com.vilicode.Utils;

import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
public class MyCipher {
    public MyCipher() {
    }

    private Key getKeyt(String str) {
        SecretKey keyt = null;

        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            DESKeySpec keySpec = new DESKeySpec(str.getBytes("UTF8"));
            keyFactory.generateSecret(keySpec);
            keyt=keyFactory.generateSecret(keySpec);
            //KeyGenerator generator = KeyGenerator.getInstance("DES");
            //generator.init(new SecureRandom(str.getBytes()));
            //keyt = generator.generateKey();
            //generator = null;
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return keyt;
    }

    private byte[] getEncrypt(byte[] plaintexts, String str) {
        Key key = this.getKeyt(str);
        byte[] ciphertexts = (byte[])null;

        Cipher cipher;
        try {
            cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            ciphertexts = cipher.doFinal(plaintexts);
        } catch (Exception var10) {
            var10.printStackTrace();
        } finally {
            cipher = null;
        }

        return ciphertexts;
    }

    public String encrypt(String plaintext, String str) {
        String ciphertext = "";
        BASE64Encoder base64en = new BASE64Encoder();

        try {
            byte[] plaintexts = plaintext.getBytes("UTF8");
            byte[] ciphers = this.getEncrypt(plaintexts, str);
            ciphertext = base64en.encodeBuffer(ciphers);
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return ciphertext;
    }

    private byte[] decrypt(byte[] ciphertexts, String str) {
        Key key = this.getKeyt(str);
        byte[] plaintexts = (byte[])null;

        Cipher cipher;
        try {
            cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            plaintexts = cipher.doFinal(ciphertexts);
        } catch (Exception var10) {
            System.out.println(var10.getMessage());
        } finally {
            cipher = null;
        }

        return plaintexts;
    }

    public String decrypt(String ciphertext, String str) {
        String plaintext = "";
        BASE64Decoder base64De = new BASE64Decoder();

        try {
            byte[] bytes = base64De.decodeBuffer(ciphertext);
            byte[] ciphers = this.decrypt(bytes, str);
            plaintext = new String(ciphers, "UTF8");
        } catch (Exception var7) {
            System.out.println(var7.getMessage() + " 空指针异常！");
        }

        return plaintext;
    }
}
