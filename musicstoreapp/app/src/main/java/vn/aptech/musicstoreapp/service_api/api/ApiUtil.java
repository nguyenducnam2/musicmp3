package vn.aptech.musicstoreapp.service_api.api;

import vn.aptech.musicstoreapp.service_api.service.ArtistService;
import vn.aptech.musicstoreapp.service_api.service.GenreService;

public class ApiUtil {
    public static final String BASE_URL = "http://192.168.1.4:8080/api/";
    public static final String WEBDATA_URL = "http://192.168.1.4:8080/webdata/";

    public static GenreService getGenreService() {
        return RetrofitClient.getClient(BASE_URL).create(GenreService.class);
    }

    public static ArtistService getArtistService() {
        return RetrofitClient.getClient(BASE_URL).create(ArtistService.class);
    }
}
