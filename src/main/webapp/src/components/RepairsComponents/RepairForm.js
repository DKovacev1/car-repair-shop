import { Form, Input, InputNumber, Slider } from "antd";
import { useEffect } from "react";

const formatter = (value) => {
    const hours = Math.floor(value);
    const minutes = Math.round((value - hours) * 60);
    return `${hours.toString().padStart(2, "0")}:${minutes
        .toString()
        .padStart(2, "0")}`;
};

const reverseTimeFormatter = (timeString) => {
    const [hours, minutes] = timeString.split(":").map(Number);
    return hours + minutes / 60;
};

export const RepairForm = ({ repairDetails, formRef, onChange }) => {
    useEffect(() => {
        if (repairDetails != undefined) {
            formRef.setFieldsValue({
                ...repairDetails,
                repairTime: reverseTimeFormatter(repairDetails.repairTime),
            });
        }
    }, [repairDetails]);

    return (
        <Form
            name="repair-form"
            form={formRef}
            className="repair-form"
            labelCol={{
                span: 12,
            }}
            wrapperCol={{
                span: 12,
            }}
            clearOnDestroy
        >
            <Form.Item
                name="name"
                label="Repair name"
                rules={[
                    {
                        required: true,
                        message: "Please input repair name!",
                    },
                ]}
            >
                <Input
                    placeholder="Enter repair name"
                    onChange={(e) => onChange({ name: e.target.value })}
                    name="name"
                />
            </Form.Item>
            <Form.Item
                name="cost"
                label="Cost"
                rules={[
                    {
                        required: true,
                        message: "Please input cost!",
                    },
                ]}
            >
                <InputNumber
                    placeholder="Enter cost"
                    onChange={(value) => onChange({ cost: value })}
                    name="cost"
                    addonAfter="€"
                />
            </Form.Item>
            <Form.Item
                name="repairTime"
                label="Repair Time"
                rules={[
                    {
                        required: true,
                        message: "Please input time!",
                    },
                ]}
            >
                <Slider
                    onChange={(value) =>
                        onChange({ repairTime: formatter(value) + ":00" })
                    }
                    name="repairTime"
                    tooltip={{ formatter }}
                    min={0.25}
                    max={8}
                    step={0.25}
                />
            </Form.Item>
        </Form>
    );
};
