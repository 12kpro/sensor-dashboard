import { BrowserRouter, Route, Routes } from "react-router-dom";
import React, { useState, useEffect } from "react";
import Home from "./components/Home";
import Navigation from "./components/Navigation";
import Profile from "./components/profile/Profile";

import Settings from "./components/sensors/Sensors";
import Users from "./components/users/Users";
import Sensors from "./components/sensors/Sensors";
import Login from "./components/users/Login";
import { useDispatch, useSelector } from "react-redux";
import { fetchUserData } from "./redux/action/auth";
import { fetchSensors } from "./redux/action/sensors";
import { fetchRoles } from "./redux/action/roles";
import { fetchUm } from "./redux/action/um";
import { fetchUsers } from "./redux/action/users";
function App() {
  const accessToken = useSelector((state) => state.auth.token);
  //const userData = useSelector((state) => state.auth.userData);
  const dispatch = useDispatch();

  useEffect(() => {
    if (accessToken) {
      dispatch(fetchUserData(accessToken));
      dispatch(fetchSensors(accessToken));
      dispatch(fetchRoles(accessToken));
      dispatch(fetchUm(accessToken));
      dispatch(fetchUsers(accessToken));
    }
  }, [accessToken]);
  // const socket = new WebSocket("ws://localhost:5080/sensorevent");
  // socket.addEventListener("open", function (event) {
  //   //console.log("WebSocket connection opened:", event);
  // });
  // socket.addEventListener("message", function (event) {
  //   //console.log("WebSocket message received:", event.data);
  // });
  // socket.addEventListener("close", function (event) {
  //   //console.log("WebSocket connection closed:", event);
  // });
  // socket.addEventListener("error", function (event) {
  //   //console.log("WebSocket error:", event);
  // });
  // }, []);
  return (
    <BrowserRouter>
      <Navigation />
      <main className="container-fluid vh-100">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/settings" element={<Settings />} />
          <Route path="/users" element={<Users />} />
          <Route path="/sensors" element={<Sensors />} />
          {/* <Route path="*" element={<NotFound />} /> */}
        </Routes>
      </main>
    </BrowserRouter>
  );
}

export default App;
