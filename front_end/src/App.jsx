import { BrowserRouter, Route, Routes } from "react-router-dom";
import React, { useState, useEffect } from "react";
import Home from "./components/Home";
import Navigation from "./components/Navigation";

function App() {
  const defaultDark = window.matchMedia("(prefers-color-scheme: dark)").matches;
  console.log(window.matchMedia("(prefers-color-scheme: dark)").matches);
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

    const socket = new WebSocket("ws://localhost:5080/sensorevent");
    socket.addEventListener("open", function (event) {
      //console.log("WebSocket connection opened:", event);
    });
    socket.addEventListener("message", function (event) {
      //console.log("WebSocket message received:", event.data);
    });
    socket.addEventListener("close", function (event) {
      //console.log("WebSocket connection closed:", event);
    });
    socket.addEventListener("error", function (event) {
      //console.log("WebSocket error:", event);
    });
  }, []);
  return (
    <>
      <Navigation />
      <div className="container-fluid vh-100">
        <div className="row h-100">
          <div className="col-3"></div>
          <div className="col">
            <BrowserRouter>
              {/* <SiteNav /> */}
              <Routes>
                <Route path="/" element={<Home />} />
                {/* <Route path="/profile" element={<Profile />} />
        <Route path="/experience" element={<Experiences />} />
        <Route path="/posts" element={<Posts />} />
        <Route path="/jobs" element={<Jobs />} />
        <Route path="/currentjob/:id" element={<Jobs />} /> */}
              </Routes>
            </BrowserRouter>
          </div>
        </div>
      </div>
    </>
  );
}

export default App;
