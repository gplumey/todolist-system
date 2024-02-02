import { useOutletContext } from "react-router-dom";
import { AppContext } from "../layouts/RootLayout";



export function useAppContext() {
    return useOutletContext<AppContext>()
}