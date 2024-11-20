import { Divider } from "antd";
import { useCars } from "../hooks";
import { useState } from "react";
import { DeleteCarModal, EditCarModal, AddNewCarCard, CarCard, AddNewCarModal } from "../components";

export const MyCarsPage = () => {
    const [carsList, resetCars] = useCars();

    const [selectedCar, setSelectedCar] = useState({});

    const [isAddNewCarModalOpened, setIsAddNewCarModalOpened] = useState(false);
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
                    My Cars
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
                    Cars: {carsList.length}
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
                {carsList.map((item) => (
                    <CarCard
                        carDetails={item}
                        editFunction={() => {
                            setSelectedCar(item);
                            setIsEditModalOpened(true);
                        }}
                        deleteFunction={() => {
                            setSelectedCar(item);
                            setIsDeleteModalOpened(true);
                        }}
                    />
                ))}

                <DeleteCarModal
                    carDetails={selectedCar}
                    open={isDeleteModalOpened}
                    close={() => {
                        setIsDeleteModalOpened(false);
                        resetCars();
                    }}
                />

                <EditCarModal
                    carDetails={selectedCar}
                    open={isEditModalOpened}
                    close={() => {
                        setIsEditModalOpened(false);
                        resetCars();
                    }}
                />

                <AddNewCarCard onClick={() => setIsAddNewCarModalOpened(true)}/>
                <AddNewCarModal 
                    open={isAddNewCarModalOpened}
                    close={() => {
                        setIsAddNewCarModalOpened(false);
                        resetCars();
                    }}
                />
            </div>
        </div>
    );
};
