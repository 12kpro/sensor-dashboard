package capstone.sensor_api;

import capstone.sensor_api.sensors.Sensor;
import capstone.sensor_api.sensors.Um;
import capstone.sensor_api.sensors.proxy.ControlProcess;
import capstone.sensor_api.sensors.repository.SensorRepository;
import capstone.sensor_api.sensors.repository.UmRepository;
import capstone.sensor_api.utils.AlertConditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@Slf4j
@Component
public class SensorRunner implements CommandLineRunner {

    private Boolean loadDemoSensors = true;
    private String[] UmDefault = new String[]{"C°","m/s","Pa"};
    @Autowired
    SensorRepository sensorRepository;
    @Autowired
    UmRepository umRepository;
    @Autowired
    ControlProcess process;
    //TODO aggiungere su env.properties
//    @Value("${load.demo.sensors}")
//    public void setloadDemoSensors(Boolean loadDemoSensors) {
//        this.loadDemoSensors = loadDemoSensors;
//    }
    ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    @Override
    public void run(String... args) throws Exception {

        if(umRepository.count() == 0) {
            for (String u : UmDefault) {
                umRepository.save(new Um(u));
            }
        }

        if (loadDemoSensors && sensorRepository.count() == 0){
            List<Um> ums = umRepository.findAll();
            List<Sensor> demoSensors = Arrays.asList(
                    new Sensor("Temperatura",true, 42.0, 42.0,6.0,-20.0, 50.0, AlertConditions.gt ,ums.get(0)),
                    new Sensor("Vento",true, 42.0, 42.0,4.0, 0.0, 50.0, AlertConditions.gt ,ums.get(1)),
                    new Sensor("Pressione",true, 42.0, 42.0,6.0, 0.0, 5.0,AlertConditions.gt ,ums.get(2))
            );


            for (Sensor s : demoSensors) {
                sensorRepository.save(s);
            }

        }

        List<Sensor> savedSensor = sensorRepository.findAll();

        if(process.getSensors().isEmpty() && !savedSensor.isEmpty()){
            for (Sensor s : savedSensor) {
                process.addSensor(s);
            }
        }
        List<Sensor> observedSensor = process.getSensors();
        Runnable task = () -> {
            Random rand = new Random();
            Sensor s = observedSensor.get(rand.nextInt(observedSensor.size()));   //savedSensor.get(rand.nextInt(savedSensor.size()));
            s.setCurrentValue(s.getRangeMin() + (s.getRangeMax() - s.getRangeMin()) * rand.nextDouble());
        };

        executor.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);


    }


}