package store.product;

import lombok.Builder;

@Builder
public record ProductIn(

    String name,
    Double price,
    String unit

) {}