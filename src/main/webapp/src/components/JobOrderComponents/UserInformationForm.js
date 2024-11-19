import { useState } from "react";
import { useUsers } from "../../hooks";
import { Form, Select } from "antd";
import { CarsService } from "../../service";
import { toast } from "react-toastify";

const usersMapper = (list) =>
    list.map((user) => {
        const userOption = {
            value: user.idAppUser,
            label: user.email + ", " + user.firstName + " " + user.lastName,
        };
        return userOption;
    });

const carsMapper = (list) =>
    list.map((car) => {
        const carOption = {
            value: car.idCar,
            label: car.maker + " " + car.model + ", " + car.registrationPlate ,
        };
        return carOption;
    });

export const UserInformationForm = ({setCarId}) => {
    const [userList] = useUsers(usersMapper);
    const [cars, setCars] = useState([]);

    const findCars = (userId) => {
        if (userId != undefined) {
            CarsService.getAllCars().then((res) => {
                const filteredCars = res.data.filter(
                    (car) => car.carOwner.idAppUser === userId
                );

                if (filteredCars.length === 0)
                    toast.error("No cars by that user are found!");
                else setCars(carsMapper(filteredCars));
            });
        }
    };

    return (
        <Form
            labelCol={{
                span: 8,
            }}
            wrapperCol={{
                span: 12,
            }}
        >
            <Form.Item
                label="Select a user"
                name="user"
                rules={[
                    {
                        required: true,
                        message: "Please select user!",
                    },
                ]}
            >
                <Select
                    allowClear
                    showSearch
                    optionFilterProp="label"
                    placeholder="Select a user"
                    options={userList}
                    onChange={(value) => findCars(value)}
                />
            </Form.Item>
            <Form.Item
                label="Select a car"
                name="car"
                rules={[
                    {
                        required: true,
                        message: "Please select a car!",
                    },
                ]}
            >
                <Select
                    allowClear
                    showSearch
                    optionFilterProp="label"
                    placeholder="Select a car"
                    options={cars}
                    onChange={(value) => setCarId({idCar: value})}
                    disabled={cars.length === 0}
                />
            </Form.Item>
        </Form>
    );
};
