import React from "react";
import { Button } from "@mui/material";
import { useNavigate } from "react-router-dom";

const LogoutButton = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("jwt");
    navigate("/signin");
  };

  return (
    <Button
      onClick={handleLogout}
      variant="contained"
      sx={{
        backgroundColor: "#FEE715",
        color: "#101820",
        "&:hover": { backgroundColor: "#ffd700" },
        marginTop: "20px",
        width: "100%",
      }}
    >
      Logout
    </Button>
  );
};

export default LogoutButton;
