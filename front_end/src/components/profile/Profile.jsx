import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import SensorTable from "../table/SensorTable";
import EditUser from "../users/EditUser";

const Profile = () => {
  return (
    <div className="row">
      <div className="col-12 col-lg-8 offset-lg-2 pb-4">
        <div className="card flex-fill">
          <div className="card-header  d-flex justify-content-between align-items-center">
            <h5 className="card-title mb-0">Dettagli profilo</h5>
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
          </div>
          <div className="card-body">
            <div className="row row-cols-2 row-cols-lg-4">
              <p className="card-text">Nome</p>
              <p className="card-text">Cognome</p>
              <p className="card-text">Username</p>
              <p className="card-text">E-mail</p>
            </div>
          </div>
        </div>
      </div>
      <div className="col-12 col-lg-8 offset-lg-2 col-xxl-9 d-flex">
        <SensorTable />
      </div>
      <EditUser />
    </div>
  );
};
export default Profile;
