import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { createUser, updateUser, deleteUser } from "../../redux/action/users";
import { updateAuthUser, deleteAuthUser } from "../../redux/action/auth";

const EditUser = ({ user, profile }) => {
  const roles = useSelector((state) => state.roles.available);
  const dispatch = useDispatch();

  const [id, setId] = useState(null);
  const [name, setName] = useState("");
  const [surname, setSurname] = useState("");
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [roleId, setRoleId] = useState("");
  const [save, setSave] = useState(false);

  useEffect(() => {
    if (user) {
      setId(user.id);
      setName(user.name);
      setSurname(user.surname);
      //setUsername(user.username);
      setEmail(user.email);
      setRoleId(user.roles[0].id);
      setSave(true);
    }
  }, [user]);

  const handleDelete = () => {
    id ? dispatch(deleteAuthUser({ id })) : dispatch(deleteUser({ id }));
    //    dispatch(deleteUser({ id }));
  };
  const handleSubmit = (e) => {
    e.preventDefault();
    const payload = id ? { name, surname, email, roleId } : { name, surname, username, email, password, roleId };
    if (profile) {
      dispatch(updateAuthUser({ id, payload }));
    } else {
      id ? dispatch(updateUser({ id, payload })) : dispatch(createUser({ id, payload }));
    }
    console.log("crea/aggiorna utente");
  };
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
                  </div>
                  <div className="mb-3">
                    <label htmlFor="surName" className="form-label">
                      Cognome
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      id="surName"
                      value={surname}
                      onChange={(e) => setSurname(e.target.value)}
                      required
                    ></input>
                    <div className="valid-feedback">Looks good!</div>
                  </div>
                  <div className="mb-3">
                    <label htmlFor="email" className="form-label">
                      E-mail
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      id="email"
                      value={email}
                      required
                      onChange={(e) => setEmail(e.target.value)}
                    ></input>
                    <div className="valid-feedback">Looks good!</div>
                  </div>
                  {!user && (
                    <>
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
                          Password
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
                    </>
                  )}
                  {roles && user.roles.find((r) => r.name === "ADMIN") && (
                    <div className="mb-3">
                      <label htmlFor="selectRole" className="form-label">
                        Imposta ruolo
                      </label>
                      <select
                        className="form-select"
                        id="selectRole"
                        value={roleId}
                        onChange={(e) => setRoleId(e.target.value)}
                      >
                        {roles.content &&
                          roles.content.map((role, i) => (
                            <option key={role.id} value={role.id}>
                              {role.name}
                            </option>
                          ))}
                      </select>
                    </div>
                  )}
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
                onClick={() => setSave(true)}
              >
                {id ? "Modifica" : "Salva"}
              </button>
              <button
                type="button"
                className="btn btn-danger"
                data-bs-toggle="modal"
                data-bs-target="#confirmUserEdit"
                onClick={() => setSave(false)}
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
            <div className="modal-body">Il tuo utente verrà {save ? (id ? "Aggiornato" : "Salvata") : "Eliminata"}</div>
            <div className="modal-footer">
              <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">
                Annulla
              </button>
              <button
                type="button"
                className="btn btn-primary"
                data-bs-dismiss="modal"
                onClick={save ? handleSubmit : handleDelete}
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
