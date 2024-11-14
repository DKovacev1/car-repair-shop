import { useEffect, useState } from "react";
import { PartsService, RepairsService } from "../service";

export const useRepairs = () => {
    const [repairsList, setRepairsList] = useState([]);

    useEffect(() => {
        RepairsService.getAllRepairs().then((response) => {
            setRepairsList(response.data);
        });
    }, []);

    const resetRepairs = () => {
        RepairsService.getAllRepairs().then((response) => {
            setRepairsList(response.data);
        });
    };

    return [repairsList, resetRepairs];
};
