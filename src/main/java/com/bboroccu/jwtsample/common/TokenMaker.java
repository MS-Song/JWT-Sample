package com.bboroccu.jwtsample.common;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

public class TokenMaker {
    public String KeyMake(){
        // 64byte 의 랜럼 수치를 저장
        String Key = "";
        while(true) {
            byte[] bytes = new byte[16];
            SecureRandom random = new SecureRandom();
            random.nextBytes(bytes);

            try {
                //System.out.println(bytes);
                //System.out.println(new String(Base64.encodeBase64(bytes, false), "UTF-8"));
                Key = new String(Base64.encodeBase64(bytes, false), "UTF-8").replace("==", "");
            } catch (Exception e) {

            }
            if(Key.matches("^[a-zA-Z0-9]*$"))
            {
                //System.out.println("Find = " + Key);
                break;
            }
        }
        return Key;
    }
    public String[] randomPassWordMake() {
        String password = KeyMake().substring(0, 10);
        return new String[]{password, encryptPassword(password)};
    }
    private String encryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
