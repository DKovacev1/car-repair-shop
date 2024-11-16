import { useSchedule } from "../../hooks";

export const TimeScheduleForm = ({ repairTime }) => {
    const [schedule] = useSchedule(repairTime);

    return (
        <div>
            {schedule.map((freeTime) => (
                <div style={{ lineHeight: "20px" }}>
                    {freeTime.freeDate +
                        " -> " +
                        freeTime.freePeriods[0].freeFrom +
                        "-" +
                        freeTime.freePeriods[0].freeTo}
                </div>
            ))}
        </div>
    );
};
