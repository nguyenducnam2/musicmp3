package vn.aptech.musicstoreapp.service_api.api;

import vn.aptech.musicstoreapp.service_api.service.AlbumService;
import vn.aptech.musicstoreapp.service_api.service.ArtistService;
import vn.aptech.musicstoreapp.service_api.service.GenreService;
import vn.aptech.musicstoreapp.service_api.service.SongService;

public class ApiUtil {
    public static final String BASE_URL = "http://172.16.0.113:8080/api/";
    public static final String WEBDATA_URL = "http://172.16.0.113:8080/webdata/";

    public static GenreService getGenreService() {
        return RetrofitClient.getClient(BASE_URL).create(GenreService.class);
    }

    public static ArtistService getArtistService() {
        return RetrofitClient.getClient(BASE_URL).create(ArtistService.class);
    }

    public static AlbumService getAlbumService() {
        return RetrofitClient.getClient(BASE_URL).create(AlbumService.class);
    }

    public static SongService getSongService() {
        return RetrofitClient.getClient(BASE_URL).create(SongService.class);
    }
}
