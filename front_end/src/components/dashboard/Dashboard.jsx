import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useWebSocket } from "react-use-websocket/dist/lib/use-websocket";
import Sensor from "./Sensor";
import { fetchSensors } from "../../redux/action/sensors";
import { fetchRoles } from "../../redux/action/roles";
import { fetchUms } from "../../redux/action/um";
import { fetchUsers } from "../../redux/action/users";
import { fillSensorData } from "../../redux/action/sensorData";

import Chart from "./Chart";
import SensorMap from "./SensorMap";
import Alerts from "./Alerts";

const Dashboard = () => {
  const dispatch = useDispatch();
  const bookMarkedSensors = useSelector((state) => state.auth.bookmarks);
  const userData = useSelector((state) => state.auth.userData);
  const { lastJsonMessage } = useWebSocket("ws://localhost:5080/sensorevent", {
    onOpen: () => console.log("opened"),
    shouldReconnect: (closeEvent) => true
  });

  useEffect(() => {
    if (userData && userData.roles.find((r) => r.name === "ADMIN")) {
      dispatch(fetchRoles());
      dispatch(fetchUms());
      dispatch(fetchUsers());
    }
    dispatch(fetchSensors());
    if (bookMarkedSensors.length > 0) {
      dispatch(fillSensorData(bookMarkedSensors[0]));
    }
  }, []);

  return (
    <>
      {userData && (
        <div className="row">
          <div className="col-12">
            <h2 className="mb-3">
              Benvenuto {userData.name} {userData.surname}
            </h2>
          </div>
        </div>
      )}
      {bookMarkedSensors.length > 0 ? (
        <>
          <div className="row row-cols row-cols-sm-2 row-cols-md-3 row-cols-lg-4 row-cols-xl-5 row-cols-xxl-6">
            {bookMarkedSensors.map((id) => (
              <Sensor key={id} sensorId={id} msg={lastJsonMessage} />
            ))}
          </div>
          <div className="row">
            <div className="col-12 col-lg-8">
              <Chart />
            </div>
            <div className="col-12 col-lg-4">
              <SensorMap />
            </div>
          </div>
        </>
      ) : (
        <div className="row">
          <div className="col-12">
            <p className="mb-3">Per iniziare, vai nel tuo profilo e seleziona quali sensori visualizzare</p>
          </div>
        </div>
      )}
      <Alerts />
    </>
  );
};
export default Dashboard;
