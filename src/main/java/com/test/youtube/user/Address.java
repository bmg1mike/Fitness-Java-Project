package com.test.youtube.user;

public record Address(
        String street,
        String suite,
        String city,
        String zipcode,
        Geo geo) {

}
