package solutions.fluidity.test.model.dto;

public class FixturesDto {
    private Long eventId;
    private String eventCode;
    private Long fixtureId;
    private Long homeTeamId;
    private Long awayTeamId;
    private String homeTeamName;
    private String awayTeamName;

    public FixturesDto(Long eventId, String eventCode, Long fixtureId, Long homeTeamId, Long awayTeamId, String homeTeamName, String awayTeamName) {
        this.eventId = eventId;
        this.eventCode = eventCode;
        this.fixtureId = fixtureId;
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
    }


    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public Long getFixtureId() {
        return fixtureId;
    }

    public Long getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(Long homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public Long getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(Long awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public void setFixtureId(Long fixtureId) {
        this.fixtureId = fixtureId;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

}
