import axios from "axios";
import { BASE_URL, initialFormUser } from "../constants";
import { toast } from "react-toastify";
import {Modal} from "antd";

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
            .then((res) => res.data)
            .catch(err => {
                Modal.error({
                    title: "Error",
                    content: err.response?.data?.message || "An unexpected error occurred. Please try again later.",
                });
            });
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
            })
            .catch(err => {
                Modal.error({
                    title: "Error",
                    content: err.response?.data?.message || "An unexpected error occurred. Please try again later.",
                });
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
            })
            .catch(err => {
                Modal.error({
                    title: "Error",
                    content: err.response?.data?.message || "An unexpected error occurred. Please try again later.",
                });
            });
    },

    addNewUser: (userData) => {
        return axios
            .post(BASE_URL + "/api/app-user", userData, {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then((res) => res.data)
            .catch(err => {
                Modal.error({
                    title: "Error",
                    content: err.response?.data?.message || "An unexpected error occurred. Please try again later.",
                });
            });
    },

    updateUser: (userId, userData) => {
        axios
            .put(BASE_URL + "/api/app-user/" + userId, userData, {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then(() => {
                toast.success("User's informations are updated!");
            })
            .catch(err => {
                Modal.error({
                    title: "Error",
                    content: err.response?.data?.message || "An unexpected error occurred. Please try again later.",
                });
            });
    },
};
