package ma.xproce.inventoryservice.service;

import ma.xproce.inventoryservice.dtos.VideoDto;
import ma.xproce.inventoryservice.dtos.VideoRequest;

import java.util.List;

public interface VideoManager {
    List<VideoDto> getAllVideos();
    VideoDto findById(Long id);
    VideoDto saveVideo(VideoRequest request);
    VideoDto changeCreator(Long videoId, Long creatorId);
}