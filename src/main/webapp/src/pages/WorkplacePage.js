import { Divider } from "antd";
import { useWorkplaces } from "../hooks";
import { useState } from "react";
import {
    WorkplaceCard,
    DeleteWorkplaceModal,
    EditWorkplaceModal,
    AddNewWorkplaceCard,
    AddNewWorkplaceModal,
} from "../components";

export const WorkplacePage = () => {
    const [workplaceList, resetWorkplaceList] = useWorkplaces();

    const [selectedWorkplace, setSelectedWorkplace] = useState({});

    const [isAddNewWorkplaceModalOpened, setIsAddNewWorkplaceModalOpened] =
        useState(false);
    const [isDeleteModalOpened, setIsDeleteModalOpened] = useState(false);
    const [isEditModalOpened, setIsEditModalOpened] = useState(false);

    return (
        <div>
            <div
                style={{
                    display: "flex",
                    justifyContent: "space-between",
                    alignItems: "end",
                }}
            >
                <h1
                    style={{
                        margin: "15px",
                        fontSize: "1.7em",
                        lineHeight: "0",
                        display: "inline-block",
                    }}
                >
                    Shop's Workplaces
                </h1>
                <h3
                    style={{
                        margin: "15px",
                        fontSize: "1.3em",
                        fontWeight: "normal",
                        lineHeight: "0",
                        display: "inline-block",
                    }}
                >
                    Workplaces: {workplaceList.length}
                </h3>
            </div>
            <Divider
                style={{ margin: "7px 0", borderWidth: "3px" }}
                variant="solid"
            />
            <div
                style={{
                    display: "flex",
                    flexWrap: "wrap",
                    justifyContent: "flex-start",
                    alignContent: "space-between",
                }}
            >
                {workplaceList.map((item) => (
                    <WorkplaceCard
                        WorkplaceDetails={item}
                        editFunction={() => {
                            setSelectedWorkplace(item);
                            setIsEditModalOpened(true);
                        }}
                        deleteFunction={() => {
                            setSelectedWorkplace(item);
                            setIsDeleteModalOpened(true);
                        }}
                    />
                ))}

                <DeleteWorkplaceModal
                    workplaceDetails={selectedWorkplace}
                    open={isDeleteModalOpened}
                    close={() => {
                        setIsDeleteModalOpened(false);
                        resetWorkplaceList();
                    }}
                />

                <EditWorkplaceModal
                    workplaceDetails={selectedWorkplace}
                    open={isEditModalOpened}
                    close={() => {
                        setIsEditModalOpened(false);
                        resetWorkplaceList();
                    }}
                />

                <AddNewWorkplaceCard
                    onClick={() => setIsAddNewWorkplaceModalOpened(true)}
                />
                <AddNewWorkplaceModal
                    open={isAddNewWorkplaceModalOpened}
                    close={() => {
                        setIsAddNewWorkplaceModalOpened(false);
                        resetWorkplaceList();
                    }}
                />
            </div>
        </div>
    );
};
