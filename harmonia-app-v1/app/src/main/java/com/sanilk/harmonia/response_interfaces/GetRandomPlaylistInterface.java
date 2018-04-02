package com.sanilk.harmonia.response_interfaces;

import com.sanilk.harmonia.networking.MyMessage;
import com.sanilk.harmonia.responses.GetRandomPlaylistResponse;

/**
 * Created by sanil on 17/2/18.
 */

public interface GetRandomPlaylistInterface {
    void responseReceived(GetRandomPlaylistResponse response);
    void onFailure();

    enum GetRandomPlaylistMessageType{
        FAILED,
        SUCCESS
    }

    class RandomPlaylistMessage extends MyMessage {
        public RandomPlaylistMessage randomPlaylistMessage;
        public boolean isAuthentic;
        public RandomPlaylistMessage(){
            type= MyMessageType.RANDOM_PLAYLIST;
        }
    }
}
