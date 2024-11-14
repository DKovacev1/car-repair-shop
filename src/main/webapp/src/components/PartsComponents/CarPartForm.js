import { Form, Input, InputNumber } from "antd";
import { useEffect } from "react";

export const CarPartForm = ({ partDetails, formRef, onChange }) => {

    useEffect(() => {
        if (partDetails != undefined) {
            formRef.setFieldsValue({
                ...partDetails,
            });
        }
    }, [partDetails]);

    return (
        <Form
            name="part-form"
            form={formRef}
            className="part-form"
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
                label="Part name"
                rules={[
                    {
                        required: true,
                        message: "Please input part name!",
                    },
                ]}
            >
                <Input
                    placeholder="Enter part name"
                    onChange={(e) =>
                        onChange({ name: e.target.value })
                    }
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
        </Form>
    );
};
