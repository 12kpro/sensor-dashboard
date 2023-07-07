import { useEffect, useState } from "react";

const Navigation = () => {
  const defaultDark = window.matchMedia("(prefers-color-scheme: dark)").matches;
  const [theme, setTheme] = useState(defaultDark);

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
    <header className="mb-3">
      <nav className="navbar navbar-expand-lg">
        <div className="container-fluid px-5">
          <a className="navbar-brand" href="#">
            <img src="assets/images/netflix_logo.png" height="40px" />
          </a>
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
            {/* <ul className="navbar-nav me-auto mb-2 mb-lg-0 link-light">
              <li className="nav-item">
                <a className="nav-link link-secondary" aria-current="page" href="index.html">
                  Home
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link link-secondary" href="#">
                  TV Shows
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link link-secondary" href="#">
                  Movies
                </a>
              </li>
              <li className="nav-item" href="#">
                <a className="nav-link link-secondary">Recently Added</a>
              </li>
              <li className="nav-item" href="#">
                <a className="nav-link link-secondary">My List</a>
              </li>
            </ul> */}
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
              <li className="nav-item">
                <a className="nav-link link-secondary" href="#">
                  <i className="bi bi-bell"></i>
                </a>
              </li>
              <li className="nav-item dropdown">
                <a
                  className="nav-link dropdown-toggle link-secondary pe-0"
                  href="#"
                  role="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                >
                  <img className="img-fluid" src="assets/images/avatar.png" height="20px" width="20px" />
                </a>
                <ul className="dropdown-menu dropdown-menu-end ">
                  <li>
                    <a className="dropdown-item" href="profile.html">
                      Profilo
                    </a>
                  </li>
                  <li>
                    <hr className="dropdown-divider" />
                  </li>
                  <li>
                    <a className="dropdown-item" href="settings.html">
                      Impostazioni
                    </a>
                  </li>
                  <li>
                    <a className="dropdown-item" href="settings.html">
                      Utenti
                    </a>
                  </li>
                </ul>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </header>
  );
};
export default Navigation;
