import { BrowserRouter, Route, Routes } from "react-router-dom";
import React, { useState, useEffect } from "react";
import Home from "./components/Home";
import Navigation from "./components/Navigation";
import Profile from "./components/profile/Profile";

import Settings from "./components/sensors/Sensors";
import Users from "./components/users/Users";
import Sensors from "./components/sensors/Sensors";

function App() {
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
          {/*<Route path="/posts" element={<Posts />} />
        <Route path="/jobs" element={<Jobs />} />
        <Route path="/currentjob/:id" element={<Jobs />} /> */}
        </Routes>
      </main>
    </BrowserRouter>
  );
}

export default App;
