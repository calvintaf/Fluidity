package solutions.fluidity.test.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public class EventDto {
    private String eventName;
    private Long eventId;
    private LocalDateTime dateTime;
    private List<FixturesDto> fixturesDtoList;
}
