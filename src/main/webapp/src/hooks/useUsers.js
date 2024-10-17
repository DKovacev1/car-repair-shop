import { useEffect, useState } from "react";
import { UsersService } from "../service";

export const useUsers = () => {
    const [userList, setUserList] = useState([]);

    useEffect(() => {
        UsersService.getAllUsers()
            .then((response) => {
                setUserList(response.data)
            });
    }, []);

    return [ userList, setUserList ];
};
