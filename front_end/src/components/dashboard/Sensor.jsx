import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { fillSensorData } from "../../redux/action/sensorData";
import { addSensorData } from "../../redux/slices/sensorsData";
import { fetchSensors } from "../../redux/action/sensors";
import { addAlert } from "../../redux/slices/alert";
import { Alarm, Warning } from "../../utils/alertsPayload";

const Sensor = ({ sensorId, msg }) => {
  const [value, setValue] = useState(0);
  const [lastUpDate, setLastUpDate] = useState("");
  const [sensor, setSensor] = useState(null);
  const [awaitingData, setAwaitingData] = useState(true);
  const [allarm, setAllarm] = useState(false);
  const [healty, setHealty] = useState(true);

  const sensors = useSelector((state) => state.sensors);
  const rawData = useSelector((state) => state.sensorData);
  const dispatch = useDispatch();
  const alarmSymbol = {
    gt: ">",
    lt: "<",
    eq: "="
  };

  useEffect(() => {
    if (sensors && sensors.available && sensors.available.content.length > 0) {
      let currentSensor = sensors.available.content.find((sensor) => sensor.id === sensorId);
      setSensor(currentSensor);
    }
  }, [sensors]);

  useEffect(() => {
    if (msg && msg.sensorId === sensorId) {
      setValue(Number(msg.value));
      setLastUpDate(
        new Date(msg.time).toLocaleTimeString("it-IT", {
          hour: "2-digit",
          minute: "2-digit",
          second: "2-digit"
        })
      );

      setAwaitingData(false);
      if (rawData && msg.sensorId === rawData.sensorId) {
        dispatch(addSensorData(msg));
        if (sensors.errorMsg) {
          dispatch(fetchSensors());
          dispatch(fillSensorData(sensorId));
        }
      }
    }
  }, [msg]);

  useEffect(() => {
    if (sensor && value) {
      let allarmValue;
      switch (sensor.alertCondition) {
        case "gt":
          allarmValue = value > sensor.alertValue;
          break;
        case "lt":
          allarmValue = value < sensor.alertValue;
          break;
        default:
          allarmValue = value === sensor.alertValue;
      }
      setAllarm(allarmValue);

      const healtyValue = value >= sensor.rangeMin && value <= sensor.rangeMax;
      setHealty(healtyValue);

      if (allarmValue) {
        dispatch(addAlert(new Alarm(sensor, value.toFixed(2), lastUpDate, alarmSymbol[sensor.alertCondition])));
      }
      if (!healtyValue) {
        dispatch(addAlert(new Warning(sensor, value.toFixed(2), lastUpDate)));
      }
    }
  }, [value]);

  // useEffect(() => {
  //   if (sensor && allarm) {
  //     dispatch(addAlert(new Alarm(sensor, value, lastUpDate, alarmSymbol[sensor.alertCondition])));
  //   }
  // }, [allarm]);

  // useEffect(() => {
  //   if (sensor && malfunction) {
  //     dispatch(addAlert(new Warning(sensor, value, lastUpDate)));
  //   }
  // }, [malfunction]);

  const handleClick = (sensorId) => {
    dispatch(fillSensorData(sensorId));
  };

  return (
    <div className="col">
      <div className="card sensor-card mb-3 shadow-sm border border-opacity-10" onClick={() => handleClick(sensorId)}>
        <div className="card-body">
          <div className="d-flex justify-content-between">
            <i
              className={`bi ${
                rawData.sensorId === sensorId ? "bi-check-circle-fill text-warning" : "bi-check-circle"
              }`}
            ></i>
            {awaitingData ? (
              <div className="spinner-grow text-danger spinner-grow-sm" role="status">
                <span className="visually-hidden">Loading...</span>
              </div>
            ) : (
              <i className="bi bi-broadcast-pin text-success "></i>
            )}
          </div>

          {sensor && !sensors.errorMsg ? (
            <>
              <h6 className="card-title text-capitalize m-0">{sensor.name}</h6>
              <span className={`fs-1 mt-1 mb-3 ${allarm ? "text-danger" : "text-secondary"}`}>{value.toFixed(2)}</span>
              <span className="fs-3 mt-1 mb-3 ps-2">{sensor.um.unit}</span>
              <div className="">
                <span className="text-info">
                  <i className="bi bi-clock me-1"></i>
                  {lastUpDate}
                </span>
              </div>

              <div className="d-flex">
                <i className={`bi me-1 ${healty ? "bi-bug" : "bi-bug-fill text-danger ping"}`}></i>
                <span>{`${sensor.rangeMin} < ${value.toFixed(2)} < ${sensor.rangeMax}`}</span>
              </div>
              <div className="d-flex">
                <i className={`bi me-1 ${allarm ? "bi-bell-fill text-danger ring" : "bi-bell"}`}></i>
                <span>{`${value.toFixed(2)} ${alarmSymbol[sensor.alertCondition]} ${sensor.alertValue}`}</span>
              </div>
            </>
          ) : (
            <div className="alert alert-danger mt-3" role="alert">
              {sensors.errorMsg}
            </div>
          )}
        </div>
      </div>
    </div>
  );
};
export default Sensor;
