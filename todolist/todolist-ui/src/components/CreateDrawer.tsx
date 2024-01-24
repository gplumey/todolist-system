import { Button, Drawer, DrawerProps, Stack, Typography } from "@mui/material"


type CreateDrawerProps = DrawerProps & {
    title: string
    open: boolean
    isDisabled: boolean
    onCreateClick: () => void
    hide: () => void
    children: React.ReactNode
}

const CreateDrawer = (props: CreateDrawerProps) => {
    const { title, open, isDisabled, onCreateClick, hide, children } = props
    return <Drawer open={open} anchor="bottom">
        <Stack style={{ padding: 10 }}>
            <Typography variant='h5'>
                {title}
            </Typography>
            {children}
            <Stack direction="row" justifyContent={'flex-end'} spacing={1}>
                <Button onClick={() => hide()}>Cancel</Button>
                <Button variant="contained" disabled={isDisabled} onClick={
                    () => onCreateClick()}>Create</Button>
            </Stack>
        </Stack>
    </Drawer>
}

export default CreateDrawer