import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import ConfirmModal from "../utils/ConfirmModal";
import { createSensor, updateSensor, deleteSensor } from "../../redux/action/sensors";
const EditSensor = ({ sensor }) => {
  const ums = useSelector((state) => state.um.available);
  const dispatch = useDispatch();
  const [id, setId] = useState("");
  const [name, setName] = useState("");
  const [um, setUm] = useState("");
  const [visible, setVisible] = useState(true);
  const [lat, setLat] = useState("");
  const [lon, setLon] = useState("");
  const [alertValue, setAlertValue] = useState("");
  const [rangeMin, setRangeMin] = useState("");
  const [rangeMax, setRangeMax] = useState("");
  const [alertCondition, setAlertCondition] = useState("gt");
  const [action, setAction] = useState("create");

  // useEffect(() => {
  //   console.log(ums[0].id);
  //   setUm("d8061601-ddc8-4f93-ba91-160e27ed346e");
  // }, []);

  useEffect(() => {
    setId(sensor ? sensor.id : "");
    setName(sensor ? sensor.name : "");
    setUm(sensor ? sensor.um.id : "");
    setVisible(sensor ? sensor.visible : true);
    setLat(sensor ? sensor.lat : "");
    setLon(sensor ? sensor.lon : "");
    setAlertValue(sensor ? sensor.alertValue : "");
    setRangeMin(sensor ? sensor.rangeMin : "");
    setRangeMax(sensor ? sensor.rangeMax : "");
    setAlertCondition(sensor ? sensor.alertCondition : "gt");
    setAction(sensor ? "update" : "create");
  }, [sensor]);

  const handleDelete = () => {
    dispatch(deleteSensor({ id }));
  };
  const handleSubmit = () => {
    const payload = { name, visible, lat, lon, alertValue, rangeMin, rangeMax, alertCondition, um };
    console.log(payload);
    id ? dispatch(updateSensor({ id, payload })) : dispatch(createSensor({ payload }));
  };
  const handleVisible = (e) => {
    setVisible(e.target.checked);
  };
  return (
    <>
      <div
        className="modal fade modal-lg"
        id="SensorEditForm"
        tabIndex="-1"
        aria-labelledby="SensorEditForm"
        aria-hidden="true"
      >
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h1 className="modal-title fs-5" id="exampleModalLabel">
                {id ? "Modifica" : "Crea"} Sensore
              </h1>
              <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div className="modal-body">
              <div className="container-fluid">
                <form className="needs-validation" novalidate>
                  <div className="mb-3">
                    <label htmlFor="name" className="form-label">
                      Nome
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      id="name"
                      value={name}
                      required
                      onChange={(e) => setName(e.target.value)}
                    ></input>
                    <div className="valid-feedback">Looks good!</div>
                    <div class="invalid-feedback">Please choose a username.</div>
                  </div>
                  {ums && (
                    <div className="mb-3">
                      <label htmlFor="um" className="form-label">
                        Unità di misura
                      </label>
                      <select
                        className="form-select"
                        id="um"
                        aria-label="Example select with button addon"
                        value={um}
                        onChange={(e) => setUm(e.target.value)}
                      >
                        <option>--Seleziona un unità --</option>
                        {ums.length > 1 &&
                          ums.map((um) => (
                            <option key={um.id} value={um.id}>
                              {um.unit}
                            </option>
                          ))}
                      </select>
                    </div>
                  )}
                  <div className="mb-3">
                    <label htmlFor="soglia" className="form-label">
                      Soglia
                    </label>
                    <input
                      type="number"
                      className="form-control"
                      id="soglia"
                      value={alertValue}
                      required
                      onChange={(e) => setAlertValue(e.target.value)}
                    ></input>
                    <div className="valid-feedback">Looks good!</div>
                  </div>
                  <div className="mb-3">
                    <label htmlFor="alertCondition" className="form-label">
                      Condizione di allarme
                    </label>
                    <select
                      className="form-select"
                      id="alertCondition"
                      aria-label="Example select with button addon"
                      value={alertCondition}
                      onChange={(e) => setAlertCondition(e.target.value)}
                    >
                      <option value="gt">Maggiore della soglia</option>;<option value="lt">Minore della soglia</option>;
                      <option value="eq">Uguale alla soglia</option>;
                    </select>
                  </div>
                  <div className="mb-3">
                    <label htmlFor="minimo" className="form-label">
                      Minimo
                    </label>
                    <input
                      type="number"
                      className="form-control"
                      id="minimo"
                      value={rangeMin}
                      required
                      onChange={(e) => setRangeMin(e.target.value)}
                    ></input>
                    <div className="valid-feedback">Looks good!</div>
                  </div>
                  <div className="mb-3">
                    <label htmlFor="massimo" className="form-label">
                      Massimo
                    </label>
                    <input
                      type="number"
                      className="form-control"
                      id="massimo"
                      value={rangeMax}
                      required
                      onChange={(e) => setRangeMax(e.target.value)}
                    ></input>
                    <div className="valid-feedback">Looks good!</div>
                  </div>
                  <div className="mb-3">
                    <label htmlFor="lat" className="form-label">
                      Latitudine
                    </label>
                    <input
                      type="number"
                      className="form-control"
                      id="lat"
                      value={lat}
                      required
                      onChange={(e) => setLat(e.target.value)}
                    ></input>
                    <div className="valid-feedback">Looks good!</div>
                  </div>
                  <div className="mb-3">
                    <label htmlFor="lon" className="form-label">
                      Longitudine
                    </label>
                    <input
                      type="number"
                      className="form-control"
                      id="lon"
                      value={lon}
                      required
                      onChange={(e) => setLon(e.target.value)}
                    ></input>
                    <div className="valid-feedback">Looks good!</div>
                  </div>
                  <div className="mb-3">
                    <div className="form-check form-switch">
                      <input
                        className="form-check-input"
                        type="checkbox"
                        role="switch"
                        id="visible"
                        checked={visible}
                        onChange={handleVisible}
                      ></input>
                      <label className="form-check-label" htmlFor="visible">
                        Visibile
                      </label>
                    </div>
                  </div>
                </form>
              </div>
            </div>
            <div className="modal-footer">
              <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">
                Chiudi
              </button>

              <button
                type="button"
                className="btn btn-primary"
                data-bs-toggle="modal"
                data-bs-target="#confirmSensorAction"
                // onClick={() => setSave(true)}
              >
                {id ? "Modifica" : "Salva"}
              </button>
              {sensor && (
                <button
                  type="button"
                  className="btn btn-danger"
                  data-bs-toggle="modal"
                  data-bs-target="#confirmSensorAction"
                  onClick={() => setAction("delete")}
                >
                  Elimina
                </button>
              )}
            </div>
          </div>
        </div>
      </div>
      <ConfirmModal
        id="confirmSensorAction"
        handleSubmit={handleSubmit}
        handleDelete={handleDelete}
        action={action}
        actionTxt={{
          update: "Il sensore verrà Aggiornato",
          create: "Il sensore verrà Salvato",
          delete: "Il sensore verrà Cancellato"
        }}
      />
    </>
  );
};
export default EditSensor;
