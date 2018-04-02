package com.sanilk.harmonia.response_interfaces;

import com.sanilk.harmonia.networking.MyMessage;
import com.sanilk.harmonia.responses.GetYoutubeSearchResultsResponse;

/**
 * Created by sanil on 14/3/18.
 */

public interface GetYoutubeSearchResultsInterface {
    void responseReceived(GetYoutubeSearchResultsResponse response);
    void onFailure();

    enum GetYoutubeSearchResultsMessageType{
        FAILED,
        SUCCESS
    }

    class GetYoutubeSearchResultsMessage extends MyMessage {
        public GetYoutubeSearchResultsInterface.GetYoutubeSearchResultsMessageType youtubeSearchResultsMessage;
        public GetYoutubeSearchResultsMessage(){
            type= MyMessageType.YOUTUBE_SEARCH_RESULT;
        }
    }
}
