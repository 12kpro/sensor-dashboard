import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { updateUserCredential } from "../../redux/action/auth";

const CredentialUpdate = ({ user }) => {
  ///const accessToken = useSelector((state) => state.auth.token);
  const dispatch = useDispatch();

  const [id, setId] = useState(null);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  useEffect(() => {
    if (user) {
      setId(user.id);
      setUsername(user.username);
    }
  }, [user]);

  const handleSubmit = (e) => {
    e.preventDefault();
    const payload = {
      username,
      password
    };
    dispatch(updateUserCredential({ id, payload }));
    // id
    //   ? dispatch(updateUserData({ accessToken, id, payload }))
    //   : dispatch(postUserPost(userData, id, JSON.stringify(formPost)));
    console.log("crea/aggiorna utente");
  };
  return (
    <>
      <div
        className="modal fade modal-lg"
        id="updateCredentialForm"
        tabIndex="-1"
        aria-labelledby="updateCredentialForm"
        aria-hidden="true"
      >
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h1 className="modal-title fs-5" id="exampleModalLabel">
                Crea/Modifica credenziali
              </h1>
              <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div className="modal-body">
              <div className="container-fluid">
                <form className="needs-validation" novalidate onSubmit={handleSubmit}>
                  <div className="mb-3">
                    <label htmlFor="username" className="form-label">
                      Nome utente
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      id="username"
                      value={username}
                      onChange={(e) => setUsername(e.target.value)}
                      required
                    ></input>
                    <div className="valid-feedback">Looks good!</div>
                  </div>
                  <div className="mb-3">
                    <label htmlFor="password" className="form-label">
                      Nuova Password
                    </label>
                    <input
                      type="password"
                      className="form-control"
                      id="password"
                      value={password}
                      required
                      onChange={(e) => setPassword(e.target.value)}
                    ></input>
                    <div className="valid-feedback">Looks good!</div>
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
                data-bs-target="#confirmUserCredentialChange"
              >
                Salva
              </button>
            </div>
          </div>
        </div>
      </div>
      <div
        className="modal fade"
        id="confirmUserCredentialChange"
        tabIndex="-1"
        aria-labelledby="confirmUserCredentialChange"
        aria-hidden="true"
      >
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h1 className="modal-title fs-5">Conferma operazione</h1>
              <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div className="modal-body">Le tue credenziali verranno aggiornate!</div>
            <div className="modal-footer">
              <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">
                Annulla
              </button>
              <button type="button" className="btn btn-primary" data-bs-dismiss="modal" onClick={handleSubmit}>
                Conferma
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};
export default CredentialUpdate;
