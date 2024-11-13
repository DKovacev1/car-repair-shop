import { useEffect, useState } from "react";
import { PartsService } from "../service";

export const useParts = () => {
    const [partsList, setPartsList] = useState([]);

    useEffect(() => {
        PartsService.getAllParts().then((response) => {
            setPartsList(response.data);
        });
    }, []);

    const resetParts = () => {
        PartsService.getAllParts().then((response) => {
            setPartsList(response.data);
        });
    };

    return [partsList, resetParts];
};
