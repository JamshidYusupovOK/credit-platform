package org.integer.creditservice.event;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoringResultEvent {
    private Long applicationId;
    private Long clientId;
    private Integer score;
    private String decision;
}
