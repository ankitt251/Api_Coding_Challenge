import React, { useState } from "react";
import axios from "axios";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Alert from "@mui/material/Alert";
import { Card } from "@mui/material";
import { useNavigate } from "react-router";

const SignIn = () => {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

  const navigate = useNavigate();

  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setSuccessMessage("");
    setErrorMessage("");

    try {
      const response = await axios.post(
        "http://localhost:8082/api/user/signin",
        formData
      );
      setSuccessMessage("Login successful!");

      localStorage.setItem("jwt", response.data.jwt);
      setFormData({ email: "", password: "" });
      navigate("/task");
    } catch (error) {
      setErrorMessage("Invalid email or password. Please try again.");
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-[#101820] text-[#FEE715]">
      <Card className="p-10 space-y-5">
        <div className="w-full max-w-md p-6 bg-white rounded-lg shadow-lg">
          <h2 className="text-2xl font-bold mb-4 text-center text-[#101820]">
            Log In
          </h2>

          {successMessage && (
            <Alert severity="success" className="mb-4">
              {successMessage}
            </Alert>
          )}
          {errorMessage && (
            <Alert severity="error" className="mb-4">
              {errorMessage}
            </Alert>
          )}

          <form onSubmit={handleSubmit} className="space-y-4">
            <TextField
              label="Email"
              type="email"
              name="email"
              fullWidth
              required
              value={formData.email}
              onChange={handleInputChange}
              InputProps={{
                style: { color: "#101820" },
              }}
              InputLabelProps={{
                style: { color: "#101820" },
              }}
              sx={{
                "& .MuiOutlinedInput-root": {
                  "& fieldset": { borderColor: "#101820" },
                  "&:hover fieldset": { borderColor: "#FEE715" },
                  "&.Mui-focused fieldset": { borderColor: "#FEE715" },
                },
              }}
            />

            <TextField
              label="Password"
              type="password"
              name="password"
              fullWidth
              required
              value={formData.password}
              onChange={handleInputChange}
              InputProps={{
                style: { color: "#101820" },
              }}
              InputLabelProps={{
                style: { color: "#101820" },
              }}
              sx={{
                "& .MuiOutlinedInput-root": {
                  "& fieldset": { borderColor: "#101820" },
                  "&:hover fieldset": { borderColor: "#FEE715" },
                  "&.Mui-focused fieldset": { borderColor: "#FEE715" },
                },
              }}
            />

            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{
                backgroundColor: "#FEE715",
                color: "#101820",
                "&:hover": { backgroundColor: "#ffd700" },
              }}
            >
              Log In
            </Button>
          </form>

          <p className="mt-4 text-center text-sm text-[#101820]">
            Don’t have an account?{" "}
            <a
              href="/signup"
              className="font-bold text-[#FEE715] hover:underline"
            >
              Sign up
            </a>
          </p>
        </div>
      </Card>
    </div>
  );
};

export default SignIn;
