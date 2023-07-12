import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link } from "react-router-dom";
import { signOut } from "../redux/action/auth";

const Navigation = () => {
  const defaultDark = window.matchMedia("(prefers-color-scheme: dark)").matches;
  const [theme, setTheme] = useState(defaultDark);
  const accessToken = useSelector((state) => state.auth.token);
  const userData = useSelector((state) => state.auth.userData);

  const dispatch = useDispatch();

  const switchTheme = () => {
    const newTheme = theme === "light" ? "dark" : "light";
    setTheme(newTheme);
  };

  useEffect(() => {
    document.documentElement.setAttribute("data-bs-theme", theme);
  }, [theme]);

  useEffect(() => {
    if (defaultDark) {
      setTheme("dark");
    }
  }, []);
  return (
    <header className="position-fixed vw-100">
      <nav className="navbar navbar-expand-lg">
        <div className="container-fluid px-5">
          <Link className="navbar-brand" to="/">
            <img src="assets/images/netflix_logo.png" height="40px" />
          </Link>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav ms-auto mb-2 mb-lg-0 ">
              <li className="nav-item">
                <button
                  className="btn nav-link link-secondary"
                  id="bd-theme"
                  type="button"
                  aria-expanded="true"
                  aria-label="Toggle theme (auto)"
                  onClick={() => switchTheme()}
                >
                  <i className={"bi " + (theme == "dark" ? "bi-moon-stars" : "bi-sun")}></i>
                </button>
              </li>
              {/* <li className="nav-item">
                <Link className="nav-link link-secondary" to="/">
                  <i className="bi bi-bell"></i>
                </Link>
              </li> */}
              {accessToken && (
                <li className="nav-item dropdown">
                  <Link
                    className="nav-link dropdown-toggle link-secondary pe-0"
                    to="/"
                    role="button"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
                  >
                    <i className="bi bi-person-circle"></i>
                  </Link>
                  <ul className="dropdown-menu dropdown-menu-end ">
                    <li>
                      <Link className="dropdown-item" to="/profile">
                        Profilo
                      </Link>
                    </li>
                    <li>
                      <Link className="dropdown-item" to="/" onClick={() => dispatch(signOut())}>
                        Esci
                      </Link>
                    </li>

                    {userData && userData.roles.find((r) => r.name === "ADMIN") && (
                      <>
                        <li>
                          <hr className="dropdown-divider" />
                        </li>
                        <li>
                          <Link className="dropdown-item" to="/sensors">
                            Sensori
                          </Link>
                        </li>
                        <li>
                          <Link className="dropdown-item" to="/users">
                            Utenti
                          </Link>
                        </li>
                      </>
                    )}
                  </ul>
                </li>
              )}
            </ul>
          </div>
        </div>
      </nav>
    </header>
  );
};
export default Navigation;
