import axios from "axios";
import { BASE_URL } from "../constants";
import { toast } from "react-toastify";

export const WorkplaceService = {
    getAllWorkplaces: () => {
        return axios.get(BASE_URL + "/api/workplace", {
            headers: {
                "Access-Control-Allow-Origin": "*",
            },
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
            });
    },

};
