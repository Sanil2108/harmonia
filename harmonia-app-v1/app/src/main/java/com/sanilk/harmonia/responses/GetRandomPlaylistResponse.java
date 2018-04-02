package com.sanilk.harmonia.responses;

import java.util.Arrays;

/**
 * Created by sanil on 17/2/18.
 */

public class GetRandomPlaylistResponse extends MyResponse {

    public static final String RESPONSE_TYPE="GET_RANDOM_PLAYLIST_RESPONSE";
    public static final String UPVOTES_KEY="upvotes";
    public static final String DOWNVOTES_KEY="downvotes";
    public static final String USERNAME_KEY="[username]";
    public static final String NAME_KEY="name";
    public static final String SONGS_COUNT_KEY="songs_count";
    public static final String SONGS_KEY="songs";

    private int upvotes;
    private int downvotes;
    private String username;
    private String name;
    private int songsCount;
    private Song[] songs;

    public GetRandomPlaylistResponse(int upvotes, int downvotes, String username, String name, int songsCount, Song[] songs) {
        this.upvotes = upvotes;
        this.downvotes = downvotes;
        this.username = username;
        this.name = name;
        this.songsCount = songsCount;
        this.songs = songs;

        this.responseType=RESPONSE_TYPE;
    }

    public static class Song {
        public static final String LINK_KEY="link";
        public static final String NAME_KEY="name";
        public static final String ARTIST_KEY="artist";
        public static final String GENRES_COUNT_KEY="genres_count";
        public static final String GENRES_KEY="genres";

        private String link;
        private String name;
        private String artist;
        private int genresCount;
        private Genre[] genres;

        public Song(String link, String name, String artist, int genresCount, Genre[] genres) {
            this.link = link;
            this.name = name;
            this.artist = artist;
            this.genresCount = genresCount;
            this.genres = genres;
        }

        public static class Genre{
            public static final String NAME_KEY="name";

            private String name;

            public Genre(String name) {
                this.name = name;
            }

            @Override
            public String toString() {
                return "Genre{" +
                        "name='" + name + '\'' +
                        '}';
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        @Override
        public String toString() {
            return "Song{" +
                    "link='" + link + '\'' +
                    ", name='" + name + '\'' +
                    ", artist='" + artist + '\'' +
                    ", genresCount=" + genresCount +
                    ", genres=" + Arrays.toString(genres) +
                    '}';
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public int getGenresCount() {
            return genresCount;
        }

        public void setGenresCount(int genresCount) {
            this.genresCount = genresCount;
        }

        public Genre[] getGenres() {
            return genres;
        }

        public void setGenres(Genre[] genres) {
            this.genres = genres;
        }
    }

    @Override
    public String toString() {
        return "GetRandomPlaylistResponse{" +
                "upvotes=" + upvotes +
                ", downvotes=" + downvotes +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", songsCount=" + songsCount +
                ", songs=" + Arrays.toString(songs) +
                '}';
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSongsCount() {
        return songsCount;
    }

    public void setSongsCount(int songsCount) {
        this.songsCount = songsCount;
    }

    public Song[] getSongs() {
        return songs;
    }

    public void setSongs(Song[] songs) {
        this.songs = songs;
    }
}
