package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.*;
import util.DataUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/*
    Goal: Retrieve each video's id, title, middle interesting moment time, and smallest box art url
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl")
*/
public class Kata9 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        return movieLists.stream()
                .flatMap(movieList -> movieList.getVideos().stream())
                .map(movie -> {
                    return ImmutableMap.of("id",movie.getId(), "title", movie.getTitle(), "time", obtenerTiempo(movie.getInterestingMoments()), "url", menorBox(movie.getBoxarts()));
                })
                .collect(Collectors.toList());

    }
    public static String menorBox(List<BoxArt> boxArtList) {
        Optional<BoxArt> result = boxArtList.stream()
                .reduce((x, y) -> x.getWidth() < y.getWidth() ? x : y);
        return result.get().getUrl();
    }

    public static Date obtenerTiempo(List<InterestingMoment> momentList){
        List<InterestingMoment> result = momentList.stream()
                .filter(interestingMoment -> interestingMoment.getType().equals("Middle"))
                .collect(Collectors.toList());
        return result.get(0).getTime();
    }
}
