package com.sanilk.harmonia.networking;

import com.sanilk.harmonia.responses.AuthenticateResponse;
import com.sanilk.harmonia.responses.CreateNewPlaylistResponse;
import com.sanilk.harmonia.responses.MyResponse;
import com.sanilk.harmonia.responses.SignUpResponse;
import com.sanilk.harmonia.responses.GetYoutubeSearchResultsResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sanil on 3/2/18.
 */

public class JSONParser {
    private static final String RESPONSE_TYPE_KEY="response_type";

    public MyResponse parse(String response) throws JSONException{
        JSONObject jsonObject=new JSONObject(response);
        String responseType=jsonObject.getString(RESPONSE_TYPE_KEY);
        switch (responseType){
            case SignUpResponse.RESPONSE_TYPE:
                return parseSignupResponse(jsonObject);
            case AuthenticateResponse.RESPONSE_TYPE:
                return parseAuthenticateResponse(jsonObject);
            case CreateNewPlaylistResponse.RESPONSE_TYPE:
                return parseCreateNewPlaylistResponse(jsonObject);
            default:
                return null;
        }
    }

    private CreateNewPlaylistResponse parseCreateNewPlaylistResponse(JSONObject jsonObject){
        return null;
    }

    public GetYoutubeSearchResultsResponse parseYoutubeSearchResultsResponse(JSONObject jsonObject){
        try{
            GetYoutubeSearchResultsResponse getYoutubeSearchResultsResponse
                    =new GetYoutubeSearchResultsResponse();

            JSONArray allItems=jsonObject.getJSONArray(GetYoutubeSearchResultsResponse.ITEMS_KEY);
            GetYoutubeSearchResultsResponse.Item[] item
                    =new GetYoutubeSearchResultsResponse.Item[allItems.length()];
            for(int i=0;i<allItems.length();i++){
                JSONObject itemJson=allItems.getJSONObject(i);

                JSONObject snippetJson=itemJson.getJSONObject(GetYoutubeSearchResultsResponse.Item.SNIPPET_KEY);
                String snippetTitle=snippetJson.getString(
                        GetYoutubeSearchResultsResponse.Item.Snippet.TITLE_KEY
                );
                JSONObject thumbnailJson=
                        snippetJson.getJSONObject(GetYoutubeSearchResultsResponse.Item.Snippet.THUMBNAIL_KEY)
                        .getJSONObject("default");
                GetYoutubeSearchResultsResponse.Item.Snippet.Thumbnail thumbnail
                        =new GetYoutubeSearchResultsResponse.Item.Snippet.Thumbnail(
                                thumbnailJson.getString(GetYoutubeSearchResultsResponse.Item.Snippet.Thumbnail.URL_KEY)
                );
                String channelTitle=snippetJson.getString(GetYoutubeSearchResultsResponse.Item.Snippet.CHANNEL_TITLE_KEY);
                GetYoutubeSearchResultsResponse.Item.Snippet snippet=
                        new GetYoutubeSearchResultsResponse.Item.Snippet(snippetTitle, thumbnail, channelTitle);

                GetYoutubeSearchResultsResponse.Item.Id id
                        =new GetYoutubeSearchResultsResponse.Item.Id();
                JSONObject idJson=itemJson.getJSONObject(GetYoutubeSearchResultsResponse.Item.ID_KEY);
                id.setVideoId(idJson.getString(GetYoutubeSearchResultsResponse.Item.Id.VIDEO_ID_KEY));

                GetYoutubeSearchResultsResponse.Item itemTemp=new GetYoutubeSearchResultsResponse.Item();
                itemTemp.setSnippet(snippet);
                itemTemp.setId(id);
                item[i]=itemTemp;
            }

            getYoutubeSearchResultsResponse.setItems(item);

            return getYoutubeSearchResultsResponse;
        }catch (JSONException e){
            e.printStackTrace();
        }

        return null;
    }

    private AuthenticateResponse parseAuthenticateResponse(JSONObject jsonObject) throws JSONException{
        boolean successful=jsonObject.getBoolean(AuthenticateResponse.SUCCESSFUL_KEY);
        long errorCode=jsonObject.getLong(AuthenticateResponse.ERROR_CODE_KEY);
        String errorDetails=jsonObject.getString(AuthenticateResponse.ERROR_DETAILS);
        boolean authentic=jsonObject.getBoolean(AuthenticateResponse.AUTHENTIC_KEY);

        return new AuthenticateResponse(successful, errorCode, errorDetails, authentic);
    }

    private SignUpResponse parseSignupResponse(JSONObject jsonObject) throws JSONException{
        boolean successful=jsonObject.getBoolean(SignUpResponse.SUCCESSFUL_KEY);
        long errorCode=jsonObject.getLong(SignUpResponse.ERROR_CODE_KEY);
        String errorDetails=jsonObject.getString(SignUpResponse.ERROR_DETAILS);

        return new SignUpResponse(successful, errorCode, errorDetails);
    }

}
