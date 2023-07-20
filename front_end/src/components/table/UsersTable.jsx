import { useSelector } from "react-redux";
import Pager from "../pager/Pager";
import EditUser from "../users/EditUser";
import UserRow from "./rows/UserRow";
import { useState } from "react";

const UsersTable = ({ edit }) => {
  const users = useSelector((state) => state.users.available);
  const authUser = useSelector((state) => state.auth.userData);
  const [user, setUser] = useState(null);
  return (
    <div className="card flex-fill">
      <div className="card-header  d-flex justify-content-between align-items-center">
        <h5 className="card-title mb-0">Utenti</h5>
        {edit && (
          <div className="btn-group" role="group" aria-label="Basic example">
            <button
              class="btn"
              id="createUser"
              type="button"
              aria-expanded="true"
              aria-label="Toggle theme (auto)"
              data-bs-toggle="modal"
              data-bs-target="#UserEditForm"
              onClick={() => setUser(null)}
            >
              <i class="bi bi-person-add"></i>
            </button>
            {/* <button
              className="btn"
              id="bd-theme"
              type="button"
              aria-expanded="true"
              aria-label="Toggle theme (auto)"
              data-bs-toggle="modal"
              data-bs-target="#editRole"
            >
              <i className="bi bi-people"></i>
            </button> */}
          </div>
        )}
      </div>
      <table className="table table-hover my-0 text-center">
        <thead>
          <tr>
            <th>Nome</th>
            <th className="">Cognome</th>
            <th className="d-none d-lg-table-cell">Username</th>
            <th className="d-none d-md-table-cell">E-mail</th>
            <th className="d-none d-lg-table-cell">Ruolo</th>
            <th>Azioni</th>
          </tr>
        </thead>
        <tbody>
          {users.content.length > 0 &&
            users.content
              .filter((user) => user.id !== authUser.id)
              .map((user) => <UserRow key={user.id} user={user} setUser={setUser} />)}
        </tbody>
      </table>
      {users.totalPages > 1 && (
        <div class="card-footer text-body-secondary">
          <Pager />
        </div>
      )}
      <EditUser user={user} />
    </div>
  );
};
export default UsersTable;
