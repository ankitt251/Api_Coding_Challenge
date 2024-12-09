import React from "react";
import { Routes, Route } from "react-router-dom";
import TaskDashboard from "../Pages/Task/TaskDashboard";
import TaskDetailPage from "../Pages/Task/TaskDetailPage";
import TaskForm from "../Pages/Task/TaskForm";

const TaskRoutes = () => {
  return (
    <Routes>
      <Route path="/" element={<TaskDashboard />} />
      <Route path=":id" element={<TaskDetailPage />} />{" "}
      <Route path="new" element={<TaskForm />} />{" "}
      <Route path="edit/:id" element={<TaskForm />} />
    </Routes>
  );
};

export default TaskRoutes;
