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
    <nav className="navbar fixed-top navbar-expand bg-body-tertiary">
      <div className="container-fluid px-5">
        <Link className="navbar-brand d-flex align-items-center" to="/">
          <svg width="30" viewBox="0 0 70 70" className="css-1j8o68f">
            <defs id="SvgjsDefs1405"></defs>
            <g
              id="SvgjsG1406"
              featurekey="HKaMnE-0"
              transform="matrix(0.638613296841145,0,0,0.638613296841145,-2.2820161934086,9.549663587638271)"
              fill="#ffffff"
            >
              <path
                xmlns="http://www.w3.org/2000/svg"
                // style="font-size:medium;font-style:normal;font-variant:normal;font-weight:normal;font-stretch:normal;text-indent:0;text-align:start;text-decoration:none;line-height:normal;letter-spacing:normal;word-spacing:normal;text-transform:none;direction:ltr;block-progression:tb;writing-mode:lr-tb;text-anchor:start;baseline-shift:baseline;opacity:1;color:;fill:#ffffff;fill-opacity:1;stroke:none;stroke-width:2;marker:none;visibility:visible;display:inline;overflow:visible;enable-background:accumulate;font-family:Sans;-inkscape-font-specification:Sans"
                d="M 41.84375 6.96875 C 39.050863 6.96875 36.75 9.2781131 36.75 12.0625 C 36.75 14.508767 38.517986 16.564708 40.84375 17.03125 L 40.84375 31.375 L 32.34375 31.375 A 1.0001 1.0001 0 0 0 31.34375 32.375 L 31.34375 40.875 L 19.0625 40.875 L 19.0625 25.15625 C 21.389074 24.690357 23.1875 22.634346 23.1875 20.1875 C 23.1875 17.403113 20.855387 15.125 18.0625 15.125 C 15.269613 15.125 12.96875 17.403113 12.96875 20.1875 C 12.96875 22.623839 14.750624 24.678664 17.0625 25.15625 L 17.0625 41.875 A 1.0001 1.0001 0 0 0 18.0625 42.875 L 31.34375 42.875 L 31.34375 57.125 L 18.0625 57.125 A 1.0001 1.0001 0 0 0 17.96875 57.125 A 1.0001 1.0001 0 0 0 17.0625 58.125 L 17.0625 74.84375 C 14.750624 75.321336 12.96875 77.376161 12.96875 79.8125 C 12.96875 82.596887 15.269613 84.875 18.0625 84.875 C 20.855387 84.875 23.1875 82.596887 23.1875 79.8125 C 23.1875 77.365654 21.389074 75.309643 19.0625 74.84375 L 19.0625 59.125 L 31.34375 59.125 L 31.34375 67.625 A 1.0001 1.0001 0 0 0 32.34375 68.625 L 40.84375 68.625 L 40.84375 82.96875 C 38.517986 83.435292 36.75 85.491233 36.75 87.9375 C 36.75 90.721887 39.050863 93.03125 41.84375 93.03125 C 44.636636 93.03125 46.9375 90.721887 46.9375 87.9375 C 46.9375 85.490653 45.170324 83.434643 42.84375 82.96875 L 42.84375 68.625 L 57.15625 68.625 L 57.15625 82.96875 C 54.829676 83.434643 53.0625 85.490653 53.0625 87.9375 C 53.0625 90.721887 55.363364 93.03125 58.15625 93.03125 C 60.949137 93.03125 63.25 90.721887 63.25 87.9375 C 63.25 85.491233 61.482014 83.435292 59.15625 82.96875 L 59.15625 68.625 L 67.65625 68.625 A 1.0001 1.0001 0 0 0 68.65625 67.625 L 68.65625 59.125 L 80.9375 59.125 L 80.9375 74.84375 C 78.610926 75.309643 76.8125 77.365654 76.8125 79.8125 C 76.8125 82.596887 79.144613 84.875 81.9375 84.875 C 84.730387 84.875 87.03125 82.596887 87.03125 79.8125 C 87.03125 77.376161 85.249376 75.321336 82.9375 74.84375 L 82.9375 58.125 A 1.0001 1.0001 0 0 0 81.9375 57.125 L 68.65625 57.125 L 68.65625 42.875 L 81.9375 42.875 A 1.0001 1.0001 0 0 0 82.9375 41.875 L 82.9375 25.15625 C 85.249376 24.678664 87.03125 22.623839 87.03125 20.1875 C 87.03125 17.403113 84.730387 15.125 81.9375 15.125 C 79.144613 15.125 76.8125 17.403113 76.8125 20.1875 C 76.8125 22.634346 78.610926 24.690357 80.9375 25.15625 L 80.9375 40.875 L 68.65625 40.875 L 68.65625 32.375 A 1.0001 1.0001 0 0 0 67.65625 31.375 L 59.15625 31.375 L 59.15625 17.03125 C 61.482014 16.564708 63.25 14.508767 63.25 12.0625 C 63.25 9.2781131 60.949137 6.96875 58.15625 6.96875 C 55.363364 6.96875 53.0625 9.2781131 53.0625 12.0625 C 53.0625 14.509347 54.829676 16.565357 57.15625 17.03125 L 57.15625 31.375 L 42.84375 31.375 L 42.84375 17.03125 C 45.170324 16.565357 46.9375 14.509347 46.9375 12.0625 C 46.9375 9.2781131 44.636636 6.96875 41.84375 6.96875 z M 41.84375 9.03125 C 43.554107 9.03125 44.90625 10.357348 44.90625 12.0625 C 44.90625 13.767653 43.554107 15.125 41.84375 15.125 C 40.133392 15.125 38.78125 13.767653 38.78125 12.0625 C 38.78125 10.357348 40.133392 9.03125 41.84375 9.03125 z M 58.15625 9.03125 C 59.866608 9.03125 61.21875 10.357348 61.21875 12.0625 C 61.21875 13.767653 59.866608 15.125 58.15625 15.125 C 56.445893 15.125 55.09375 13.767653 55.09375 12.0625 C 55.09375 10.357348 56.445893 9.03125 58.15625 9.03125 z M 18.0625 17.15625 C 19.772857 17.15625 21.125 18.482348 21.125 20.1875 C 21.125 21.892653 19.772857 23.25 18.0625 23.25 C 16.352142 23.25 15.03125 21.892653 15.03125 20.1875 C 15.03125 18.482348 16.352142 17.15625 18.0625 17.15625 z M 81.9375 17.15625 C 83.647858 17.15625 84.96875 18.482348 84.96875 20.1875 C 84.96875 21.892653 83.647858 23.25 81.9375 23.25 C 80.227143 23.25 78.875 21.892653 78.875 20.1875 C 78.875 18.482348 80.227143 17.15625 81.9375 17.15625 z M 33.34375 33.375 L 66.65625 33.375 L 66.65625 66.625 L 33.34375 66.625 L 33.34375 33.375 z M 18.0625 76.75 C 19.772857 76.75 21.125 78.107347 21.125 79.8125 C 21.125 81.517652 19.772857 82.84375 18.0625 82.84375 C 16.352142 82.84375 15.03125 81.517652 15.03125 79.8125 C 15.03125 78.107347 16.352142 76.75 18.0625 76.75 z M 81.9375 76.75 C 83.647858 76.75 84.96875 78.107347 84.96875 79.8125 C 84.96875 81.517652 83.647858 82.84375 81.9375 82.84375 C 80.227143 82.84375 78.875 81.517652 78.875 79.8125 C 78.875 78.107347 80.227143 76.75 81.9375 76.75 z M 41.84375 84.875 C 43.554107 84.875 44.90625 86.232347 44.90625 87.9375 C 44.90625 89.642652 43.554107 90.96875 41.84375 90.96875 C 40.133392 90.96875 38.78125 89.642652 38.78125 87.9375 C 38.78125 86.232347 40.133392 84.875 41.84375 84.875 z M 58.15625 84.875 C 59.866608 84.875 61.21875 86.232347 61.21875 87.9375 C 61.21875 89.642652 59.866608 90.96875 58.15625 90.96875 C 56.445893 90.96875 55.09375 89.642652 55.09375 87.9375 C 55.09375 86.232347 56.445893 84.875 58.15625 84.875 z "
              ></path>
            </g>
          </svg>
          Sensors Dashboard
          {/* <img src="assets/images/netflix_logo.png" height="40px" /> */}
        </Link>
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
  );
};
export default Navigation;
