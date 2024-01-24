import { Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle } from "@mui/material"

type DeleteConfirmDialogProps = {
    open: boolean
    message: string
    close: () => void
    confirm: () => void
}

const DeleteConfirmDialog = ({ open, message, close, confirm }: DeleteConfirmDialogProps) => {
    return <Dialog open={open}>
        <DialogTitle>
            Delete ?
        </DialogTitle>
        <DialogContent>
            <DialogContentText id="alert-dialog-description">
                {message}
            </DialogContentText>
        </DialogContent>
        <DialogActions>
            <Button onClick={() => close()}>No</Button>
            <Button onClick={() => confirm()} autoFocus>
                Yes
            </Button>
        </DialogActions>
    </Dialog >
}

export default DeleteConfirmDialog