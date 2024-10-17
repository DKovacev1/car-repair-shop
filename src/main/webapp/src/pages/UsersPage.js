import {
    Button,
    Checkbox,
    FloatButton,
    Form,
    Input,
    Select,
    Table,
    Tag,
} from "antd";
import {
    checkboxOffset,
    formCols,
    formOffset,
    initialFormUser,
    ROLE_NAMES,
} from "../constants";
import { LockFilled, UnlockFilled, UserAddOutlined } from "@ant-design/icons";
import { useState } from "react";
import { UsersService } from "../service";
import { ActivationModal, AddNewUserModal } from "../components";

export const UsersPage = () => {
    const [userList, setUserList] = useState([]);

    const [selectedUser, setSelectedUser] = useState({});
    const [isActivationModalOpened, setIsActivationModalOpened] = useState(false);
    const [isAddNewUserModalOpened, setIsAddNewUserModalOpened] = useState(false);

    const [formValues, setFormValues] = useState(initialFormUser);

    const columns = [
        {
            title: "Status",
            dataIndex: "activated",
            key: "activated",
            render: (_, record) => (
                <>
                    {record.activated ? (
                        <UnlockFilled style={{ color: "green", fontSize: "2em" }} />
                    ) : (
                        <LockFilled
                            style={{ color: "red", fontSize: "2em" }}
                            onClick={() => {
                                setSelectedUser(record);
                                setIsActivationModalOpened(true);
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

    const onValueChange = (attributeKey, attributeValue) =>
        setFormValues({ ...formValues, [attributeKey]: attributeValue });

    const onFormFinish = () =>
        UsersService.getUsersByFilter(formValues).then((users) =>
            setUserList(users)
        );

    return (
        <div>
            <Form
                {...formCols}
                labelWrap
                onFinish={onFormFinish}
                style={{ marginTop: "50px", marginBottom: "75px" }}
            >
                <Form.Item name="firstName" label="First Name">
                    <Input
                        placeholder="Enter wanted first name"
                        onChange={(e) =>
                            onValueChange(e.target.name, e.target.value)
                        }
                        name="firstName"
                    />
                </Form.Item>
                <Form.Item name="lastName" label="Last Name">
                    <Input
                        placeholder="Enter wanted last name"
                        onChange={(e) =>
                            onValueChange(e.target.name, e.target.value)
                        }
                        name="lastName"
                    />
                </Form.Item>
                <Form.Item name="email" label="eMail">
                    <Input
                        type="email"
                        placeholder="Enter wanted email"
                        onChange={(e) =>
                            onValueChange(e.target.name, e.target.value)
                        }
                        name="email"
                    />
                </Form.Item>
                <Form.Item name="role" label="Role">
                    <Select
                        style={{ width: "100%", textAlign: "left" }}
                        placeholder={"Select wanted role"}
                        onChange={(value) => onValueChange("idRole", value)}
                        options={[
                            { value: 0, label: "User" },
                            { value: 1, label: "Admin" },
                            { value: 2, label: "Employee" },
                        ]}
                    />
                </Form.Item>
                <Form.Item
                    name="isActivated"
                    label="Show only activated users"
                    {...checkboxOffset}
                >
                    <Checkbox
                        name="isActivated"
                        onChange={(e) => {
                            onValueChange(
                                "isActivated",
                                e.target.checked ? true : ""
                            );
                        }}
                    />
                </Form.Item>

                <Form.Item {...formOffset}>
                    <Button type="primary" htmlType="submit">
                        Get Users
                    </Button>
                </Form.Item>
            </Form>

            <Table
                columns={columns}
                dataSource={userList}
                locale={{ emptyText: "No Users Found" }}
            />

            <ActivationModal
                userData={selectedUser}
                open={isActivationModalOpened}
                close={() => setIsActivationModalOpened(false)}
            />

            <AddNewUserModal
                open={isAddNewUserModalOpened}
                close={() => setIsAddNewUserModalOpened(false)}
            />

            <FloatButton
                shape="square"
                type="primary"
                style={{
                    insetInlineEnd: 48,
                }}
                icon={<UserAddOutlined />}
                tooltip={<div>Add New User</div>}
                onClick={() => setIsAddNewUserModalOpened(true)}
            />
        </div>
    );
};
