package capstone.sensor_api.exceptions;

public class SensorFault extends Exception{
    public SensorFault(String errorMessage) {
        super(errorMessage);
    }
}
