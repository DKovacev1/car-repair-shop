import React from "react";
import { AppContext } from "../../AppContext";
import { AuthenticatedContent } from "./AuthenticatedContent";
import { UnauthenticatedContent } from "./UnauthenticatedContent";

export const PopoverContent = () => {
    const appContext = React.useContext(AppContext);

    return (
        <>
            {appContext.isAuthenticated ? (
                <AuthenticatedContent />
            ) : (
                <UnauthenticatedContent />
            )}
        </>
    );
};
