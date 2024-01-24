import { useState } from 'react'
import { TextField } from '@mui/material'
import { useTodos } from '../../hooks/useTodos'
import CreateDrawer from '../CreateDrawer'

type Form = {
    label: string
}

const initialForm: Form = { label: '' }

type CreateTodoProps = { todolistId: string, open: boolean, hide: () => void }

const CreateTodo = ({ todolistId, hide, open }: CreateTodoProps) => {
    const [data, setData] = useState<Form>(initialForm)
    const { createTodo, isLoading } = useTodos(todolistId, {
        onCreateSuccess: () => {
            setData(initialForm)
            hide()
        }
    })
    const isDisabled = isLoading || (data.label.length > 0 ? false : true)

    return <CreateDrawer title="New todo" hide={hide} isDisabled={isDisabled} open={open} onCreateClick={() => createTodo(data.label)}>
        <TextField
            required
            style={{ paddingTop: 10, paddingBottom: 10 }}
            id="todolist-name"
            label="Name"
            variant="standard"
            value={data.label}
            onChange={(event) => {
                setData({ ...data, label: event.target.value })
            }}
        />
    </CreateDrawer>

}

export default CreateTodo