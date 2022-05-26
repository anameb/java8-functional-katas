package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.BoxArt;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve id, title, and a 150x200 box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": BoxArt)
*/
public class Kata4 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        List<Map> listmaps = movieLists.stream()
                .map(movieList -> movieList.getVideos())
                .flatMap(video -> video.stream())
                .map(video -> {
                    return ImmutableMap.of("id", video.getId(),
                            "title", video.getTitle(),
                            "boxart", video.getBoxarts().stream()
                                    .filter(boxArt -> boxArt.getHeight()==200 && boxArt.getWidth()==150));
                })
                .collect(Collectors.toList());

        //listmaps.stream()
          //      .forEach(System.out::println);
                //.flatMap(map -> map.entrySet().stream())
                //.filter();
        //;

        return listmaps;
        //return ImmutableList.of(ImmutableMap.of("id", 5, "title", "Bad Boys", "boxart", new BoxArt(150, 200, "url")));

    }
}
