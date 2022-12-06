package solutions.fluidity.test.domain.enums;

public enum Position {

    GOAL_KEEPER(1L),
    DEFENDER(2L),
    MIDFIELDER(3L),
    FORWARD(4L);

    private final Long value;

    Position(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}
