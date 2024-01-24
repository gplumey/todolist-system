import { AppBar, Box, Breadcrumbs, Link, Toolbar, Typography } from "@mui/material"
import { Outlet } from "react-router-dom"
import AlertBanner from "../components/alertBanner/AlertBanner"
import { useState } from "react"
import { Todolist } from "../types"

type LinkInfo = {
    path: string
    label: string
}

export type AppContext = {
    setCurrentTodolist(totolist: Todolist): void
}

const todolistsLink: LinkInfo = { path: "/", label: "Todolists" }
const RootLayout = () => {
    const [links, setLink] = useState<Array<LinkInfo>>([todolistsLink])
    const context: AppContext = {
        setCurrentTodolist(todolist) {
            setLink([todolistsLink, { label: todolist.name, path: `/todolists/${todolist.id}` }])
        },
    }
    return <Box sx={{ flexGrow: 1 }}>
        <AppBar position="static">
            <Toolbar>
                <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                    Todo list
                </Typography>
            </Toolbar>
        </AppBar>
        <Breadcrumbs style={{ padding: 10 }} maxItems={4} aria-label="breadcrumb">
            {links.map((link, index) => <Link key={index} underline="hover" color="inherit" href={link.path}>{link.label}</Link>)}
        </Breadcrumbs>
        <AlertBanner />
        <Outlet context={context} />
    </Box >
}

export default RootLayout