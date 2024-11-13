import { Form, Input, InputNumber, Select } from "antd";
import { useState, useEffect } from "react";
import { useCarMakers } from "../../hooks";
import { CarsService } from "../../service";

export const CarForm = ({ carDetails, formRef, onChange }) => {
    const [carMakers] = useCarMakers();
    const [carModels, setCarModels] = useState([]);

    const [selectedMaker, setSelectedMaker] = useState(null);

    useEffect(() => {
        if (carDetails != undefined) {
            formRef.setFieldsValue({
                ...carDetails,
            });
        }
    }, [carDetails]);

    useEffect(() => {
        if (carDetails != undefined) {
            const defaultMaker = carMakers.find(
                (item) => item.label == carDetails.maker
            );
            if (defaultMaker) getModels(defaultMaker.value, true);
        }
    }, [carMakers]);

    const getModels = (id, isInitial) => {
        if (id != undefined && id != null) {
            CarsService.getCarModels(id).then((response) => {
                const models = response.data.map((item) => {
                    const object = { value: item.idCarModel, label: item.name };
                    return object;
                });
                setCarModels(models);
                if (isInitial != true)
                    formRef.setFieldsValue({
                        model: null,
                    });
            });
        } else {
            setCarModels([]);
        }
    };

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
                <Select
                    placeholder="Enter maker"
                    onChange={(value, option) => {
                        onChange({
                            maker: value == undefined ? "" : option.label,
                        });

                        getModels(value);
                    }}
                    name="maker"
                    options={carMakers}
                    allowClear
                    showSearch
                    optionFilterProp="label"
                />
            </Form.Item>
            {carModels.length != 0 && (
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
                    <Select
                        placeholder="Enter model"
                        onChange={(value, option) =>
                            onChange({
                                model: value == undefined ? "" : option.label,
                            })
                        }
                        name="model"
                        options={carModels}
                        allowClear
                        showSearch
                        optionFilterProp="label"
                    />
                </Form.Item>
            )}
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
                    onChange={(value) => onChange({ yearOfProduction: value })}
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
                    onChange={(e) =>
                        onChange({ registrationPlate: e.target.value })
                    }
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
                    onChange={(value) => onChange({ fuelType: value })}
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
                    onChange={(value) => onChange({ displacement: value })}
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
                    onChange={(value) => onChange({ cylinders: value })}
                    name="cylinders"
                />
            </Form.Item>
        </Form>
    );
};
