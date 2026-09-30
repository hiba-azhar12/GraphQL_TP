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
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.Random;

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

   
    @SubscriptionMapping
    public Flux<VideoDto> notifyVideoChange() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(tick -> {
                    CreatorRequest request = CreatorRequest.builder()
                            .name("x" + new Random().nextInt(1000))
                            .email("x@gmail.com")
                            .build();
                    CreatorDto creator = creatorManager.saveCreator(request);
                    return videoManager.changeCreator(1L, creator.getId());
                });
    }
}