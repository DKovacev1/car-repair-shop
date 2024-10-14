import React from "react";
import { Outlet } from "react-router-dom";
import { AppContext } from "../AppContext";
import { NoAuthorityPage } from "../pages/NoAuthorityPage";

export const SecureRoute = ({ authorized_roles }) => {
    const userContext = React.useContext(AppContext);

    const isUserAuthorized = authorized_roles.includes(userContext.role.name);

    return isUserAuthorized ? <Outlet /> : <NoAuthorityPage />;
};
