import { Form, Input } from "antd";
import { useEffect } from "react";

export const WorkplaceForm = ({ workplaceDetails, formRef, onChange }) => {

    useEffect(() => {
        if (workplaceDetails != undefined) {
            formRef.setFieldsValue({
                ...workplaceDetails,
            });
        }
    }, [workplaceDetails]);

    return (
        <Form
            name="workplace-form"
            form={formRef}
            className="workplace-form"
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
                label="Workplace name"
                rules={[
                    {
                        required: true,
                        message: "Please input workplace name!",
                    },
                ]}
            >
                <Input
                    placeholder="Enter workplace name"
                    onChange={(e) =>
                        onChange({ name: e.target.value })
                    }
                    name="name"
                />
            </Form.Item>
        </Form>
    );
};
