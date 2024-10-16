import axios from "axios";
import { useEffect, useState } from "react";
import { BASE_URL } from "../constants";

export const useCars = ({ userId, numberOfCarsInRow }) => {
    const [carsList, setCarsList] = useState([]);

    useEffect(() => {
        /*axios
            .get(BASE_URL + "/api/app-user", {
                headers: {
                    "Access-Control-Allow-Origin": "*",
                },
            })
            .then((response) => {
                setCarsList(response.data)
            });*/

        setCarsList([
            {
                id: 1,
                name: "Ženin auto",
                brand: "Kia",
                model: "Sportage",
                godina: "2024",
                kilometri: "3500",
                zadnjiServis: "28.08.2024.",
                imageSrc: "https://app.kmag.net/files/download/?file_id=64485",
            },
            {
                id: 2,
                name: "Ženin auto",
                brand: "Kia",
                model: "Sportage",
                godina: "2024",
                kilometri: "3500",
                zadnjiServis: "28.08.2024.",
                imageSrc: "https://app.kmag.net/files/download/?file_id=64485",
            },
            {
                id: 3,
                name: "Ženin auto",
                brand: "Kia",
                model: "Sportage",
                godina: "2024",
                kilometri: "3500",
                zadnjiServis: "28.08.2024.",
                imageSrc: "https://app.kmag.net/files/download/?file_id=64485",
            },
            {
                id: 4,
                name: "Ženin auto",
                brand: "Kia",
                model: "Sportage",
                godina: "2024",
                kilometri: "3500",
                zadnjiServis: "28.08.2024.",
                imageSrc: "https://app.kmag.net/files/download/?file_id=64485",
            },
            {
                id: 5,
                name: "Ženin auto",
                brand: "Kia",
                model: "Sportage",
                godina: "2024",
                kilometri: "3500",
                zadnjiServis: "28.08.2024.",
                imageSrc: "https://app.kmag.net/files/download/?file_id=64485",
            },
        ]);
    }, []);

    return [carsList];
};
