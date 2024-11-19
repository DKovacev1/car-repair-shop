import { DatePicker, Form, Input, TimePicker } from "antd";
import { useSchedule } from "../../hooks";
import dayjs from "dayjs";
import { useState } from "react";
const { TextArea } = Input;

export const TimeScheduleForm = ({ repairTime, setTime, setDescription }) => {
    const [schedule] = useSchedule(repairTime);

    const [selectedDay, setSelectedDay] = useState("");

    const handleDateChange = (date) => {
        if (date) {
            const formattedDate = date.format("YYYY-MM-DD");
            setSelectedDay(formattedDate);
        } else {
            setSelectedDay(null);
        }
    };

    const handleTimeChange = (time) => {
        if (!time || !repairTime) return;

        const timeFrom = time.format("HH:mm:ss");
        const timeTo = time.add(repairTime, "minute").format("HH:mm:ss");

        setTime({
            orderDate: selectedDay,
            timeFrom: timeFrom,
            timeTo: timeTo,
        });
    };

    const disabledDate = (current) => {
        const today = dayjs().endOf("day");
        const maxDate = dayjs().add(30, "days").endOf("day");

        const isInDateList = schedule.some((dateObj) =>
            dayjs(current).isSame(dayjs(dateObj.freeDate), "day")
        );

        return (
            current && (current < today || current > maxDate || !isInDateList)
        );
    };

    const disabledDateTime = (current) => {
        const dateObj = schedule.find((date) => date.freeDate === selectedDay);

        if (!dateObj) {
            return {
                disabledHours: () => Array.from({ length: 24 }, (_, i) => i),
                disabledMinutes: () => Array.from({ length: 60 }, (_, i) => i),
                disabledSeconds: () => Array.from({ length: 60 }, (_, i) => i),
            };
        }

        const validHours = [];
        const validMinutesByHour = {};

        dateObj.freePeriods.forEach((period) => {
            const freeFrom = dayjs(`${selectedDay} ${period.freeFrom}`);
            const freeTo = dayjs(`${selectedDay} ${period.freeTo}`);

            let currentTime = freeFrom;

            while (
                currentTime.add(repairTime, "minute").isBefore(freeTo) ||
                currentTime.add(repairTime, "minute").isSame(freeTo)
            ) {
                const hour = currentTime.hour();
                const minute = currentTime.minute();

                if (!validHours.includes(hour)) {
                    validHours.push(hour);
                    validMinutesByHour[hour] = [];
                }

                validMinutesByHour[hour].push(minute);
                currentTime = currentTime.add(1, "minute");
            }
        });

        return {
            disabledHours: () =>
                Array.from({ length: 24 }, (_, i) => i).filter(
                    (hour) => !validHours.includes(hour)
                ),
            disabledMinutes: (selectedHour) =>
                Array.from({ length: 60 }, (_, i) => i).filter(
                    (minute) =>
                        !validMinutesByHour[selectedHour]?.includes(minute)
                ),
            disabledSeconds: () => [],
        };
    };

    return (
        <Form
            labelCol={{
                span: 7,
            }}
            wrapperCol={{
                span: 12,
            }}
        >
            <Form.Item
                label="Select Date"
                name="date"
                rules={[
                    {
                        required: true,
                        message: "Please select date!",
                    },
                ]}
            >
                <DatePicker
                    format="YYYY-MM-DD"
                    disabledDate={disabledDate}
                    onCalendarChange={handleDateChange}
                    style={{ width: "100%" }}
                />
            </Form.Item>
            <Form.Item
                label="Select Time"
                name="time"
                rules={[
                    {
                        required: true,
                        message: "Please select time!",
                    },
                ]}
            >
                <TimePicker
                    disabled={selectedDay == "" || selectedDay == null}
                    showTime={{ format: "HH:mm", hourStep: 1, minuteStep: 30 }}
                    disabledTime={disabledDateTime}
                    showNow={false}
                    style={{ width: "100%" }}
                    needConfirm
                    onChange={handleTimeChange}
                />
            </Form.Item>
            <Form.Item
                label="Description"
                name="description"
                rules={[
                    {
                        required: true,
                        message: "Please input description!",
                    },
                ]}
            >
                <TextArea
                    rows={4}
                    placeholder="Enter all usefull informations if needed..."
                    onChange={(e) => setDescription(e.target.value)}
                />
            </Form.Item>
        </Form>
    );
};
