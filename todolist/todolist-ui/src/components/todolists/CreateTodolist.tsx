import { useState } from 'react'
import { TextField } from '@mui/material'
import { useTodolists } from '../../hooks/useTodolists'
import { useAppState } from '../../hooks/useAppState'
import CreateDrawer from '../CreateDrawer'

type Form = {
    name: string
}
type CreateTodolistProps = {
    open: boolean
    hide: () => void
}
const initialForm: Form = {
    name: ''
}
const CreateTodolist = ({ open, hide }: CreateTodolistProps) => {
    const [data, setData] = useState<Form>(initialForm)
    const { createTodolist, isLoading } = useTodolists({
        onCreateSuccess: () => {
            setData(initialForm)
            hide()
        }
    })
    const { clearErrorMessage } = useAppState()
    const isDisabled = isLoading || (data.name.length > 0 ? false : true)

    return <CreateDrawer
        title="New todo list"
        hide={hide}
        isDisabled={isDisabled}
        open={open}
        onCreateClick={() => createTodolist(data.name)}>
        <TextField style={{ paddingTop: 10, paddingBottom: 10 }}
            required
            id="todolist-name"
            label="Name"
            variant="standard"
            value={data.name}
            onChange={(event) => {
                clearErrorMessage()
                setData({ ...data, name: event.target.value })
            }}
        />
    </CreateDrawer>

}

export default CreateTodolist