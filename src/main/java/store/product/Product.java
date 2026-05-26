package store.product;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true, fluent = true)
public class Product {

    @Id
    private String id;

    private String name;
    private Double price;
    private String unit;
}