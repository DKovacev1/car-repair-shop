import { Form, Input, InputNumber, Select } from "antd";
import { useEffect } from "react";

export const CarForm = ({ carDetails, formRef, onChange }) => {

    useEffect(() => {
        if (carDetails != undefined) {
            formRef.setFieldsValue({
                ...carDetails,
            });
        }
    }, [carDetails]);

    return (
        <Form
            name="car-form"
            form={formRef}
            className="car-form"
            labelCol={{
                span: 12,
            }}
            wrapperCol={{
                span: 12,
            }}
            clearOnDestroy
        >
            <Form.Item
                name="maker"
                label="Maker"
                rules={[
                    {
                        required: true,
                        message: "Please input car maker!",
                    },
                ]}
            >
                <Input
                    placeholder="Enter maker"
                    onChange={(e) => onChange({maker: e.target.value})}
                    name="maker"
                />
            </Form.Item>
            <Form.Item
                name="model"
                label="Model"
                rules={[
                    {
                        required: true,
                        message: "Please input model of a car!",
                    },
                ]}
            >
                <Input
                    placeholder="Enter model"
                    onChange={(e) => onChange({model: e.target.value})}
                    name="model"
                />
            </Form.Item>
            <Form.Item
                name="yearOfProduction"
                label="Year of production"
                rules={[
                    {
                        required: true,
                        message: "Please input year of production!",
                    },
                ]}
            >
                <InputNumber
                    placeholder="Enter year of production"
                    onChange={(value) => onChange({yearOfProduction: value})}
                    name="yearOfProduction"
                />
            </Form.Item>

            <Form.Item
                name="registrationPlate"
                label="Registration plate"
                rules={[
                    {
                        required: true,
                        message: "Please input registration plate!",
                    },
                ]}
            >
                <Input
                    placeholder="Enter registration plate"
                    onChange={(e) => onChange({registrationPlate: e.target.value})}
                    name="registrationPlate"
                />
            </Form.Item>
            <Form.Item
                name="fuelType"
                label="Fuel type"
                rules={[
                    {
                        required: true,
                        message: "Please input fuel type!",
                    },
                ]}
            >
                <Select
                    placeholder="Enter fuel type"
                    onChange={(value) => onChange({fuelType: value})}
                    name="fuelType"
                    options={[
                        { value: "diesel", label: "Diesel" },
                        { value: "petrol", label: "Petrol" },
                        { value: "electric", label: "Electric" },
                        { value: "hybrid", label: "Hybrid" },
                        { value: "other", label: "Other" },
                    ]}
                />
            </Form.Item>
            <Form.Item
                name="displacement"
                label="Displacement"
                rules={[
                    {
                        required: true,
                        message: "Please input displacement!",
                    },
                ]}
            >
                <InputNumber
                    placeholder="Enter displacement"
                    onChange={(value) => onChange({displacement: value})}
                    name="displacement"
                    step={0.05}
                />
            </Form.Item>

            <Form.Item
                name="cylinders"
                label="Number of cylinders"
                rules={[
                    {
                        required: true,
                        message: "Please input cylinders!",
                    },
                ]}
            >
                <InputNumber
                    placeholder="Enter cylinders"
                    onChange={(value) => onChange({cylinders: value})}
                    name="cylinders"
                />
            </Form.Item>
        </Form>
    );
};
