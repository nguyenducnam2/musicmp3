package vn.aptech.musicstoreapp.api;

import vn.aptech.musicstoreapp.service.GenreService;

public class ApiUtil {
    public static final String BASE_URL = "http://192.168.1.4:8080/api/";

    public static GenreService getGenreService() {
        return RetrofitClient.getClient(BASE_URL).create(GenreService.class);
    }
}
