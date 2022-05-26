package katas;

import com.google.common.collect.ImmutableMap;
import util.DataUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    Goal: Create a datastructure from the given data:

    We have 2 arrays each containing lists, and videos respectively.
    Each video has a listId field indicating its parent list.
    We want to build an array of list objects, each with a name and a videos array.
    The videos array will contain the video's id and title.
    In other words we want to build the following structure:

    [
        {
            "name": "New Releases",
            "videos": [
                {
                    "id": 65432445,
                    "title": "The Chamber"
                },
                {
                    "id": 675465,
                    "title": "Fracture"
                }
            ]
        },
        {
            "name": "Thrillers",
            "videos": [
                {
                    "id": 70111470,
                    "title": "Die Hard"
                },
                {
                    "id": 654356453,
                    "title": "Bad Boys"
                }
            ]
        }
    ]

    DataSource: DataUtil.getLists(), DataUtil.getVideos()
    Output: the given datastructure
*/
public class Kata10 {
    public static List<Map> execute() {
        List<Map> lists = DataUtil.getLists();
        List<Map> videos = DataUtil.getVideos();



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
                    return ImmutableMap.of("id", (Integer) map.get("id"), "title", (String) map.get("title"));
                })
        ;
    }
}
