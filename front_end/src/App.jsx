import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import React, { useState, useEffect } from "react";

import Navigation from "./components/Navigation";
import Profile from "./components/profile/Profile";

import Users from "./components/users/Users";
import Sensors from "./components/sensors/Sensors";
import Login from "./components/users/Login";
import Dashboard from "./components/dashboard/Dashboard";
import { useDispatch, useSelector } from "react-redux";
import { fetchUserData } from "./redux/action/auth";

function App() {
  const accessToken = useSelector((state) => state.auth.token);
  const dispatch = useDispatch();

  useEffect(() => {
    if (accessToken) {
      dispatch(fetchUserData());
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
      <main className="container-fluid vh-100 px-5 position-relative">
        <Routes>
          <Route path="/login" element={<Login />} />

          <Route path="/" element={accessToken ? <Dashboard /> : <Navigate to="/login" />} />
          <Route path="/profile" element={accessToken ? <Profile /> : <Navigate to="/login" />} />
          <Route path="/users" element={accessToken ? <Users /> : <Navigate to="/login" />} />
          <Route path="/sensors" element={accessToken ? <Sensors /> : <Navigate to="/login" />} />
          {/* <Route path="*" element={<NotFound />} /> */}
        </Routes>
      </main>
    </BrowserRouter>
  );
}

export default App;
