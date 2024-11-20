import { useEffect, useState } from "react";
import { CarsService } from "../service";

export const useCarMakers = () => {
    const [carMakers, setCarMakers] = useState([]);

    useEffect(() => {
        CarsService.getCarMakers()
            .then((response) => {
                const makers = response.data.map(item => {
                    const object = {value: item.idCarMaker, label: item.name};
                    return object
                })
                setCarMakers(makers)
            });
    }, []);

    return [ carMakers ];
};
