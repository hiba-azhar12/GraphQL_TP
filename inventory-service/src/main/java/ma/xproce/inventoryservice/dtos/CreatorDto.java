package ma.xproce.inventoryservice.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreatorDto {
    private Long id;
    private String name;
    private String email;
}