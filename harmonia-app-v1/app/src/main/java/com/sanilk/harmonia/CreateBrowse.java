package com.sanilk.harmonia;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sanilk.harmonia.networking.NetworkHandler;
import com.sanilk.harmonia.response_interfaces.GetRandomPlaylistInterface;
import com.sanilk.harmonia.responses.GetRandomPlaylistResponse;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateBrowse extends Fragment {


    public CreateBrowse() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_create_browse, container, false);

        NetworkHandler networkHandler=NetworkHandler.getNetworkHandler();
        networkHandler.startGetrandomPlaylistThread("GRPT1", new GetRandomPlaylistInterface() {
            @Override
            public void responseReceived(GetRandomPlaylistResponse response) {

            }

            @Override
            public void onFailure() {

            }
        });

        return v;
    }

}
