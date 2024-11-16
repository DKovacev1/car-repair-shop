import { useEffect, useState } from "react";
import { JobOrderService } from "../service";

export const useSchedule = (repairTime) => {
    const [schedule, setSchedule] = useState();

    const minutesToTime = (minutes) => {
        const totalSeconds = minutes * 60;
        const hours = Math.floor(totalSeconds / 3600);
        const remainingSeconds = totalSeconds % 3600;
        const mins = Math.floor(remainingSeconds / 60);
        const secs = remainingSeconds % 60;

        return [
            String(hours).padStart(2, "0"),
            String(mins).padStart(2, "0"),
            String(secs).padStart(2, "0"),
        ].join(":");
    };

    useEffect(() => {
        JobOrderService.getSchedule(minutesToTime(repairTime)).then((response) => {
            setSchedule(response.data);
        });
    }, []);

    return [schedule];
};
