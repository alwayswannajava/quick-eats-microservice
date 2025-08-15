package com.restaurantservice.common;

public enum CuisineType {
    ITALIAN("Italian"),
    UKRAINIAN("Ukrainian"),
    CHINESE("Chinese"),
    JAPANESE("Japanese"),
    AMERICAN("American"),
    MEXICAN("Mexican"),
    INDIAN("Indian"),
    THAI("Thai"),
    FRENCH("French"),
    GERMAN("German"),
    GREEK("Greek"),
    TURKISH("Turkish"),
    KOREAN("Korean"),
    VIETNAMESE("Vietnamese"),
    MEDITERRANEAN("Mediterranean"),
    MIDDLE_EASTERN("Middle Eastern"),
    FAST_FOOD("Fast Food"),
    PIZZA("Pizza"),
    BURGER("Burger"),
    SUSHI("Sushi"),
    SEAFOOD("Seafood"),
    VEGETARIAN("Vegetarian"),
    VEGAN("Vegan"),
    HEALTHY("Healthy"),
    DESSERTS("Desserts"),
    COFFEE("Coffee"),
    BAKERY("Bakery");

    private final String displayName;

    CuisineType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
