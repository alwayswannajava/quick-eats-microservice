package com.restaurantservice.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;

    private String building;

    private String city;

    @Field("postal_code")
    private String postalCode;

    private Coordinates coordinates;

    @Getter
    @Setter
    @EqualsAndHashCode
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static final class Coordinates {
        private BigDecimal latitude;
        private BigDecimal longitude;
    }
}
