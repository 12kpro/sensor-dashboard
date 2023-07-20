import { createSlice } from "@reduxjs/toolkit";
import { fetchUsers, createUser, updateUser, deleteUser } from "../action/users";
const initialState = {
  loading: false,
  available: null
};
//TODO: rivedere per la paginazione
const Users = createSlice({
  name: "users",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchUsers.pending, (state, action) => {
        state.loading = true;
      })
      .addCase(fetchUsers.fulfilled, (state, action) => {
        state.available = action.payload;
        state.loading = false;
      })
      .addCase(fetchUsers.rejected, (state, action) => {
        state.loading = false;
        state.available = [];
      })
      .addCase(createUser.pending, (state, action) => {
        state.loading = true;
      })
      .addCase(createUser.fulfilled, (state, action) => {
        const { userData } = action.payload;
        state.available.content = [userData, ...state.available.content];
        state.loading = false;
      })
      .addCase(createUser.rejected, (state, action) => {
        state.loading = false;
      })
      .addCase(updateUser.pending, (state, action) => {
        state.loading = true;
      })
      .addCase(updateUser.fulfilled, (state, action) => {
        const { userData } = action.payload;
        state.available.content = state.available.content.map((user) => {
          if (user.id !== userData.id) {
            return user;
          }
          return {
            ...userData
          };
        });
        state.loading = false;
      })
      .addCase(updateUser.rejected, (state, action) => {
        state.loading = false;
      })
      .addCase(deleteUser.pending, (state, action) => {
        state.loading = true;
      })
      .addCase(deleteUser.fulfilled, (state, action) => {
        const { id } = action.payload;
        state.available.content = state.available.content.filter((user) => user.id !== id);
        state.loading = false;
      })
      .addCase(deleteUser.rejected, (state, action) => {
        state.loading = false;
      });
  }
});

export const {} = Users.actions;

export default Users.reducer;
