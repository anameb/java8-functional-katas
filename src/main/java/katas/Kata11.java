package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.BoxArt;
import model.InterestingMoment;
import util.DataUtil;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    Goal: Create a datastructure from the given data:

    This time we have 4 seperate arrays each containing lists, videos, boxarts, and bookmarks respectively.
    Each object has a parent id, indicating its parent.
    We want to build an array of list objects, each with a name and a videos array.
    The videos array will contain the video's id, title, bookmark time, and smallest boxart url.
    In other words we want to build the following structure:

    [
        {
            "name": "New Releases",
            "videos": [
                {
                    "id": 65432445,
                    "title": "The Chamber",
                    "time": 32432,
                    "boxart": "http://cdn-0.nflximg.com/images/2891/TheChamber130.jpg"
                },
                {
                    "id": 675465,
                    "title": "Fracture",
                    "time": 3534543,
                    "boxart": "http://cdn-0.nflximg.com/images/2891/Fracture120.jpg"
                }
            ]
        },
        {
            "name": "Thrillers",
            "videos": [
                {
                    "id": 70111470,
                    "title": "Die Hard",
                    "time": 645243,
                    "boxart": "http://cdn-0.nflximg.com/images/2891/DieHard150.jpg"
                },
                {
                    "id": 654356453,
                    "title": "Bad Boys",
                    "time": 984934,
                    "boxart": "http://cdn-0.nflximg.com/images/2891/BadBoys140.jpg"
                }
            ]
        }
    ]

    DataSource: DataUtil.getLists(), DataUtil.getVideos(), DataUtil.getBoxArts(), DataUtil.getBookmarkList()
    Output: the given datastructure
*/
public class Kata11 {
    public static List<Map> execute() {
        List<Map> lists = DataUtil.getLists();
        List<Map> videos = DataUtil.getVideos();
        List<Map> boxArts = DataUtil.getBoxArts();
        List<Map> bookmarkList = DataUtil.getBookmarkList();

        //return ImmutableList.of(ImmutableMap.of("name", "someName", "videos", ImmutableList.of(
               // ImmutableMap.of("id", 5, "title", "The Chamber", "time", 123, "boxart", "someUrl")
        //)));

        //return lists.stream()
                //.map(movie -> {
                    //return ImmutableMap.of("id",movie.getId(), "title", movie.getTitle(), "time", optenerTiempo(movie.getInterestingMoments()), "url", menorBox(movie.getBoxarts()));
                //})
                //.collect(Collectors.toList());

        return lists.stream()
                .map(map -> {
                    return ImmutableMap.of("name", map.get("name"), "videos", obtenerVideos((Integer) map.get("id"), videos));
                })
                .collect(Collectors.toList());

    }

    public static Stream<ImmutableMap<String, Serializable>> obtenerVideos (Integer listId, List<Map> videos){
        return videos.stream()
                .filter(map -> map.get("listId").equals(listId))
                .map(map -> {
                    return ImmutableMap.of("id", (Integer) map.get("id"), "title", (String) map.get("title") ,
                            "time", obtTiempo((List<Map>) map.get("time"))), "url", minBox(map.get("url")));
                })
                ;
    }

    private static <time> Object obtTiempo(List<Map> bookmarkLists) {
        return (Date) bookmarkLists.stream()
            .map(book -> book.get("time"))
            .collect(Collectors.toList());
    }

    public static String minBox(List<BoxArt> boxArtList) {
        Optional<BoxArt> result = boxArtList.stream()
                .reduce((x, y) -> x.getWidth() < y.getWidth() ? x : y);
        return result.get().getUrl();


    }

}
