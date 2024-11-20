import { useEffect, useState } from "react";
import { CarsService } from "../service";

export const useCars = () => {
    const [carsList, setCarsList] = useState([]);

    useEffect(() => {
        CarsService.getAllCars().then((response) => {
            setCarsList(response.data);
        });
    }, []);

    const resetCars = () => {
        CarsService.getAllCars().then((response) => {
            setCarsList(response.data);
        });
    };

    return [carsList, resetCars];
};
