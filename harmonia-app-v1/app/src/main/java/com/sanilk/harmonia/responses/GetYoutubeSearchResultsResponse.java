package com.sanilk.harmonia.responses;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.net.URL;

/**
 * Created by sanil on 14/3/18.
 */

public class GetYoutubeSearchResultsResponse extends MyResponse {
    public static final String ITEMS_KEY="items";

    public Item[] items;

    public GetYoutubeSearchResultsResponse(){}

    public GetYoutubeSearchResultsResponse(Item[] items) {
        this.items = items;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public static class Item{
        public static final String ID_KEY="id";
        public static final String SNIPPET_KEY="snippet";

        public Id id;
        public Snippet snippet;

        public Item(){}

        public Item(Id id, Snippet snippet) {
            this.id = id;
            this.snippet = snippet;
        }

        public Id getId() {
            return id;
        }

        public void setId(Id id) {
            this.id = id;
        }

        public Snippet getSnippet() {
            return snippet;
        }

        public void setSnippet(Snippet snippet) {
            this.snippet = snippet;
        }

        public static class Id{
            public static final String VIDEO_ID_KEY="videoId";

            public String videoId;

            public Id(){}

            public Id(String videoId) {
                this.videoId = videoId;
            }

            public String getVideoId() {
                return videoId;
            }

            public void setVideoId(String videoId) {
                this.videoId = videoId;
            }
        }

        public static class Snippet{
            public static final String TITLE_KEY="title";
            public static final String THUMBNAIL_KEY="thumbnails";
            public static final String CHANNEL_TITLE_KEY="channelTitle";

            public String title;
            public Thumbnail thumbnail;
            public String channelTitle;

            public Bitmap bitmap;

            public void setBitmap() {
                try {
                    URL url = new URL(thumbnail.url);
                    bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                }catch (Exception e){e.printStackTrace();}
            }

            public Snippet(){}

            public Snippet(String title, Thumbnail thumbnail, String channelTitleKey) {
                this.title = title;
                this.thumbnail = thumbnail;
                this.channelTitle = channelTitleKey;
            }

            public String getChannelTitle() {
                return channelTitle;
            }

            public void setChannelTitle(String channelTitle) {
                this.channelTitle = channelTitle;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public Thumbnail getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(Thumbnail thumbnail) {
                this.thumbnail = thumbnail;
            }

            public static class Thumbnail{
                public static final String URL_KEY="url";

                public String url;

                public Thumbnail(){}

                public Thumbnail(String url) {
                    this.url = url;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }
    }
}
