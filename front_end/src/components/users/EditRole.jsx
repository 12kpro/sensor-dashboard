const EditRole = () => {
  const handleDelete = () => {};
  const handleSubmit = (e) => {};
  return (
    <div className="modal modal-sm" id="editRole">
      <div className="modal-dialog">
        <div className="modal-content">
          <div className="modal-header">
            <h5 className="modal-title">Aggiungi/Rimuovi Ruolo</h5>
            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div className="modal-body">
            <table className="table table-hover my-0 text-center">
              <thead>
                <tr>
                  <th>Nome</th>
                  <th className="d-none d-md-table-cell">Azioni</th>
                </tr>
              </thead>
              <tbody>
                {[...Array(3)].map((_, i) => (
                  <tr key={i}>
                    <td>Admin</td>
                    <td className="d-none d-md-table-cell">
                      <button
                        className="btn"
                        id="bd-tdeme"
                        type="button"
                        aria-expanded="true"
                        aria-label="Toggle tdeme (auto)"
                        data-bs-toggle="tooltip"
                        data-bs-title="Rimuovi"
                      >
                        <i className="bi bi-trash3"></i>
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
          <div className="modal-footer">
            <form>
              <div className="input-group mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Nome Ruolo"
                  aria-label="Nome Ruolo"
                  aria-describedby="button-addon2"
                ></input>
                <button
                  className="btn btn-outline-secondary"
                  type="button"
                  id="button-addon2"
                  data-bs-toggle="tooltip"
                  data-bs-title="Aggiungi"
                >
                  <i className="bi bi-check2"></i>
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};
export default EditRole;
