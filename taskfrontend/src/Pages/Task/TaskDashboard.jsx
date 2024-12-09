// src/Pages/Task/TaskDashboard.js
import React from "react";
import TaskDrawerList from "../../components/TaskDrawerList";
import TaskRoutes from "../../Routes/TaskRoutes";

const TaskDashboard = () => {
  const toggleDrawer = () => {};

  return (
    <div className="lg:flex lg:h-[90vh]">
      <section className="hidden lg:block h-full">
        <TaskDrawerList toggleDrawer={toggleDrawer} />
      </section>
      <section className="p-10 w-full lg:w-[80%] overflow-y-auto">
        {/* TaskRoutes will handle the nested routes */}
        <TaskRoutes />
      </section>
    </div>
  );
};

export default TaskDashboard;
