package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.BoxArt;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;

/*
    Goal: Retrieve the largest rating using reduce()
    DataSource: DataUtil.getMovies()
    Output: Double
*/
public class Kata5 {
    public static Double execute() {
        List<Movie> movies = DataUtil.getMovies();

        return movies.stream()
                .map(movie -> movie.getRating())
                .reduce(Double.MIN_VALUE, Double::max);

                //.filter(movie -> movie.getRating() == 5)

        //return 3.0;
    }
}
