package ma.xproce.inventoryservice.web;

import ma.xproce.inventoryservice.dtos.CreatorDto;
import ma.xproce.inventoryservice.dtos.CreatorRequest;
import ma.xproce.inventoryservice.dtos.VideoDto;
import ma.xproce.inventoryservice.dtos.VideoRequest;
import ma.xproce.inventoryservice.service.CreatorManager;
import ma.xproce.inventoryservice.service.VideoManager;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class VideoGraphQlController {

    private final CreatorManager creatorManager;
    private final VideoManager videoManager;

    public VideoGraphQlController(CreatorManager creatorManager, VideoManager videoManager) {
        this.creatorManager = creatorManager;
        this.videoManager = videoManager;
    }

    @QueryMapping
    public List<VideoDto> videoList() {
        return videoManager.getAllVideos();
    }

    @QueryMapping
    public CreatorDto creatorById(@Argument Long id) {
        return creatorManager.findById(id);
    }

    @MutationMapping
    public CreatorDto saveCreator(@Argument CreatorRequest creator) {
        return creatorManager.saveCreator(creator);
    }

    @MutationMapping
    public VideoDto saveVideo(@Argument VideoRequest video) {
        return videoManager.saveVideo(video);
    }
}