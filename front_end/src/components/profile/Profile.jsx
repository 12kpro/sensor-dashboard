import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import SensorTable from "../table/SensorTable";
import EditUser from "../users/EditUser";
import { useSelector } from "react-redux";
import CredentialUpdate from "../users/CredentialUpdate";

const Profile = () => {
  const userData = useSelector((state) => state.auth.userData);
  return (
    <div className="row">
      <div className="col-12 col-lg-8 offset-lg-2 pb-4">
        <div className="card flex-fill">
          <div className="card-header  d-flex justify-content-between align-items-center">
            <h5 className="card-title mb-0">Dettagli profilo</h5>
            <div class="btn-group">
              <button
                className="btn"
                id="bd-theme"
                type="button"
                aria-expanded="true"
                aria-label="Toggle theme (auto)"
                data-bs-toggle="modal"
                data-bs-target="#UserEditForm"
              >
                <i className="bi bi-pencil"></i>
              </button>
              <button
                className="btn"
                id="changeUserCredential"
                type="button"
                aria-expanded="true"
                aria-label="Toggle theme (auto)"
                data-bs-toggle="modal"
                data-bs-target="#updateCredentialForm"
              >
                <i className="bi bi-key"></i>
              </button>
            </div>
          </div>
          <div className="card-body">
            <div className="row row-cols-2 row-cols-lg-4">
              <div className="col">
                <h6>Nome</h6>
                <p className="card-text fs-5">{userData.name}</p>
              </div>
              <div className="col">
                <h6>Cognome</h6>
                <p className="card-text fs-5">{userData.surname}</p>
              </div>
              <div className="col">
                <h6>Nome utente</h6>
                <p className="card-text fs-5">{userData.username}</p>
              </div>
              <div className="col">
                <h6>E-mail</h6>
                <p className="card-text fs-5">{userData.email}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className="col-12 col-lg-8 offset-lg-2 col-xxl-9 d-flex">
        <SensorTable />
      </div>
      <EditUser user={userData} profile />
      <CredentialUpdate user={userData} />
    </div>
  );
};
export default Profile;
