package ma.xproce.inventoryservice.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class VideoRequest {
    private String name;
    private String url;
    private String description;
    private String datePublication;  
    private CreatorRequest creator;
}