import { useEffect } from "react";
import EditRole from "./EditRole";

const EditUser = () => {
  useEffect(() => {}, []);

  const handleDelete = () => {};
  const handleSubmit = (e) => {};
  return (
    <>
      <div
        className="modal fade modal-lg"
        id="UserEditForm"
        tabIndex="-1"
        aria-labelledby="UserEditForm"
        aria-hidden="true"
      >
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h1 className="modal-title fs-5" id="exampleModalLabel">
                Crea/Modifica utente
              </h1>
              <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div className="modal-body">
              <div className="container-fluid">
                <form className="needs-validation" novalidate onSubmit={handleSubmit}>
                  <div class="mb-3">
                    <label htmlFor="validationCustom01" class="form-label">
                      Nome
                    </label>
                    <input type="text" class="form-control" id="validationCustom01" value="Mark" required></input>
                    <div class="valid-feedback">Looks good!</div>
                  </div>
                  <div class="mb-3">
                    <label htmlFor="validationCustom01" class="form-label">
                      Cognome
                    </label>
                    <input type="text" class="form-control" id="validationCustom01" value="Mark" required></input>
                    <div class="valid-feedback">Looks good!</div>
                  </div>
                  <div class="mb-3">
                    <label htmlFor="validationCustom01" class="form-label">
                      Nome utente
                    </label>
                    <input type="text" class="form-control" id="validationCustom01" value="Mark" required></input>
                    <div class="valid-feedback">Looks good!</div>
                  </div>
                  <div class="mb-3">
                    <label htmlFor="validationCustom01" class="form-label">
                      E-mail
                    </label>
                    <input type="text" class="form-control" id="validationCustom01" value="Mark" required></input>
                    <div class="valid-feedback">Looks good!</div>
                  </div>
                  <div class="mb-3">
                    <label htmlFor="validationCustom01" class="form-label">
                      Password
                    </label>
                    <input type="text" class="form-control" id="validationCustom01" value="Mark" required></input>
                    <div class="valid-feedback">Looks good!</div>
                  </div>
                  <div class="mb-3">
                    <label htmlFor="selectRole" class="form-label">
                      Imposta ruolo
                    </label>
                    <select class="form-select" id="selectRole" aria-label="Example select with button addon">
                      <option selected>Choose...</option>
                      <option value="1">One</option>
                      <option value="2">Two</option>
                      <option value="3">Three</option>
                    </select>
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
                data-bs-target="#confirmUserEdit"
                // onClick={() => setSave(true)}
              >
                {/* {id ? "Modifica" : "Salva"} */}
              </button>
              <button
                type="button"
                className="btn btn-danger"
                data-bs-toggle="modal"
                data-bs-target="#confirmUserEdit"
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
        id="confirmUserEdit"
        tabIndex="-1"
        aria-labelledby="confirmUserEdit"
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
export default EditUser;
