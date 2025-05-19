package shopping.onlineshopping.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoryDTO {
    @NonNull
    private String categoryName;

    @NonNull
    private String description;
}
