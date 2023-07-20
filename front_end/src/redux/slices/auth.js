import { createSlice } from "@reduxjs/toolkit";
import { fetchUserData, updateUserCredential, updateAuthUser, deleteAuthUser, login, signOut } from "../action/auth";

const initialState = {
  token: null,
  loading: false,
  userData: null,
  loginError: "",
  bookmarks: []
};

export const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    addSensorToBookmark: (state, action) => {
      const bookMarkExits = state.bookmarks.find((id) => id === action.payload);
      if (!bookMarkExits) {
        state.bookmarks = [...state.bookmarks, action.payload];
      }
    },
    removeSensorFromBookmark: (state, action) => {
      state.bookmarks = state.bookmarks.filter((id) => id !== action.payload);
    }
  },
  extraReducers: (builder) => {
    builder
      .addCase(signOut.fulfilled, (state, action) => {
        state.loading = false;
        state.userData = null;
        state.token = null;
        state.loginError = "";
      })
      .addCase(login.pending, (state, action) => {
        state.loading = true;
        state.loginError = "";
      })
      .addCase(login.fulfilled, (state, action) => {
        const { accessToken } = action.payload;
        state.token = accessToken;
        state.loginError = "";
        state.loading = false;
      })
      .addCase(login.rejected, (state, action) => {
        state.loginError = action.payload.message;
        state.loading = false;
      })
      .addCase(fetchUserData.pending, (state, action) => {
        state.loading = true;
      })
      .addCase(fetchUserData.fulfilled, (state, action) => {
        const { accessToken, user } = action.payload;
        state.token = accessToken;
        state.userData = user;
        state.loading = false;
      })
      .addCase(fetchUserData.rejected, (state, action) => {
        state.loading = false;
        state.userData = null;
        state.token = null;
      })
      .addCase(deleteAuthUser.pending, (state, action) => {
        state.loading = true;
      })
      .addCase(deleteAuthUser.fulfilled, (state, action) => {
        state.token = null;
        state.userData = null;
        state.loading = false;
      })
      .addCase(deleteAuthUser.rejected, (state, action) => {
        state.loading = false;
      })
      .addCase(updateAuthUser.pending, (state, action) => {
        state.loading = true;
      })
      .addCase(updateAuthUser.fulfilled, (state, action) => {
        const { user } = action.payload;
        state.userData = user;
        state.loading = false;
      })
      .addCase(updateAuthUser.rejected, (state, action) => {
        state.loading = false;
      })
      .addCase(updateUserCredential.pending, (state, action) => {
        state.loading = true;
      })
      .addCase(updateUserCredential.fulfilled, (state, action) => {
        const { accessToken } = action.payload;
        state.token = accessToken;
        state.loading = false;
      })
      .addCase(updateUserCredential.rejected, (state, action) => {
        state.loading = false;
        // state.userData = null;
        // state.token = null;
      });
  }
});

export const { addSensorToBookmark, removeSensorFromBookmark } = authSlice.actions;

export default authSlice.reducer;
