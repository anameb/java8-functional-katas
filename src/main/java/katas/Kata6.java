package katas;

import model.BoxArt;
import model.Movie;
import util.DataUtil;

import java.util.List;
import java.util.Optional;

/*
    Goal: Retrieve the url of the largest boxart using map() and reduce()
    DataSource: DataUtil.getMovieLists()
    Output: String
*/
public class Kata6 {
    public static String execute() {
        List<Movie> movies = DataUtil.getMovies();

        Optional<BoxArt> result = movies.stream()
                .map(movie -> movie.getBoxarts())
                .flatMap(boxArts -> boxArts.stream())
                .reduce((x, y) -> x.getWidth() > y.getWidth() ? x : y);
        return result.get().getUrl();


    }
}
