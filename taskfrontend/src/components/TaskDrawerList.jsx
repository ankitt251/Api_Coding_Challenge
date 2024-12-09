import React from "react";
import DrawList from "./DrawList";

const menu = [
  {
    name: "Dashboard",
    path: "/task",
  },
  {
    name: "Create Task",
    path: "/task/create",
  },
  {
    name: "Update Task",
    path: "/task/update",
  },
  {
    name: "Logout",
    path: "/logout",
  },
];

const TaskDrawerList = ({ toggleDrawer }) => {
  return (
    <div>
      <DrawList menu={menu} toggleDrawer={toggleDrawer} />
    </div>
  );
};

export default TaskDrawerList;