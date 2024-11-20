import axios from "axios";
import { BASE_URL } from "../constants";
import { toast } from "react-toastify";
import {Modal} from "antd";

export const PartsService = {
    getAllParts: () => {
        return axios.get(BASE_URL + "/api/part", {
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

    addPart: (partDetails) => {
        return axios
            .post(BASE_URL + "/api/part", partDetails, {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then(() => {
                toast.success("New part is added!");
            }).catch(err => {
                Modal.error({
                    title: "Error",
                    content: err.response?.data?.message || "An unexpected error occurred. Please try again later.",
                });
            });
    },

    deletePart: (partId) => {
        return axios
            .delete(BASE_URL + "/api/part/" + partId, {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then(() => {
                toast.success("Car part is deleted!");
            }).catch(err => {
                Modal.error({
                    title: "Error",
                    content: err.response?.data?.message || "An unexpected error occurred. Please try again later.",
                });
            });
    },

    editPart: (partId, partDetails) => {
        return axios
            .put(BASE_URL + "/api/part/" + partId, partDetails, {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then(() => {
                toast.success("Car part is updated!");
            }).catch(err => {
                Modal.error({
                    title: "Error",
                    content: err.response?.data?.message || "An unexpected error occurred. Please try again later.",
                });
            });
    },

};
