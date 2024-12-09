// src/components/PrivateRoute.js
import React from "react";
import { Navigate } from "react-router-dom";

const PrivateRoute = ({ element }) => {
  const isAuthenticated = localStorage.getItem("jwt");

  // If there's no jwt token, redirect to the sign-in page
  return isAuthenticated ? element : <Navigate to="/signin" />;
};

export default PrivateRoute;
