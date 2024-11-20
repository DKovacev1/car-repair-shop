import { useEffect, useState } from "react";
import { WorkplaceService } from "../service/WorkplaceService";

export const useWorkplaces = () => {
    const [workplaceList, setWorkplaceList] = useState([]);

    useEffect(() => {
        WorkplaceService.getAllWorkplaces().then((response) => {
            setWorkplaceList(response.data);
        });
    }, []);

    const resetWorkplaceList = () => {
        WorkplaceService.getAllWorkplaces().then((response) => {
            setWorkplaceList(response.data);
        });
    };

    return [workplaceList, resetWorkplaceList];
};
