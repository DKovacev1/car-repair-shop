import axios from "axios";
import React, { useEffect, useState } from "react";
import { BASE_URL } from "../constants";
import { AppContext } from "../AppContext";

export const useRoles = ({ mode }) => {
    const [rolesList, setRolesList] = useState([]);
    const appContext = React.useContext(AppContext);

    useEffect(() => {
        if (appContext.isAuthenticated)
            axios
                .get(BASE_URL + "/api/role", {
                    headers: {
                        "Access-Control-Allow-Origin": "*",
                    },
                })
                .then((response) => {
                    if (mode === "Add User") {
                        const roleId = appContext.role.roleId;
                        const roles = response.data.map((role) => {
                            if (role.id >= roleId)
                                setRolesList((prev) => [...prev, role]);
                        });
                        setRolesList(roles);
                    } else setRolesList(response.data);
                });
    }, []);

    return [rolesList];
};

useRoles.defaultProps = {
    mode: "",
};
