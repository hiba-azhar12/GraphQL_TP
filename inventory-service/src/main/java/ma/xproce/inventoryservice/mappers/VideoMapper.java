package ma.xproce.inventoryservice.mappers;

import ma.xproce.inventoryservice.dao.entities.Video;
import ma.xproce.inventoryservice.dtos.VideoDto;
import ma.xproce.inventoryservice.dtos.VideoRequest;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class VideoMapper {

    private static final String DATE_PATTERN = "dd/MM/yyyy";

    private final CreatorMapper creatorMapper;

    public VideoMapper(CreatorMapper creatorMapper) {
        this.creatorMapper = creatorMapper;
    }

    public Video fromVideoRequestToVideo(VideoRequest request) {
        return Video.builder()
                .name(request.getName())
                .url(request.getUrl())
                .description(request.getDescription())
                .datePublication(parseDate(request.getDatePublication()))
                .creator(request.getCreator() == null ? null
                        : creatorMapper.fromCreatorRequestToCreator(request.getCreator()))
                .build();
    }

    public VideoDto fromVideoToVideoDto(Video video) {
        return VideoDto.builder()
                .id(video.getId())
                .name(video.getName())
                .url(video.getUrl())
                .description(video.getDescription())
                .datePublication(formatDate(video.getDatePublication()))
                .creator(video.getCreator() == null ? null
                        : creatorMapper.fromCreatorToCreatorDto(video.getCreator()))
                .build();
    }

    private Date parseDate(String value) {
        if (value == null || value.isBlank()) {
            return new Date();
        }
        try {
            return new SimpleDateFormat(DATE_PATTERN).parse(value);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Date invalide (format attendu : dd/MM/yyyy) : " + value);
        }
    }

    private String formatDate(Date date) {
        return date == null ? null : new SimpleDateFormat(DATE_PATTERN).format(date);
    }
}