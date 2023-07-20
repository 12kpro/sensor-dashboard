import { useDispatch, useSelector } from "react-redux";
import Pager from "../pager/Pager";
import EditSensor from "../sensors/EditSensor";
import EditUm from "../sensors/EditUm";
import SensorRow from "./rows/SensorRow";
import { useEffect, useState } from "react";
import { removeSensorFromBookmark } from "../../redux/slices/auth";

const SensorTable = ({ edit }) => {
  const dispatch = useDispatch();
  const loadedSensor = useSelector((state) => state.sensors.available);
  const bookMarkedSensors = useSelector((state) => state.auth.bookmarks);
  const [sensor, setSensor] = useState(null);

  useEffect(() => {
    for (const bookmark of bookMarkedSensors) {
      if (!loadedSensor.content.find((sensor) => sensor.id !== bookmark)) {
        dispatch(removeSensorFromBookmark(bookmark));
      }
    }
  }, [loadedSensor]);

  return (
    <div className="card flex-fill">
      <div className="card-header  d-flex justify-content-between align-items-center">
        <h5 className="card-title mb-0">Sensori</h5>
        {edit && (
          <div className="btn-group" role="group" aria-label="Basic example">
            <button
              className="btn"
              id="bd-theme"
              type="button"
              aria-expanded="true"
              aria-label="Toggle theme (auto)"
              data-bs-toggle="modal"
              data-bs-target="#SensorEditForm"
              onClick={() => setSensor(null)}
            >
              <i className="bi bi-node-plus"></i>
            </button>
            <button
              className="btn"
              id="bd-theme"
              type="button"
              aria-expanded="true"
              aria-label="Toggle theme (auto)"
              data-bs-toggle="modal"
              data-bs-target="#editUm"
            >
              <i className="bi bi-gear"></i>
            </button>
          </div>
        )}
      </div>
      <table className="table table-hover my-0 text-center table-striped">
        <thead>
          <tr>
            <th>Nome</th>
            <th className="d-none d-xl-table-cell">Latitudione</th>
            <th className="d-none d-xl-table-cell">Longitudine</th>
            <th className="d-none d-sm-table-cell ">Unità</th>
            <th className="d-none d-sm-table-cell ">Allarme</th>
            <th className="d-none d-md-table-cell">Massimo</th>
            <th className="d-none d-md-table-cell">Minimo</th>
            {edit && <th className="d-none d-md-table-cell">Visibile</th>}
            <th>Azioni</th>
          </tr>
        </thead>
        <tbody>
          {console.log(loadedSensor)}
          {loadedSensor && edit
            ? loadedSensor.content.length > 0 &&
              loadedSensor.content.map((sensor) => (
                <SensorRow key={sensor.id} edit={edit} sensor={sensor} setSensor={setSensor} />
              ))
            : loadedSensor.content.length > 0 &&
              loadedSensor.content
                .filter((sensor) => sensor.visible)
                .map((sensor) => <SensorRow key={sensor.id} edit={edit} sensor={sensor} setSensor={setSensor} />)}
        </tbody>
      </table>
      {loadedSensor && loadedSensor.totalPages > 1 && (
        <div className="card-footer text-body-secondary">
          <Pager />
        </div>
      )}

      <EditSensor sensor={sensor} />
      <EditUm />
    </div>
  );
};
export default SensorTable;
