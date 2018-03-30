package com.pgzxc.encryption;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.pgzxc.encryption.aes.AESUtils;
import com.pgzxc.encryption.base64.Base64Utils;
import com.pgzxc.encryption.bean.Person;
import com.pgzxc.encryption.des.DESUtils;
import com.pgzxc.encryption.rsa.RSAUtils;
import com.pgzxc.encryption.utils.Base64Decoder;
import com.pgzxc.encryption.utils.Base64Encoder;
import com.pgzxc.encryption.utils.JsonUtils;
import com.pgzxc.encryption.xor.XORUtils;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_rsa).setOnClickListener(view->{
            RSA();
        });
        findViewById(R.id.btn_aes).setOnClickListener(view->{
            AES();
        });
        findViewById(R.id.btn_des).setOnClickListener(view->{
            DES();
        });
        findViewById(R.id.btn_base64).setOnClickListener(view->{
            Base64();
        });
        findViewById(R.id.btn_xor).setOnClickListener(view->{
            XOR();
        });
    }

    private void XOR() {
        byte[] bytes = XORUtils.encrypt("加密1".getBytes());//加密
        String str1 = new String(XORUtils.encrypt(bytes));//解密
        Log.e("MainActivity", "XOR: "+str1);

        byte[] bytesKeys = XORUtils.encryptkey("加密2".getBytes());//加密
        String strKey = new String(XORUtils.decrypt(bytesKeys));//解密
        Log.e("MainActivity", "XORKeys: "+strKey);

    }

    private void Base64() {
        List<Person> personList = new ArrayList<>();
        int testMaxCount = 1000;//测试的最大数据条数
        //添加测试数据
        for (int i = 0; i < testMaxCount; i++) {
            Person person = new Person();
            person.setAge(i);
            person.setName(String.valueOf(i));
            personList.add(person);
        }
        //FastJson生成json数据
        String jsonData = JsonUtils.objectToJsonForFastJson(personList);
        Log.e("MainActivity", "Base64加密前json数据 ---->" + jsonData);
        Log.e("MainActivity", "Base64加密前json数据长度 ---->" + jsonData.length());


        //Base64加密
        long start = System.currentTimeMillis();
        String encryStr = Base64Utils.encode(jsonData);
        long end = System.currentTimeMillis();
        Log.e("MainActivity", "Base64加密耗时 cost time---->" + (end - start));
        Log.e("MainActivity", "Base64加密后json数据 ---->" + encryStr);
        Log.e("MainActivity", "Base64加密后json数据长度 ---->" + encryStr.length());

        //Base64解密
        start = System.currentTimeMillis();
        String decryStr = Base64Utils.decode( encryStr);
        end = System.currentTimeMillis();
        Log.e("MainActivity", "Base64解密耗时 cost time---->" + (end - start));
        Log.e("MainActivity", "Base64解密后json数据 ---->" + decryStr);
    }

    private void DES() {
        List<Person> personList = new ArrayList<>();
        int testMaxCount = 1000;//测试的最大数据条数
        //添加测试数据
        for (int i = 0; i < testMaxCount; i++) {
            Person person = new Person();
            person.setAge(i);
            person.setName(String.valueOf(i));
            personList.add(person);
        }
        //FastJson生成json数据
        String jsonData = JsonUtils.objectToJsonForFastJson(personList);
        Log.e("MainActivity", "DES加密前json数据 ---->" + jsonData);
        Log.e("MainActivity", "DES加密前json数据长度 ---->" + jsonData.length());

        //生成一个动态key
        String secretKey = DESUtils.generateKey();
        Log.e("MainActivity", "DES动态secretKey ---->" + secretKey);

        //DES加密
        long start = System.currentTimeMillis();
        String encryStr = DESUtils.encode(secretKey, jsonData);
        long end = System.currentTimeMillis();
        Log.e("MainActivity", "DES加密耗时 cost time---->" + (end - start));
        Log.e("MainActivity", "DES加密后json数据 ---->" + encryStr);
        Log.e("MainActivity", "DES加密后json数据长度 ---->" + encryStr.length());

        //DES解密
        start = System.currentTimeMillis();
        String decryStr = DESUtils.decode(secretKey, encryStr);
        end = System.currentTimeMillis();
        Log.e("MainActivity", "DES解密耗时 cost time---->" + (end - start));
        Log.e("MainActivity", "DES解密后json数据 ---->" + decryStr);
    }

    private void AES() {
        List<Person> personList = new ArrayList<>();
        int testMaxCount = 1000;//测试的最大数据条数
        //添加测试数据
        for (int i = 0; i < testMaxCount; i++) {
            Person person = new Person();
            person.setAge(i);
            person.setName(String.valueOf(i));
            personList.add(person);
        }
        //FastJson生成json数据
        String jsonData = JsonUtils.objectToJsonForFastJson(personList);
        Log.e("MainActivity", "AES加密前json数据 ---->" + jsonData);
        Log.e("MainActivity", "AES加密前json数据长度 ---->" + jsonData.length());

        //生成一个动态key
        String secretKey = AESUtils.generateKey();
        Log.e("MainActivity", "AES动态secretKey ---->" + secretKey);

        //AES加密
        long start = System.currentTimeMillis();
        String encryStr = AESUtils.encrypt(secretKey, jsonData);
        long end = System.currentTimeMillis();
        Log.e("MainActivity", "AES加密耗时 cost time---->" + (end - start));
        Log.e("MainActivity", "AES加密后json数据 ---->" + encryStr);
        Log.e("MainActivity", "AES加密后json数据长度 ---->" + encryStr.length());

        //AES解密
        start = System.currentTimeMillis();
        String decryStr = AESUtils.decrypt(secretKey, encryStr);
        end = System.currentTimeMillis();
        Log.e("MainActivity", "AES解密耗时 cost time---->" + (end - start));
        Log.e("MainActivity", "AES解密后json数据 ---->" + decryStr);
    }

    /**
     * RSA算法
     */
    private void RSA() {
        List<Person> personList=new ArrayList<>();
        int testMaxCount=100;//测试的最大数据条数
        //添加测试数据
        for(int i=0;i<testMaxCount;i++){
            Person person =new Person();
            person.setAge(i);
            person.setName(String.valueOf(i));
            personList.add(person);
        }
        //FastJson生成json数据
        String jsonData= JsonUtils.objectToJsonForFastJson(personList);
        Log.e("MainActivity","加密前json数据 ---->"+jsonData);
        Log.e("MainActivity","加密前json数据长度 ---->"+jsonData.length());


        KeyPair keyPair= RSAUtils.generateRSAKeyPair(RSAUtils.DEFAULT_KEY_SIZE);
        // 公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        // 私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();


        try {
            //公钥加密
            long start=System.currentTimeMillis();
            byte[] encryptBytes=    RSAUtils.encryptByPublicKeyForSpilt(jsonData.getBytes(),publicKey.getEncoded());
            long end=System.currentTimeMillis();
            Log.e("MainActivity","公钥加密耗时 cost time---->"+(end-start));
            String encryStr= Base64Encoder.encode(encryptBytes);
            Log.e("MainActivity","加密后json数据 --1-->"+encryStr);
            Log.e("MainActivity","加密后json数据长度 --1-->"+encryStr.length());
            //私钥解密
            start=System.currentTimeMillis();
            byte[] decryptBytes=  RSAUtils.decryptByPrivateKeyForSpilt(Base64Decoder.decodeToBytes(encryStr),privateKey.getEncoded());
            String decryStr=new String(decryptBytes);
            end=System.currentTimeMillis();
            Log.e("MainActivity","私钥解密耗时 cost time---->"+(end-start));
            Log.e("MainActivity","解密后json数据 --1-->"+decryStr);

            //私钥加密
            start=System.currentTimeMillis();
            encryptBytes=    RSAUtils.encryptByPrivateKeyForSpilt(jsonData.getBytes(),privateKey.getEncoded());
            end=System.currentTimeMillis();
            Log.e("MainActivity","私钥加密密耗时 cost time---->"+(end-start));
            encryStr=Base64Encoder.encode(encryptBytes);
            Log.e("MainActivity","加密后json数据 --2-->"+encryStr);
            Log.e("MainActivity","加密后json数据长度 --2-->"+encryStr.length());
            //公钥解密
            start=System.currentTimeMillis();
            decryptBytes=  RSAUtils.decryptByPublicKeyForSpilt(Base64Decoder.decodeToBytes(encryStr),publicKey.getEncoded());
            decryStr=new String(decryptBytes);
            end=System.currentTimeMillis();
            Log.e("MainActivity","公钥解密耗时 cost time---->"+(end-start));
            Log.e("MainActivity","解密后json数据 --2-->"+decryStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
