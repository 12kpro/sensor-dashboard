import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { createUser, updateUser, deleteUser } from "../../redux/action/users";
import { updateAuthUser, deleteAuthUser } from "../../redux/action/auth";
import ConfirmModal from "../utils/ConfirmModal";

const EditUser = ({ user, profile }) => {
  const roles = useSelector((state) => state.roles.available);
  const authUser = useSelector((state) => state.auth.userData);
  const dispatch = useDispatch();

  const [id, setId] = useState(null);
  const [name, setName] = useState("");
  const [surname, setSurname] = useState("");
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [roleId, setRoleId] = useState("");
  const [action, setAction] = useState("create");

  useEffect(() => {
    setId(user ? user.id : "");
    setName(user ? user.name : "");
    setSurname(user ? user.surname : "");
    setUsername(user ? user.username : "");
    setEmail(user ? user.email : "");
    setRoleId(user ? user.roles[0].id : "");
    setAction(user ? "update" : "create");
  }, [user]);

  const handleDelete = () => {
    id && profile ? dispatch(deleteAuthUser({ id })) : dispatch(deleteUser({ id }));
  };
  const handleSubmit = () => {
    const payload = id ? { name, surname, email, roleId } : { name, surname, username, email, password, roleId };
    if (profile) {
      dispatch(updateAuthUser({ id, payload }));
    } else {
      id ? dispatch(updateUser({ id, payload })) : dispatch(createUser({ payload }));
    }
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
                {id ? "Modifica" : "Crea"} utente
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
                  {roles && authUser.roles.find((r) => r.name === "ADMIN") && (
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
                        <option>--Seleziona un ruolo --</option>
                        {roles.length > 1 &&
                          roles.map((role, i) => (
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
                data-bs-target="#confirmUserAction"
                // onClick={() => setSave(true)}
              >
                {id ? "Modifica" : "Salva"}
              </button>
              {user && (
                <button
                  type="button"
                  className="btn btn-danger"
                  data-bs-toggle="modal"
                  data-bs-target="#confirmUserAction"
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
        id="confirmUserAction"
        handleSubmit={handleSubmit}
        handleDelete={handleDelete}
        action={action}
        actionTxt={{
          update: "L'utente verrà Aggiornato",
          create: "L'utente verrà Salvato",
          delete: "L'utente verrà Cancellato"
        }}
      />
    </>
  );
};
export default EditUser;
