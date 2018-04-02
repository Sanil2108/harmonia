package com.sanilk.harmonia;

/**
 * Created by sanil on 16/2/18.
 */

public class MyAssetManager {
    private static MyAssetManager myAssetManager;

    private MyAssetManager(){

    }

    public static MyAssetManager getMyAssetManager() {
        return myAssetManager;
    }
}
