import { Collapse, Form, InputNumber, Progress, Select } from "antd";
import { useParts, useRepairs } from "../../hooks";
import { useState } from "react";

const timeCalculator = (time) => {
    const [hours, minutes] = time.split(":").map(Number);
    return hours * 60 + minutes;
};

export const RepairsForm = ({ setRepairTime, setRepairs, setParts }) => {
    const [percent, setPercent] = useState(0);

    const [repairs] = useRepairs((repair) => ({
        value: repair.idRepair,
        label: repair.name,
        repairTime: repair.repairTime,
    }));

    const handleRepairTime = (objects) => {
        const tmpTime = objects.reduce(
            (sum, repair) => sum + timeCalculator(repair.repairTime),
            0
        );
        setPercent((tmpTime / 480) * 100);
        setRepairTime(tmpTime);
    };

    const addPart = (obj) => setParts(obj);

    const [partsList] = useParts((part) => (
        <Form.Item
            label={part.name + " (" + part.cost.toFixed(2) + "€)"}
            labelCol={{
                span: 12,
            }}
            wrapperCol={{
                span: 4,
            }}
        >
            <InputNumber
                placeholder={"Number of " + part.name}
                onChange={(value) =>
                    addPart({ idPart: part.idPart, quantity: value })
                }
                min={0}
                defaultValue={0}
            />
        </Form.Item>
    ));

    return (
        <Form
            labelCol={{
                span: 7,
            }}
            wrapperCol={{
                span: 12,
            }}
        >
            <Progress
                percent={percent}
                type="line"
                style={{ margin: "16px auto", width: "60%" }}
                status={percent > 100 ? "exception" : ""}
            />
            <Form.Item
                label="Select repairs"
                name="user"
                rules={[
                    {
                        required: true,
                        message: "Please select repairs!",
                    },
                ]}
            >
                <Select
                    mode="multiple"
                    allowClear
                    style={{ width: "100%" }}
                    placeholder="Select repairs"
                    onChange={(ids, objects) => {
                        setRepairs(ids);
                        handleRepairTime(objects);
                    }}
                    options={repairs}
                />
            </Form.Item>
            <Collapse
                items={[{ key: 1, label: "Car Parts", children: partsList }]}
                style={{ width: "80%", margin: "48px auto 32px" }}
            />
        </Form>
    );
};
