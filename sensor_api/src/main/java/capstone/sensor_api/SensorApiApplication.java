package capstone.sensor_api;

import capstone.sensor_api.sensors.Sensor;
import capstone.sensor_api.sensors.proxy.ControlCenterImpl;
import capstone.sensor_api.sensors.proxy.ControlCenterProxy;
import capstone.sensor_api.sensors.proxy.ControlProcess;
import capstone.sensor_api.sensors.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SensorApiApplication {
//	private static ControlCenterImpl controlCenter = new ControlCenterImpl();
//	private static ControlCenterProxy proxy = new ControlCenterProxy(controlCenter);
//	public static ControlProcess process = new ControlProcess(proxy);
	public static void main(String[] args) {
		SpringApplication.run(SensorApiApplication.class, args);



	}

}
