package com.realdolmen.course.domain;

@Silly
public class IssnNumberGenerator implements NumberGenerator{

    @Override
    public int generateNumber() {
        return 8;
    }
}
