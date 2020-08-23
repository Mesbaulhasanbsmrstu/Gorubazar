package com.example.gorubazar;

public class Apiutlize {


    private Apiutlize() {}

    public static final String BASE_URL ="";
    public static Apiservice getAPIService2() {

        return Myclient.getClient(BASE_URL).create(Apiservice.class);
    }
    public static MyApi getAPIService() {

        return Myclient.getClient(BASE_URL).create(MyApi.class);
    }
    public static Loginapi getAPIService1() {

        return  Myclient.getClient(BASE_URL).create(Loginapi.class);
    }
    public static Verifyapi getAPIService3() {

        return  Myclient.getClient(BASE_URL).create(Verifyapi.class);
    }
    public static Showapi getAPIService4() {

        return  Myclient.getClient(BASE_URL).create(Showapi.class);
    }

    public static Updateapi getAPIService5()
    {
        return Myclient.getClient(BASE_URL).create(Updateapi.class);

    }
    public static Deleteapi getAPIService6()
    {
        return Myclient.getClient(BASE_URL).create(Deleteapi.class);

    }
    public static Editapi getAPIService7()
    {
        return Myclient.getClient(BASE_URL).create(Editapi.class);

    }
    public static Showapiforclient getAPIService8()
    {
        return Myclient.getClient(BASE_URL).create(Showapiforclient.class);

    }
}
