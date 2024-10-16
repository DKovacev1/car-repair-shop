import { Card, Divider, Flex } from "antd";
import { useCars } from "../hooks";
import { CarCard } from "../components/Cards/CarCard";
import { AddNewCarCard } from "../components/Cards/AddNewCarCard";

export const MyCarsPage = ({ userId }) => {
    const [carsList] = useCars({ userId });

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
                    <CarCard carDetails={item}/>
                ))}
                <AddNewCarCard />
            </div>
        </div>
    );
};
