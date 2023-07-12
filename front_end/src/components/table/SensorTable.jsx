import { useSelector } from "react-redux";
import Pager from "../pager/Pager";
import EditSensor from "../sensors/EditSensor";
import EditUm from "../sensors/EditUm";
import SensorRow from "./rows/SensorRow";

const SensorTable = ({ edit }) => {
  const loadedSensor = useSelector((state) => state.sensors.available.sensors);
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
      <table className="table table-hover my-0 text-center">
        <thead>
          <tr>
            <th>Nome</th>
            <th className="d-none d-xl-table-cell">Latitudione</th>
            <th className="d-none d-xl-table-cell">Longitudine</th>
            <th className="d-none d-md-table-cell">Unità</th>
            <th>Soglia</th>
            <th className="d-none d-md-table-cell">Massimo</th>
            <th className="d-none d-md-table-cell">Minimo</th>
            {edit && <th className="d-none d-md-table-cell">Visibile</th>}
            <th className="d-none d-md-table-cell">Azioni</th>
          </tr>
        </thead>
        <tbody>
          {loadedSensor.content &&
            loadedSensor.content
              .filter((sensor) => sensor.visible)
              .map((sensor) => <SensorRow key={sensor.id} edit={edit} sensor={sensor} />)}
        </tbody>
      </table>
      {loadedSensor.totalPages > 1 && (
        <div className="card-footer text-body-secondary">
          <Pager />
        </div>
      )}

      <EditSensor />
      <EditUm />
    </div>
  );
};
export default SensorTable;
