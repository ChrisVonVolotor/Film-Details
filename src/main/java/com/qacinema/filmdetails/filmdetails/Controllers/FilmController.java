package com.qacinema.filmdetails.filmdetails.Controllers;

import com.uwetrottmann.tmdb2.Tmdb;
import com.uwetrottmann.tmdb2.entities.Movie;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.services.MoviesService;
import com.uwetrottmann.tmdb2.services.SearchService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@RestController
@RequestMapping(value ="/films")
public class FilmController {
    Tmdb tmdb = new Tmdb("1fa36fc7ed8328af5bc485a7b227ec58");
    MoviesService moviesService = tmdb.moviesService();
    SearchService searchService = tmdb.searchService();
    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public MovieResultsPage getCurrent()throws IOException {
            Call<MovieResultsPage> call = moviesService
                    .nowPlaying(null,null);

            MovieResultsPage page = call.execute().body();

        return page;
    }

    @RequestMapping(value = "/upcoming", method = RequestMethod.GET)
    public MovieResultsPage getUpcoming() throws IOException {
        Call<MovieResultsPage> call = moviesService
                .upcoming(null,null);

        MovieResultsPage page = call.execute().body();

        return page;
    }

    @RequestMapping(value = "/search/{name}", method = RequestMethod.GET)
    public MovieResultsPage getSearchResults(@PathVariable String name) throws IOException {
        Call<MovieResultsPage> call = searchService.movie(name,null,null,null,null, null, null);
        MovieResultsPage page = call.execute().body();
        return page;
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    public Movie getFilmById(@PathVariable int id) throws IOException {
        Response<Movie> call = moviesService.summary(id).execute();

        return call.body();
    }
}
