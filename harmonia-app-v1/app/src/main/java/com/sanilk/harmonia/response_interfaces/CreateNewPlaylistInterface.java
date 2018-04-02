package com.sanilk.harmonia.response_interfaces;

import com.sanilk.harmonia.networking.MyMessage;
import com.sanilk.harmonia.responses.AuthenticateResponse;
import com.sanilk.harmonia.responses.CreateNewPlaylistResponse;

/**
 * Created by sanil on 18/3/18.
 */

public interface CreateNewPlaylistInterface {
    void responseReceived(CreateNewPlaylistResponse response);
    void onFailure();

    enum CreateNewPlaylistMessageType{
        FAILED,
        SUCCESS
    }

    class CreateNewPlaylistMessage extends MyMessage {
        public CreateNewPlaylistInterface.CreateNewPlaylistMessageType createNewPlaylistMessageType;
        public CreateNewPlaylistMessage(){
            type= MyMessageType.CREATE_NEW_PLAYLIST;
        }
    }
}
