package com.d3vlin13.amazonviewer.model.dao;

import com.d3vlin13.amazonviewer.db.DBConnectionManager;
import com.d3vlin13.amazonviewer.model.Movie;

import java.sql.*;
import java.util.ArrayList;

import static com.d3vlin13.amazonviewer.db.DataBase.*;

public interface IMovieDao {
    default Movie setMovieViewed(Movie movie) {
        try (Connection connection = DBConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO " + TVIEWED +
                    " (" + TVIEWED_IDMATERIAL + ", " + TVIEWED_IDELEMENT + ", " + TVIEWED_IDUSUARIO + ")" +
                    " VALUES(" + TMATERIAL_ID[0] + ", " + movie.getId() + ", " + TUSER_ID + ")";
            if (statement.executeUpdate(query) > 0) {
                System.out.println("Visto!");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return movie;
    }

    default ArrayList<Movie> read() {
        ArrayList<Movie> movies = new ArrayList<>();
        try (Connection connection = DBConnectionManager.getConnection()) {
            String query = "SELECT * FROM " + TMOVIE;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Movie movie = new Movie(
                        resultSet.getString(TMOVIE_TITLE),
                        resultSet.getString(TMOVIE_GENRE),
                        resultSet.getString(TMOVIE_CREATOR),
                        resultSet.getInt(TMOVIE_DURATION),
                        resultSet.getShort(TMOVIE_YEAR));
                movie.setId(resultSet.getInt(TMOVIE_ID));
                movie.setViewed(getMovieViewed(preparedStatement, connection, movie.getId()));
                movies.add(movie);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return movies;
    }

    private boolean getMovieViewed(PreparedStatement preparedStatement, Connection connection, int idMovie) {
        boolean viewed = false;
        String query = "SELECT * FROM " + TVIEWED +
                " WHERE " + TVIEWED_IDMATERIAL + " = ?" +
                " AND " + TVIEWED_IDELEMENT + " = ?" +
                " AND " + TVIEWED_IDUSUARIO + " = ?";
        ResultSet resultSet;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, TMATERIAL_ID[0]);
            preparedStatement.setInt(2, idMovie);
            preparedStatement.setInt(3, TUSER_ID);
            resultSet = preparedStatement.executeQuery();
            viewed = resultSet.next();

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return viewed;
    }
}
