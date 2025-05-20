package shopping.onlineshopping.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TerritoryDTO {
    @NonNull
    private String territoryDescription;
    @NonNull
    private Long region;
}
