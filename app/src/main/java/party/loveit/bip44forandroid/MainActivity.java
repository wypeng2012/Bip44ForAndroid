package party.loveit.bip44forandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import party.loveit.bip44forandroidlibrary.utils.Bip44Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    //get 12 words
                    List<String> words = Bip44Utils.generateMnemonicWords(MainActivity.this);
                    Log.e("TAG", "words: " + words.toString());

                    // get bip39 seed
                    byte[] seed = Bip44Utils.getSeed(words);
                    Log.e("TAG", "seed: " + new BigInteger(1,seed).toString(16));

                    //get PrivateKey by path
                    BigInteger pri1 = Bip44Utils.getPathPrivateKey(words,"m/44'/194'/0'/0/0");
                    Log.e("TAG", "pri1: " + pri1.toString(16));

                    BigInteger pri2 = Bip44Utils.getPathPrivateKey(words,seed,"m/44'/194'/0'/0/0");
                    Log.e("TAG", "pri2: " + pri2.toString(16));

                    byte[] pri3 = Bip44Utils.getPathPrivateKeyBytes(words, "m/44'/194'/0'/0/0");
                    Log.e("TAG", "pri3: " + new BigInteger(1,pri3).toString(16));

                    byte[] pri4 = Bip44Utils.getPathPrivateKeyBytes(words, seed,"m/44'/194'/0'/0/0");
                    Log.e("TAG", "pri4: " + new BigInteger(1,pri4).toString(16));

                    byte[] pri5 = Bip44Utils.getDefaultPathPrivateKeyBytes(words, 194);
                    Log.e("TAG", "pri5: " + new BigInteger(1,pri5).toString(16));



                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("TAG", "Exception: " + e);
                }

            }
        }).start();
    }
}
