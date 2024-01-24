import { Alert } from '@mui/material'
import { useAppState } from "../../hooks/useAppState"

const AlertBanner = () => {
    const { errorMessage } = useAppState()
    return <>{errorMessage && <Alert severity="error">{errorMessage}
    </Alert>} </>
}

export default AlertBanner