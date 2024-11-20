import axios from "axios";
import { BASE_URL } from "../constants";
import { toast } from "react-toastify";
import {Modal} from "antd";

export const WorkplaceService = {
    getAllWorkplaces: () => {
        return axios.get(BASE_URL + "/api/workplace", {
            headers: {
                "Access-Control-Allow-Origin": "*",
            },
        }).catch(err => {
            Modal.error({
                title: "Error",
                content: err.response?.data?.message || "An unexpected error occurred. Please try again later.",
            });
        });
    },

    addWorkplace: (workplaceDetails) => {
        return axios
            .post(BASE_URL + "/api/workplace", workplaceDetails, {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then(() => {
                toast.success("New workplace is added!");
            }).catch(err => {
                Modal.error({
                    title: "Error",
                    content: err.response?.data?.message || "An unexpected error occurred. Please try again later.",
                });
            });
    },

    deleteWorkplace: (workplaceId) => {
        return axios
            .delete(BASE_URL + "/api/workplace/" + workplaceId, {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then(() => {
                toast.success("Workplace is deleted!");
            }).catch(err => {
                Modal.error({
                    title: "Error",
                    content: err.response?.data?.message || "An unexpected error occurred. Please try again later.",
                });
            });
    },

    editWorkplace: (workplaceId, workplaceDetails) => {
        return axios
            .put(BASE_URL + "/api/workplace/" + workplaceId, workplaceDetails, {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then(() => {
                toast.success("Workplace is updated!");
            }).catch(err => {
                Modal.error({
                    title: "Error",
                    content: err.response?.data?.message || "An unexpected error occurred. Please try again later.",
                });
            });
    },

};
