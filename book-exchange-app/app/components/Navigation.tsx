'use client'
import * as React from 'react'
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import { AccountCircle, Book, Home } from '@mui/icons-material';

import {
    authenticate,
    selectIsAuthenticated
} from "@/lib/features/auth/authSlice"
import { useAppDispatch, useAppSelector } from '@/lib/hooks';
import { useRouter } from 'next/navigation';

import { Drawer, List, ListItem, ListItemButton, ListItemIcon, ListItemText } from "@mui/material";
import { useState } from "react";

type SideBarProps = {
    open: boolean,
    onClose: Function
}

type SideBarItemProps = {
    text: string,
    iconClass: any,
    navigationRoute: string
}

const SideBarItem = (props: SideBarItemProps) => {
    const { text, iconClass, navigationRoute } = props
    const router = useRouter()

    return (
        <ListItem key={text} disablePadding>
            <ListItemButton onClick={() => router.push(navigationRoute)}>
                <ListItemIcon>
                    {React.createElement(iconClass, {})}
                </ListItemIcon>
                <ListItemText primary={text} />
            </ListItemButton>
        </ListItem>
    )
}

const SideBar = (props: SideBarProps) => {
    const { open, onClose } = props
    const router = useRouter()

    return(
        <Drawer
            anchor="left"
            open={open}
            onClose={() => onClose()}
        >
            <Box
                sx={{width: 250}}
                role="presentation"    
            >
                <List>
                    <SideBarItem text="Home" iconClass={Home} navigationRoute='/' />
                    <SideBarItem text="Books" iconClass={Book} navigationRoute='/books' />
                </List>
            </Box>
        </Drawer>
    )
}

type ButtonAppBarProps = {
    onClickMenuButton: Function
}

const ButtonAppBar = (props: ButtonAppBarProps) => {
    const { onClickMenuButton } = props;
    const dispatch = useAppDispatch();
    const router = useRouter()
    const isAuthenticated = useAppSelector(selectIsAuthenticated);
    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static">
                <Toolbar>
                    <IconButton
                        size="large"
                        edge="start"
                        color="inherit"
                        aria-label="menu"
                        sx={{ mr: 2 }}
                        onClick={() => onClickMenuButton()}
                    >
                        <MenuIcon />
                    </IconButton>
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                        Book Exchange
                    </Typography>
                    {isAuthenticated === true ? (
                        <IconButton
                            size="large"
                            aria-label="account of current user"
                            aria-controls="menu-appbar"
                            aria-haspopup="true"
                            color="inherit"
                            onClick={() => router.push('/profile')}
                        >
                            <AccountCircle/>
                        </IconButton>
                        ) : (
                            <Button color='inherit' onClick={() => {
                                dispatch(authenticate())
                            }}>Sign in</Button>
                        )
                    }
                </Toolbar>
            </AppBar>
        </Box>
    )
}

export default function Navigation() {
    const [openSideBar, setOpenSideBar] = useState(false)
    return (<>
        <ButtonAppBar onClickMenuButton={() => setOpenSideBar(!openSideBar)}/>
        <SideBar open={openSideBar} onClose={() => setOpenSideBar(false)}/>
    </>)
}