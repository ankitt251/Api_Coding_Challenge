import React from "react";
import Button from "@mui/material/Button";
import { useNavigate } from "react-router";

const Home = () => {
  const navigate = useNavigate();

  return (
    <div className="min-h-screen flex flex-col items-center justify-center">
      {/* Header Section */}
      <header className="text-center mb-10">
        <h1 className="text-4xl font-bold">Welcome to Task Manager</h1>
        <p className="text-lg mt-2">Organize your tasks efficiently!</p>
      </header>

      {/* Main Section */}
      <main className="flex flex-col md:flex-row items-center justify-around w-11/12">
        {/* Image Section */}
        <div className="flex-shrink-0 mb-6 md:mb-0">
          <img
            src="https://projectsly.com/images/blog/employee-task-management-system.png?v=1686553999071005322"
            alt="Task Management Workspace"
            className="rounded-lg shadow-lg max-w-[50%] h-[50%]"
          />
        </div>

        {/* Buttons Section */}
        <div className="flex flex-col items-center md:items-start space-y-4">
          <Button
            onClick={() => navigate("/signin")}
            variant="contained"
            size="large"
            sx={{
              backgroundColor: "#FEE715",
              color: "#101820",
              gap: "5",
              "&:hover": { backgroundColor: "#ffd700" },
            }}
          >
            Login
          </Button>
          <Button
            onClick={() => navigate("/signup")}
            variant="outlined"
            size="large"
            sx={{
              borderColor: "#FEE715",
              color: "#FEE715",
              "&:hover": { borderColor: "#ffd700", color: "#ffd700" },
            }}
          >
            Sign Up
          </Button>
        </div>
      </main>

      {/* Footer Section */}
      <footer className="mt-12 text-center">
        <p className="text-sm">© 2024 Task Manager. All Rights Reserved.</p>
      </footer>
    </div>
  );
};

export default Home;
