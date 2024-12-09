// // src/api.js
// import axios from "axios";

// // Create an axios instance
// const axiosInstance = axios.create({
//   baseURL: "http://localhost/8082/api/tasks", // Backend base URL
//   headers: {
//     "Content-Type": "application/json",
//   },
// });

// // Add Authorization Token to every request
// axiosInstance.interceptors.request.use(
//   (config) => {
//     const token = localStorage.getItem("token"); // Assuming JWT token is saved in localStorage
//     if (token) {
//       config.headers["Authorization"] = `Bearer ${token}`;
//     }
//     return config;
//   },
//   (error) => Promise.reject(error)
// );

// export default axiosInstance;
