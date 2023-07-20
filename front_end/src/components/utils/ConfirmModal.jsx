const ConfirmModal = ({ id, action, actionTxt, handleSubmit, handleDelete }) => {
  const handleAction = () => {
    if (action === "update" || action === "create") {
      handleSubmit();
    } else {
      handleDelete();
    }
  };
  return (
    <div className="modal fade" id={id} tabIndex="-1" aria-labelledby="confirmAction" aria-hidden="true">
      <div className="modal-dialog">
        <div className="modal-content">
          <div className="modal-header">
            <h1 className="modal-title fs-5">Conferma operazione</h1>
            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div className="modal-body">
            {actionTxt[action]}
            {/* Il tuo utente verrà {save ? (id ? "Aggiornato" : "Salvata") : "Eliminata"} */}
          </div>
          <div className="modal-footer">
            <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">
              Annulla
            </button>
            <button type="button" className="btn btn-primary" data-bs-dismiss="modal" onClick={handleAction}>
              Conferma
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};
export default ConfirmModal;
