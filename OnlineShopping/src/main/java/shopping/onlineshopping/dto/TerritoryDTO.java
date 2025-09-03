package shopping.onlineshopping.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TerritoryDTO {
    private Long territoryId;
    private String territoryName;
    @NonNull
    private String territoryDescription;
    @NonNull
    private Long region;
}
