import React, { useState } from "react";
import axios from "axios";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Alert from "@mui/material/Alert";

const SignUp = () => {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

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
        "http://localhost:8082/api/user/signup",
        formData
      );
      setSuccessMessage("Sign-up successful! You can now log in.");
      setFormData({ email: "", password: "" }); // Clear form
    } catch (error) {
      setErrorMessage(
        "Error signing up. Please check your input or try again."
      );
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-[#101820] text-[#FEE715]">
      <div className="w-full max-w-md p-10 bg-white rounded-lg shadow-lg">
        <h2 className="text-2xl font-bold mb-4 text-center text-[#010304]">
          Sign Up
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
            helperText="Password must contain uppercase, lowercase, digit, and be at least 6 characters."
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
            Sign Up
          </Button>
        </form>

        <p className="mt-4 text-center text-sm text-[#101820]">
          Already have an account?{" "}
          <a
            href="/signin"
            className="font-bold text-[#FEE715] hover:underline"
          >
            Log in
          </a>
        </p>
      </div>
    </div>
  );
};

export default SignUp;
