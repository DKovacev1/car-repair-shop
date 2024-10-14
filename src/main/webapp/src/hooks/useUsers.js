import axios from "axios";
import { useEffect, useState } from "react";
import { BASE_URL } from "../constants";

export const useUsers = () => {
    const [userList, setUserList] = useState([]);

    useEffect(() => {
        axios
            .get(BASE_URL + "/api/app-user", {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then((response) => {
                setUserList(response.data)
            });
    }, []);

    return [ userList ];
};
