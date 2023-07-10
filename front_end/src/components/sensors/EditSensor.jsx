import { useEffect } from "react";

const EditSensor = () => {
  useEffect(() => {}, []);

  const handleDelete = () => {};
  const handleSubmit = (e) => {};
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
                Aggiungi/Modifica Sensore
              </h1>
              <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div className="modal-body">
              <div className="container-fluid">
                <form onSubmit={handleSubmit}>
                  <div class="mb-3">
                    <label htmlFor="validationCustom01" class="form-label">
                      Nome
                    </label>
                    <input type="text" class="form-control" id="validationCustom01" value="Mark" required></input>
                    <div class="valid-feedback">Looks good!</div>
                  </div>
                  <div class="mb-3">
                    <label htmlFor="vinputGroupSelect04" class="form-label">
                      Unità di misura
                    </label>
                    <select class="form-select" id="inputGroupSelect04" aria-label="Example select with button addon">
                      <option selected>Choose...</option>
                      <option value="1">One</option>
                      <option value="2">Two</option>
                      <option value="3">Three</option>
                    </select>
                  </div>
                  <div class="mb-3">
                    <label htmlFor="validationCustom01" class="form-label">
                      Soglia
                    </label>
                    <input type="text" class="form-control" id="validationCustom01" value="Mark" required></input>
                    <div class="valid-feedback">Looks good!</div>
                  </div>
                  <div class="mb-3">
                    <label htmlFor="validationCustom01" class="form-label">
                      Minimo
                    </label>
                    <input type="text" class="form-control" id="validationCustom01" value="Mark" required></input>
                    <div class="valid-feedback">Looks good!</div>
                  </div>
                  <div class="mb-3">
                    <label htmlFor="validationCustom01" class="form-label">
                      Massimo
                    </label>
                    <input type="text" class="form-control" id="validationCustom01" value="Mark" required></input>
                    <div class="valid-feedback">Looks good!</div>
                  </div>
                  <div class="mb-3">
                    <div class="form-check form-switch">
                      <input class="form-check-input" type="checkbox" role="switch" id="flexSwitchCheckDefault"></input>
                      <label class="form-check-label" for="flexSwitchCheckDefault">
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
                data-bs-target="#confirmSensorEdit"
                // onClick={() => setSave(true)}
              >
                {/* {id ? "Modifica" : "Salva"} */}
              </button>
              <button
                type="button"
                className="btn btn-danger"
                data-bs-toggle="modal"
                data-bs-target="#confirmSensorEdit"
                //   onClick={() => setSave(false)}
              >
                Elimina
              </button>
            </div>
          </div>
        </div>
      </div>
      <div
        className="modal fade"
        id="confirmSensorEdit"
        tabIndex="-1"
        aria-labelledby="confirmSensorEdit"
        aria-hidden="true"
      >
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h1 className="modal-title fs-5" id="confirmPostModalLabel">
                Conferma operazione
              </h1>
              <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            {/* <div className="modal-body">IL post verrà {save ? (id ? "Modificata" : "Salvata") : "Eliminata"}</div> */}
            <div className="modal-footer">
              <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">
                Annulla
              </button>
              <button
                type="button"
                className="btn btn-primary"
                data-bs-dismiss="modal"
                // onClick={save ? handleSubmit : handleDelete}
              >
                Conferma
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};
export default EditSensor;
