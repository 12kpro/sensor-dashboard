package capstone.fe_api.dataservice.dto;

import capstone.fe_api.utils.AlertConditions;

import java.util.UUID;

public class Sensor {

    private UUID id;

    private String name;
    private Boolean visible;

    private Double lat;

    private Double lon;

    private Double alertValue;

    private Double rangeMin;

    private Double rangeMax;

    private AlertConditions alertCondition;
}
