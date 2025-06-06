package com.d3vlin13.amazonviewer;

import com.d3vlin13.amazonviewer.model.*;
import com.d3vlin13.amazonviewer.util.AmazonUtil;
import com.d3vlin13.makereport.Report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * <h1>AmazonViewer</h1>
 * AmazonViewer is a program that lets you to watch movies, TV series with episodes,
 * books, and magazines. It also allows you to generate general reports with the date and time.
 * <p>There are some rules such as all items can be viewed or read except for magazines,
 * which can only be viewed as a display without being read.</p>
 *
 * @author D3vlin13
 * @version 1.1
 * @since 2025
 */
public class Main {
    static ArrayList<Book> books= Book.makeBookList();
    static ArrayList<Movie> movies = new ArrayList<>();
    static ArrayList<Serie> series = Serie.makeSeriesList();

    public static void main(String[] args) {
        showMenu();
    }

    public static void showMenu() {
        int exit = 0;
        do {
            System.out.println("BIENVENIDOS AMAZON VIEWER");
            System.out.println();
            System.out.println("Selecciona el número de la opción deseada");
            System.out.println("1. Movies");
            System.out.println("2. Series");
            System.out.println("3. Books");
            System.out.println("4. Magazines");
            System.out.println("5. Report");
            System.out.println("6. Report Today");
            System.out.println("0. Exit");

            int response = AmazonUtil.validateUserResponseMenu(0, 6);

            switch (response) {
                case 0:
                    exit = 0;
                    break;
                case 1:
                    showMovies();
                    break;
                case 2:
                    showSeries();
                    break;
                case 3:
                    showBooks();
                    break;
                case 4:
                    showMagazines();
                    break;
                case 5:
                    makeReport();
                    exit = 1;
                    break;
                case 6:
                    makeReport(new Date());
                    exit = 1;
                    break;

                default:
                    System.out.println();
                    System.out.println("....¡¡Selecciona una opción!!....");
                    System.out.println();
                    exit = 1;
                    break;
            }
        } while(exit != 0);
    }

    public static void showMovies() {
        movies = Movie.makeMoviesList();
        int exit = 1;
        do {
            System.out.println();
            System.out.println(":: MOVIES ::");
            System.out.println();

            for (int i = 0; i < movies.size(); i++) { //1. Movie 1
                System.out.println(i+1 + ". " + movies.get(i).getTitle() + " Visto: " + movies.get(i).isViewed());
            }

            System.out.println("0. Regresar al Menu");
            System.out.println();

            int response = AmazonUtil.validateUserResponseMenu(0, movies.size());

            if(response == 0) {
                exit = 0;
                showMenu();
                break;
            }

            if (response > 0) {
                Movie movieSelected = movies.get(response-1);
                movieSelected.view();
            }
        } while(exit != 0);

    }

    public static void showSeries() {
        int exit = 1;
        do {
            System.out.println();
            System.out.println(":: SERIES ::");
            System.out.println();

            for (int i = 0; i < series.size(); i++) { //1. Serie 1
                System.out.println(i+1 + ". " + series.get(i).getTitle() + " Visto: " + series.get(i).isViewed());
            }

            System.out.println("0. Regresar al Menu");
            System.out.println();

            int response = AmazonUtil.validateUserResponseMenu(0, series.size());

            if(response == 0) {
                exit = 0;
                showMenu();
            }

            if(response > 0) {
                showChapters(series.get(response-1).getChapters());
            }
        } while(exit != 0);
    }

    public static void showChapters(ArrayList<Chapter> chaptersOfSerieSelected) {
        int exit = 1;
        do {
            System.out.println();
            System.out.println(":: CHAPTERS ::");
            System.out.println();


            for (int i = 0; i < chaptersOfSerieSelected.size(); i++) { //1. Chapter 1
                System.out.println(i+1 + ". " + chaptersOfSerieSelected.get(i).getTitle() + " Visto: " + chaptersOfSerieSelected.get(i).isViewed());
            }

            System.out.println("0. Regresar al Menu");
            System.out.println();

            int response = AmazonUtil.validateUserResponseMenu(0, chaptersOfSerieSelected.size());

            if(response == 0) {
                exit = 0;
            }

            if(response > 0) {
                Chapter chapterSelected = chaptersOfSerieSelected.get(response-1);
                chapterSelected.view();
            }
        } while(exit != 0);
    }

    public static void showBooks() {
        int exit = 1;
        do {
            System.out.println();
            System.out.println(":: BOOKS ::");
            System.out.println();

            for (int i = 0; i < books.size(); i++) { //1. Book 1
                System.out.println(i+1 + ". " + books.get(i).getTitle() + " Leído: " + books.get(i).isReaded());
            }

            System.out.println("0. Regresar al Menu");
            System.out.println();

            int response = AmazonUtil.validateUserResponseMenu(0, books.size());

            if(response == 0) {
                exit = 0;
                showMenu();
            }

            if(response > 0) {
                Book bookSelected = books.get(response-1);
                bookSelected.view();
            }
        } while(exit != 0);
    }

    public static void showMagazines() {
        ArrayList<Magazine> magazines = Magazine.makeMagazineList();

        int exit = 0;
        do {
            System.out.println();
            System.out.println(":: MAGAZINES ::");
            System.out.println();

            for (int i = 0; i < magazines.size(); i++) { //1. Book 1
                System.out.println(i+1 + ". " + magazines.get(i).getTitle());
            }

            System.out.println("0. Regresar al Menu");
            System.out.println();

            int response = AmazonUtil.validateUserResponseMenu(0, 0);

            if(response == 0) {
                exit = 0;
                showMenu();
            }
        } while(exit != 0);
    }

    public static void makeReport() {
        Report report = new Report();
        report.setNameFile("reporte");
        report.setExtension("txt");
        report.setTitle(":: VISTOS ::");
        String contentReport = "";

        for (Movie movie : movies) {
            if (movie.getIsViewed()) {
                contentReport += movie.toString() + "\n";

            }
        }

        for (Serie serie : series) {
            ArrayList<Chapter> chapters = serie.getChapters();
            for (Chapter chapter : chapters) {
                if (chapter.getIsViewed()) {
                    contentReport += chapter.toString() + "\n";

                }
            }
        }

        for (Book book : books) {
            if (book.getIsReaded()) {
                contentReport += book.toString() + "\n";

            }
        }

        report.setContent(contentReport);
        report.makeReport();
        System.out.println("Reporte Generado");
        System.out.println();
    }

	public static void makeReport(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-h-m-s-S");
		String dateString = df.format(date);
		Report report = new Report();

		report.setNameFile("reporte" + dateString);
		report.setExtension("txt");
		report.setTitle(":: VISTOS ::");


		SimpleDateFormat dfNameDays = new SimpleDateFormat("E, W MMM Y");
		dateString = dfNameDays.format(date);
		String contentReport = "Date: " + dateString + "\n\n\n";

		for (Movie movie : movies) {
			if (movie.getIsViewed()) {
				contentReport += movie.toString() + "\n";

			}
		}

		for (Serie serie : series) {
			ArrayList<Chapter> chapters = serie.getChapters();
			for (Chapter chapter : chapters) {
				if (chapter.getIsViewed()) {
					contentReport += chapter.toString() + "\n";

				}
			}
		}

		for (Book book : books) {
			if (book.getIsReaded()) {
				contentReport += book.toString() + "\n";

			}
		}

		report.setContent(contentReport);
		report.makeReport();

		System.out.println("Reporte Generado");
		System.out.println();
	}
}