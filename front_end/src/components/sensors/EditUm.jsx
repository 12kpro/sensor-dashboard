import { useDispatch, useSelector } from "react-redux";
import ConfirmModal from "../utils/ConfirmModal";
import { useEffect, useState } from "react";
import { createUm, deleteUm } from "../../redux/action/um";
const EditUm = () => {
  const dispatch = useDispatch();
  const ums = useSelector((state) => state.um.available);
  const [action, setAction] = useState("create");
  const [id, setId] = useState("");
  const [unit, setUnit] = useState("");
  const [description, setDesciption] = useState("");

  useEffect(() => {
    setId("");
    setUnit("");
    setDesciption("");
  }, [ums]);

  const handleDelete = () => {
    console.log(id);
    dispatch(deleteUm({ id }));
  };
  const handleSubmit = () => {
    const payload = { unit, description };
    dispatch(createUm({ payload }));
    // id ? dispatch(updateSensor({ id, payload })) : dispatch(createSensor({ payload }));
  };

  return (
    <>
      <div className="modal" id="editUm" data-bs-backdrop="static">
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title">Aggiungi/Rimuovi Unità</h5>
              <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div className="modal-body">
              <table className="table table-hover my-0 text-center">
                <thead>
                  <tr>
                    <th>Unità</th>
                    <th>Descrizione</th>
                    <th className="d-none d-md-table-cell">Azioni</th>
                  </tr>
                </thead>
                <tbody>
                  {ums.length > 0 &&
                    ums.map((um) => (
                      <tr key={um.id}>
                        <td>{um.unit}</td>
                        <td>{um.description}</td>
                        <td className="d-none d-md-table-cell">
                          <button
                            className="btn"
                            id="bd-tdeme"
                            type="button"
                            data-bs-toggle="modal"
                            data-bs-target="#confirmUmAction"
                            onClick={() => {
                              setId(um.id);
                              setAction("delete");
                            }}
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
              <form className="row">
                <div class="col-md-5">
                  <input
                    type="text"
                    className="form-control"
                    placeholder="Nome unità"
                    aria-label="Nome unità"
                    aria-describedby="button-addon2"
                    value={unit}
                    required
                    onChange={(e) => setUnit(e.target.value)}
                  ></input>
                </div>
                <div class="col-md-5">
                  <input
                    type="text"
                    class="form-control"
                    placeholder="Descrizione"
                    id="description"
                    value={description}
                    required
                    onChange={(e) => {
                      setDesciption(e.target.value);
                    }}
                  ></input>
                </div>
                <div class="col-md-2">
                  <button
                    className="btn btn-outline-secondary"
                    type="button"
                    id="button-addon2"
                    data-bs-toggle="modal"
                    data-bs-target="#confirmUmAction"
                    onClick={() => setAction("create")}
                    disabled={!(unit && description)}
                  >
                    <i className="bi bi-check2"></i>
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
      <ConfirmModal
        id="confirmUmAction"
        handleSubmit={handleSubmit}
        handleDelete={handleDelete}
        action={action}
        actionTxt={{
          update: "L'unità verrà Aggiornata",
          create: "L'unità verrà Salvata",
          delete: "L'unità e tutti i sensori collegati verranno Rimossi"
        }}
      />
    </>
  );
};
export default EditUm;
