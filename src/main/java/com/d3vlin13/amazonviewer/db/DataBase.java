package com.d3vlin13.amazonviewer.db;

public class DataBase {
    static public final String URL = "jdbc:mysql://localhost:3306/";
    static public final String DB = "amazonviewer";
    static public final String USER = "root";
    static public final String PASSWORD = "";

    static public final String TMOVIE = "movie";
    static public final String TMOVIE_ID = "id";
    static public final String TMOVIE_TITLE = "title";
    static public final String TMOVIE_GENRE = "genre";
    static public final String TMOVIE_CREATOR = "creator";
    static public final String TMOVIE_DURATION = "duration";
    static public final String TMOVIE_YEAR = "year";

    static public final String TMATERIAL = "material";
    static public final int[] TMATERIAL_ID = {1, 2, 3, 4, 5};

    static public final String TUSER = "user";
    static public final int TUSER_ID = 1;

    static public final String TVIEWED = "viewed";
    static public final String TVIEWED_IDMATERIAL = "id_material";
    static public final String TVIEWED_IDELEMENT = "id_element";
    static public final String TVIEWED_IDUSUARIO = "id_user";
}
