import axios from "axios";
import { BASE_URL } from "../constants";
import { toast } from "react-toastify";
import {Modal} from "antd";

export const RepairsService = {
    getAllRepairs: () => {
        return axios.get(BASE_URL + "/api/repair", {
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

    addRepair: (repairDetails) => {
        return axios
            .post(BASE_URL + "/api/repair", repairDetails, {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then(() => {
                toast.success("New repair is added!");
            }).catch(err => {
                Modal.error({
                    title: "Error",
                    content: err.response?.data?.message || "An unexpected error occurred. Please try again later.",
                });
            });
    },

    deleteRepair: (repairId) => {
        return axios
            .delete(BASE_URL + "/api/repair/" + repairId, {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then(() => {
                toast.success("Car repair is deleted!");
            }).catch(err => {
                Modal.error({
                    title: "Error",
                    content: err.response?.data?.message || "An unexpected error occurred. Please try again later.",
                });
            });
    },

    editRepair: (repairId, repairDetails) => {
        return axios
            .put(BASE_URL + "/api/repair/" + repairId, repairDetails, {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then(() => {
                toast.success("Car repair is updated!");
            }).catch(err => {
                Modal.error({
                    title: "Error",
                    content: err.response?.data?.message || "An unexpected error occurred. Please try again later.",
                });
            });
    },

};
