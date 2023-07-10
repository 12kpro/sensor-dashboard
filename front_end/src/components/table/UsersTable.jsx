import Pager from "../pager/Pager";
import EditRole from "../users/EditRole";
import EditUser from "../users/EditUser";
import UserRow from "./rows/UserRow";

const UsersTable = ({ edit }) => {
  return (
    <div className="card flex-fill">
      <div className="card-header  d-flex justify-content-between align-items-center">
        <h5 className="card-title mb-0">Utenti</h5>
        {edit && (
          <div className="btn-group" role="group" aria-label="Basic example">
            <button
              class="btn"
              id="bd-theme"
              type="button"
              aria-expanded="true"
              aria-label="Toggle theme (auto)"
              data-bs-toggle="modal"
              data-bs-target="#UserEditForm"
            >
              <i class="bi bi-person-add"></i>
            </button>
            <button
              className="btn"
              id="bd-theme"
              type="button"
              aria-expanded="true"
              aria-label="Toggle theme (auto)"
              data-bs-toggle="modal"
              data-bs-target="#editRole"
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
            <th className="d-none d-xl-table-cell">Cognome</th>
            <th className="d-none d-xl-table-cell">Username</th>
            <th className="d-none d-md-table-cell">E-mail</th>
            <th>Ruolo</th>
            <th className="d-none d-md-table-cell">Azioni</th>
          </tr>
        </thead>
        <tbody>
          {[...Array(10)].map((_, i) => (
            <UserRow key={i} edit={edit} />
          ))}
        </tbody>
      </table>
      <div class="card-footer text-body-secondary">
        <Pager />
      </div>
      <EditUser />
      <EditRole />
    </div>
  );
};
export default UsersTable;
