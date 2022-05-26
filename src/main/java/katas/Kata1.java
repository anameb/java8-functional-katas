package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Movie;
import util.DataUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/*
    Goal: use map() to project an array of videos into an array of {id, title}-pairs
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys")
*/
public class Kata1 {
    public static List<Map> execute() {
        List<Movie> movies = DataUtil.getMovies();

        List<Map> idTitles = new ArrayList<>();

        return movies.stream()
                .map(movie -> {
                    return ImmutableMap.of("id", movie.getId(), "title", movie.getTitle());
                })
                .collect(toList());

        //Map listMapreturn = null;
         /*return movies.stream()
                 .map(movie -> {
                     (movie.getId()), movie.getTitle());
                     return movie;
                 })*/
                 //.map(movie -> Collectors.toMap(Movie::getId, Movie::getTitle));
        //.collect(Collectors.toList());
                 //.forEach(movie -> {
                 //    return ImmutableList.of(ImmutableMap.of("id", movie.getId(), "title", movie.getTitle()));
                 //})
                //.map(movie -> {

                //    return ImmutableList.of(ImmutableMap.of("id", movie.getId(), "title", movie.getTitle()));
                //})
                // .forEach(immutableMaps -> )

        //return ImmutableList.of(
        //        (Map) movies.stream()
         //               .map(movie -> {return ImmutableMap.of("id", movie.getId(), "title", movie.getTitle());}));
       //return null;
        //return ImmutableList.of(ImmutableMap.of("id", 5, "title", "Bad Boys"));
    }
}
