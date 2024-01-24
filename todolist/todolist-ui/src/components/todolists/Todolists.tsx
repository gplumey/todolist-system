
import { List, ListItemText, ListItemButton, Box, Tooltip, IconButton } from '@mui/material'
import { Delete } from '@mui/icons-material'
import { useTodolists } from "../../hooks/useTodolists"
import { useNavigate } from 'react-router-dom'
import DeleteConfirmDialog from '../DeleteConfirmDialog'
import { useState } from 'react'
import { useDependency } from '../../services/ServiceProvider'
import { useMutation } from 'react-query'

const Todolists = () => {
    const { todolistService } = useDependency()
    const { todolists, refresh } = useTodolists()
    const [toDeleteItem, setToDeleteItem] = useState<null | string>(null)
    const { mutate: deleteTodolist } = useMutation(todolistService.delete, {
        onSuccess: () => {
            setToDeleteItem(null)
            refresh()
        }
    })
    const navigate = useNavigate()
    return <Box>
        <DeleteConfirmDialog open={toDeleteItem != null} close={() => { setToDeleteItem(null) }} confirm={() => {
            toDeleteItem && deleteTodolist(toDeleteItem)
        }} message={`It will be definitly deleted`} />
        <List>
            {todolists.map(todolist => <ListItemButton key={todolist.id} onClick={() => {
                console.log("Click", todolist.id)
                navigate("/todolists/" + todolist.id)
            }}>
                <ListItemText primary={todolist.name} />
                <Tooltip title="Delete">
                    <IconButton onClick={(event) => {
                        event.preventDefault()
                        event.stopPropagation()
                        setToDeleteItem(todolist.id)
                    }}>
                        <Delete />
                    </IconButton>
                </Tooltip></ListItemButton>)}
        </List>
    </Box >
}
export default Todolists