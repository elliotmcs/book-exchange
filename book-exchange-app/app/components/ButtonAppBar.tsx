'use client'
import * as React from 'react'
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import { AccountCircle } from '@mui/icons-material';

import {
    authenticate,
    selectIsAuthenticated
} from "@/lib/features/auth/authSlice"
import { useAppDispatch, useAppSelector } from '@/lib/hooks';
import { useRouter } from 'next/navigation';

export default function ButtonAppBar() {
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