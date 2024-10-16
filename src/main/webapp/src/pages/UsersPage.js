import { Button, Form, Input, Modal, Switch, Table, Tag } from "antd";
import { useUsers } from "../hooks";
import { BASE_URL, ROLE_NAMES } from "../constants";
import { LockFilled, UnlockFilled } from "@ant-design/icons";
import { useState } from "react";
import axios from "axios";
import { toast } from "react-toastify";

export const UsersPage = () => {
    const [userList] = useUsers([]);

    const [selectedUser, setSelectedUser] = useState({});
    const [isActivationModuleOpened, setIsActivationModuleOpened] =
        useState(false);

    const [formValues, setFormValues] = useState({
        firstName: "",
        lastName: "",
        email: "",
        isActivated: true,
        idRole: "",
    });

    const columns = [
        {
            title: "Status",
            dataIndex: "activated",
            key: "activated",
            render: (_, record) => (
                <>
                    {record.activated ? (
                        <UnlockFilled
                            style={{ color: "green", fontSize: "2em" }}
                        />
                    ) : (
                        <LockFilled
                            style={{ color: "red", fontSize: "2em" }}
                            onClick={() => {
                                setSelectedUser(record);
                                setIsActivationModuleOpened(true);
                            }}
                        />
                    )}
                </>
            ),
        },
        {
            title: "First Name",
            dataIndex: "firstName",
            key: "firstName",
        },
        {
            title: "Last Name",
            dataIndex: "lastName",
            key: "lastName",
        },
        {
            title: "E mail",
            dataIndex: "email",
            key: "email",
        },
        {
            title: "Role",
            key: "role",
            dataIndex: "role",
            render: (_, { role }) => (
                <Tag
                    color={role.name === ROLE_NAMES.Admin ? "red" : "blue"}
                    key={role.idRole}
                >
                    {role.name.toUpperCase()}
                </Tag>
            ),
        },
    ];

    const handleCancel = () => {
        setIsActivationModuleOpened(false);
    };
    const handleOk = () => {
        axios
            .post(
                BASE_URL +
                    "/api/app-user/" +
                    selectedUser.idAppUser +
                    "/activate-app-user"
            )
            .then(() => {
                toast.success("User is activated!");
            });
        setIsActivationModuleOpened(false);
    };

    const onValueChange = (e) => {
        setFormValues({ ...formValues, [e.target.name]: e.target.value });
    };

    return (
        <div>
            <Form>
                {/*firstName=&lastName&email&isActivated=&idRole=*/}
                <Form.Item name="firstName" label="First Name">
                    <Input
                        placeholder="Enter wanted first name"
                        onChange={onValueChange}
                        name="firstName"
                    />
                </Form.Item>
                <Form.Item name="lastName" label="Last Name">
                    <Input
                        placeholder="Enter wanted last name"
                        onChange={onValueChange}
                        name="lastName"
                    />
                </Form.Item>
                <Form.Item name="email" label="email">
                    <Input
                        type="email"
                        placeholder="Enter wanted first name"
                        onChange={onValueChange}
                        name="email"
                    />
                </Form.Item>
                <Form.Item name="isActivated" label="Is user activated?">
                    <Switch
                        defaultChecked
                        onChange={(boolean, e) => onValueChange(e)}
                        name="isActivated"
                        checkedChildren={<>Yes</>}
                        unCheckedChildren={<>No</>}
                    />
                </Form.Item>
                {/* ROLE */}
            </Form>
            <Table columns={columns} dataSource={userList} />
            <Modal
                title="User Activation"
                open={isActivationModuleOpened}
                centered
                onOk={handleOk}
                onCancel={handleCancel}
                footer={[
                    <Button key="back" onClick={handleCancel}>
                        Return
                    </Button>,
                    <Button key="submit" type="primary" onClick={handleOk}>
                        Activate User
                    </Button>,
                ]}
                destroyOnClose
            >
                <p>
                    Do you want to activate user{" "}
                    {selectedUser.firstName + " " + selectedUser.lastName}?
                </p>
            </Modal>
        </div>
    );
};
