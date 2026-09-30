package ma.xproce.inventoryservice.service;

import ma.xproce.inventoryservice.dao.entities.Creator;
import ma.xproce.inventoryservice.dao.entities.Video;
import ma.xproce.inventoryservice.dao.repositories.CreatorRepository;
import ma.xproce.inventoryservice.dao.repositories.VideoRepository;
import ma.xproce.inventoryservice.dtos.VideoDto;
import ma.xproce.inventoryservice.dtos.VideoRequest;
import ma.xproce.inventoryservice.mappers.VideoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoManagerImpl implements VideoManager {

    private final VideoRepository videoRepository;
    private final CreatorRepository creatorRepository;
    private final VideoMapper videoMapper;

    public VideoManagerImpl(VideoRepository videoRepository,
                            CreatorRepository creatorRepository,
                            VideoMapper videoMapper) {
        this.videoRepository = videoRepository;
        this.creatorRepository = creatorRepository;
        this.videoMapper = videoMapper;
    }

    @Override
    public List<VideoDto> getAllVideos() {
        return videoRepository.findAll().stream()
                .map(videoMapper::fromVideoToVideoDto)
                .toList();
    }

    @Override
    public VideoDto findById(Long id) {
        return videoMapper.fromVideoToVideoDto(getVideoOrThrow(id));
    }

    @Override
    public VideoDto saveVideo(VideoRequest request) {
        Video video = videoMapper.fromVideoRequestToVideo(request);
        // le Creator reçu est nouveau : on le sauvegarde d'abord,
        // sinon JPA refuse d'enregistrer la vidéo qui le référence
        if (video.getCreator() != null) {
            video.setCreator(creatorRepository.save(video.getCreator()));
        }
        return videoMapper.fromVideoToVideoDto(videoRepository.save(video));
    }

    @Override
    public VideoDto changeCreator(Long videoId, Long creatorId) {
        Video video = getVideoOrThrow(videoId);
        Creator creator = creatorRepository.findById(creatorId)
                .orElseThrow(() -> new RuntimeException(String.format("Creator %s not found", creatorId)));
        video.setCreator(creator);
        return videoMapper.fromVideoToVideoDto(videoRepository.save(video));
    }

    private Video getVideoOrThrow(Long id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Video %s not found", id)));
    }
}