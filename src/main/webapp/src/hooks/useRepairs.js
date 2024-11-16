import { useEffect, useState } from "react";
import { PartsService, RepairsService } from "../service";

export const useRepairs = ( mapper = (item) => item ) => {
    const [repairsList, setRepairsList] = useState([]);

    useEffect(() => {
        RepairsService.getAllRepairs().then((response) => {
            setRepairsList(response.data.map(mapper));
        });
    }, []);

    const resetRepairs = () => {
        RepairsService.getAllRepairs().then((response) => {
            setRepairsList(response.data.map(mapper));
        });
    };

    return [repairsList, resetRepairs];
};
