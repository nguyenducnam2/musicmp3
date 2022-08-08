package com.example.muizkapp.Service_API;

public class APIService {
    private static String base_url = "http://localhost:8080/api/";
    public static Dataservice getService(){
        return APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }
}
