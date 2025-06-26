import { createAppSlice } from "@/lib/createAppSlice";
import type { AppThunk } from "@/lib/store";
import type { PayloadAction } from "@reduxjs/toolkit";

export interface AuthSliceState {
    isAuthenticated: boolean;
}

const initialState: AuthSliceState = {
    isAuthenticated: false
}

export const authSlice = createAppSlice({
    name: "auth",
    initialState,
    reducers: (create) => ({
        authenticate: create.reducer((state) => {
            state.isAuthenticated = true;
        }),
        unauthenticate: create.reducer((state) => {
            state.isAuthenticated = false;
        })
    }),
    selectors: {
        selectIsAuthenticated: (auth) => auth.isAuthenticated
    }
})

export const { authenticate, unauthenticate } = authSlice.actions;
export const { selectIsAuthenticated } = authSlice.selectors;