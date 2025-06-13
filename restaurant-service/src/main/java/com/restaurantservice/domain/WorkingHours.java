package com.restaurantservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalTime;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WorkingHours {
    private DaySchedule monday;
    private DaySchedule tuesday;
    private DaySchedule wednesday;
    private DaySchedule thursday;
    private DaySchedule friday;
    private DaySchedule saturday;
    private DaySchedule sunday;

    @Getter
    @Setter
    @EqualsAndHashCode
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DaySchedule {
        private LocalTime open;
        private LocalTime close;

        @Field("is_closed")
        private Boolean isClosed;
    }
}
