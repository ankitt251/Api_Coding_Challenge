import React from "react";
import { ListItemIcon, ListItemText, ListItem, Divider } from "@mui/material";
import { useLocation, useNavigate } from "react-router";

const DrawList = ({ menu, toggleDrawer }) => {
  const location = useLocation();
  const navigate = useNavigate();

  const handleNavigation = (path) => {
    if (path !== location.pathname) {
      navigate(path); // Only navigate if the path is different
      toggleDrawer(false); // Close the drawer after navigation
    }
  };

  return (
    <div className="h-full">
      <div className="flex flex-col justify-between h-full w-[300px] border-r py-5">
        <div className="space-y-2 cursor-pointer">
          {menu.map((item, index) => (
            <p
              key={index}
              onClick={() => handleNavigation(item.path)} // Use the new handleNavigation function
              className={`${
                item.path === location.pathname
                  ? "bg-sub text-custom"
                  : "text-custom"
              } flex items-center px-5 py-1 rounded-r-full`}
            >
              <ListItem button>
                <ListItemIcon>
                  {item.path === location.pathname
                    ? item.activeIcon
                    : item.icon}
                </ListItemIcon>
                <ListItemText primary={item.name} />
              </ListItem>
            </p>
          ))}
        </div>
      </div>
      <Divider />
    </div>
  );
};

export default DrawList;
