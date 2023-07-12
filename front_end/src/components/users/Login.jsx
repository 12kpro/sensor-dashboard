import { useDispatch, useSelector } from "react-redux";
import { login } from "../../redux/action/auth";
import { useState } from "react";

const Login = () => {
  const [username, setUserName] = useState("");
  const [password, setPassword] = useState("");
  const dispatch = useDispatch();
  const { token, loading } = useSelector((state) => state.auth);

  const handleLogin = (e) => {
    e.preventDefault();
    dispatch(login({ username, password }));
  };

  return (
    <div className="row vh-100">
      <div className="col-sm-10 col-md-8 col-lg-6 col-xl-5 mx-auto d-table h-100">
        <div className="d-table-cell align-middle">
          <div className="text-center mt-4">
            <h1 className="h2">Get started</h1>
            <p className="lead">Start creating the best possible user experience for you customers.</p>
          </div>

          <div className="card">
            <div className="card-body">
              <div className="m-sm-3">
                <form onSubmit={handleLogin}>
                  <div className="mb-3">
                    <label className="form-label">User Name</label>
                    <input
                      className="form-control form-control-lg"
                      type="text"
                      name="name"
                      placeholder="Inserisci il tuo username"
                      value={username}
                      onChange={(e) => setUserName(e.target.value)}
                    ></input>
                  </div>
                  <div className="mb-3">
                    <label className="form-label">Password</label>
                    <input
                      className="form-control form-control-lg"
                      type="password"
                      name="password"
                      placeholder="Inserisci la password"
                      value={password}
                      onChange={(e) => setPassword(e.target.value)}
                    ></input>
                  </div>
                  <div className="d-grid gap-2 mt-3">
                    {loading ? (
                      <div className="spinner-border text-success" role="status">
                        <span class="visually-hidden">Loading...</span>
                      </div>
                    ) : (
                      <button type="submit" name="Login">
                        Entra
                      </button>
                    )}
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
export default Login;
