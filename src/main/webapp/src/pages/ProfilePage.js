import { Button, Form, Input } from "antd";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { AppContext } from "../AppContext";
import { UsersService } from "../service";

export const ProfilePage = () => {
    const navigate = useNavigate();
    const [formValues, setFormValues] = useState({});
    const userContext = React.useContext(AppContext);
    const [formRef] = Form.useForm();

    const onValueChange = (e) => {
        setFormValues({ ...formValues, [e.target.name]: e.target.value });
    };

    useEffect(() => {
        if (userContext != undefined) {
            formRef.setFieldsValue({
                firstName: userContext.firstName,
                lastName: userContext.lastName,
                email: userContext.email, 
            });
            setFormValues({
                firstName: userContext.firstName,
                lastName: userContext.lastName,
                email: userContext.email, 
            })
        }
    }, [userContext]);

    return (
        <Form
            name="register-form"
            form={formRef}
            labelCol={{
                span: 8,
            }}
            wrapperCol={{
                span: 8,
            }}
            onFinish={() =>
                UsersService.updateUser(userContext.idAppUser, formValues)
            }
            clearOnDestroy
        >
            <Form.Item
                name="firstName"
                label="First Name"
                rules={[
                    {
                        required: true,
                        message: "Please input first name!",
                    },
                ]}
            >
                <Input
                    placeholder="Enter first name"
                    onChange={onValueChange}
                    name="firstName"
                />
            </Form.Item>
            <Form.Item
                name="lastName"
                label="Last Name"
                rules={[
                    {
                        required: true,
                        message: "Please input last name!",
                    },
                ]}
            >
                <Input
                    placeholder="Enter last name"
                    onChange={onValueChange}
                    name="lastName"
                />
            </Form.Item>
            <Form.Item
                name="email"
                label="E-mail"
                rules={[
                    {
                        type: "email",
                        required: true,
                        message: "Please input e-mail!",
                    },
                ]}
            >
                <Input
                    type="mail"
                    placeholder="Enter E-mail address"
                    onChange={onValueChange}
                    name="email"
                />
            </Form.Item>
            <Button
                type="primary"
                htmlType="submit"
                className="register-form-button"
                style={{ marginRight: "5px" }}
            >
                Update
            </Button>
        </Form>
    );
};
