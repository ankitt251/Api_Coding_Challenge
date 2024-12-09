// src/App.js
import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./Pages/Home/Home";
import SignUp from "./User/SignUp";
import SignIn from "./User/SignIn";
import TaskDashboard from "./Pages/Task/TaskDashboard";
import PrivateRoute from "./Routes/PrivateRoute";

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/signin" element={<SignIn />} />

        {/* Protect /task route with PrivateRoute */}
        <Route
          path="/task/*"
          element={<PrivateRoute element={<TaskDashboard />} />}
        />
      </Routes>
    </Router>
  );
};

export default App;
