import axios from "axios";
import { BASE_URL, initialFormUser } from "../constants";
import { toast } from "react-toastify";

export const UsersService = {
    getAllUsers: () => {
        return UsersService.getUsersByFilter(initialFormUser);
    },

    getUsersByFilter: (filters) => {
        const filtersString =
            "firstName=" +
            filters.firstName +
            "&lastName=" +
            filters.lastName +
            "&email=" +
            filters.email +
            "&isActivated=" +
            filters.isActivated +
            "&idRole=" +
            filters.idRole;

        return axios
            .get(BASE_URL + "/api/app-user?" + filtersString, {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then((res) => res.data);
    },

    activateUser: (userId) => {
        axios
            .post(BASE_URL + "/api/app-user/" + userId + "/activate-app-user", {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then(() => {
                toast.success("User is activated!");
            });
    },

    deactivateUser: (userId) => {
        axios
            .delete(BASE_URL + "/api/app-user/" + userId, {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then(() => {
                toast.success("User is deleted!");
            });
    },

    addNewUser: (userData) => {
        return axios
            .post(BASE_URL + "/api/app-user", userData, {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then((res) => res.data);
    },

    updateUser: (userId, userData) => {
        axios
            .put(BASE_URL + "/api/app-user/" + userId, userData, {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then(() => {
                toast.success("User is deleted!");
            });
    },
};
