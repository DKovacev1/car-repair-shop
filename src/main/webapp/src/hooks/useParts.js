import { useEffect, useState } from "react";
import { PartsService } from "../service";

export const useParts = ( mapper = (item) => item ) => {
    const [partsList, setPartsList] = useState([]);

    useEffect(() => {
        PartsService.getAllParts().then((response) => {
            setPartsList(response.data.map(mapper));
        });
    }, []);

    const resetParts = () => {
        PartsService.getAllParts().then((response) => {
            setPartsList(response.data.map(mapper));
        });
    };

    return [partsList, resetParts];
};
