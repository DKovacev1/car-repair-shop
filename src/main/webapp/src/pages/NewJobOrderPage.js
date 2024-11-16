import { Button, Steps, theme } from "antd";
import {
    ClockCircleOutlined,
    SmileOutlined,
    ToolOutlined,
    UserOutlined,
} from "@ant-design/icons";
import { useState } from "react";
import { toast } from "react-toastify";
import {
    RepairsForm,
    TimeScheduleForm,
    UserInformationForm,
} from "../components";

export const NewJobOrderPage = () => {
    const { token } = theme.useToken();
    const [current, setCurrent] = useState(0);
    const [values, setValues] = useState({
        idCar: null,
        repairIds: [],
        parts: [],
    });
    const [repairTime, setRepairTime] = useState("");

    const next = () => {
        setCurrent(current + 1);
    };

    const contentStyle = {
        lineHeight: "260px",
        textAlign: "center",
        color: token.colorTextTertiary,
        backgroundColor: token.colorFillAlter,
        borderRadius: token.borderRadiusLG,
        border: `1px dashed ${token.colorBorder}`,
        marginTop: 16,
        paddingTop: 16,
        paddingBottom: 16,
    };

    console.log(values);

    const steps = [
        {
            title: "User Information",
            icon: <UserOutlined />,
            content: (
                <UserInformationForm
                    setCarId={(value) => setValues({ ...values, ...value })}
                />
            ),
        },
        {
            title: "Repairs",
            icon: <ToolOutlined />,
            content: (
                <RepairsForm
                    setRepairTime={setRepairTime}
                    setRepairs={(repairIds) =>
                        setValues({ ...values, repairIds: repairIds })
                    }
                    setParts={(newPart) => {
                        const index = values.parts.findIndex(
                            (part) => part.idPart === newPart.idPart
                        );

                        if (newPart.quantity === 0) {
                            if (index !== -1) {
                                values.parts.splice(index, 1);
                            }
                        } else if (index !== -1) {
                            values.parts[index] = newPart;
                        } else {
                            values.parts.push(newPart);
                        }
                    }}
                />
            ),
        },
        {
            title: "Time",
            icon: <ClockCircleOutlined />,
            content: <TimeScheduleForm repairTime={repairTime} />,
        },
    ];

    return (
        <>
            <Steps current={current} items={steps} />
            <div style={contentStyle}>{steps[current].content}</div>
            <div style={{ marginTop: 24 }}>
                {current < steps.length - 1 && (
                    <Button
                        type="primary"
                        onClick={() => next()}
                        disabled={
                            (current == 0 && values.idCar == null) ||
                            (current == 1 && repairTime > 8 * 60)
                        }
                    >
                        Next
                    </Button>
                )}
                {current === steps.length - 1 && (
                    <Button
                        type="primary"
                        onClick={() => toast.success("Processing complete!")}
                    >
                        Done
                    </Button>
                )}
            </div>
        </>
    );
};