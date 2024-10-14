import { Table, Tag } from "antd";
import { useUsers } from "../hooks";
import { ROLE_NAMES } from "../constants";
import { LockFilled, UnlockFilled } from "@ant-design/icons";

const columns = [
    {
        title: "Status",
        dataIndex: "activated",
        key: "activated",
        render: (_, { activated }) => (
            <>{activated ? <UnlockFilled style={{color:"green"}}/> : <LockFilled style={{color:"red"}}/>}</>
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

export const UsersPage = () => {
    const [userList] = useUsers([]);

    return (
        <div>
            <Table columns={columns} dataSource={userList} />
        </div>
    );
};
