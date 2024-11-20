import { useEffect, useState } from "react";
import { UsersService } from "../service";

export const useUsers = (mapper) => {
    const [userList, setUserList] = useState([]);

    useEffect(() => {
        UsersService.getAllUsers()
            .then((data) => {
                setUserList(mapper(data))
            });
    }, []);

    return [ userList, setUserList ];
};
