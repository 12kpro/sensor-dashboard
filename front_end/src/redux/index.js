import { combineReducers, configureStore } from "@reduxjs/toolkit";
import { encryptTransform } from "redux-persist-transform-encrypt";
import { persistStore, persistReducer } from "redux-persist";
import storage from "redux-persist/lib/storage";
import auth from "./slices/auth";
import sensors from "./slices/sensors";
import roles from "./slices/roles";
import um from "./slices/um";
import users from "./slices/users";

const persistConfig = {
  key: "root",
  storage,
  whitelist: ["auth"],
  transforms: [
    encryptTransform({
      secretKey: process.env.REACT_APP_SECRET
    })
  ]
};

const rootReducer = combineReducers({
  auth,
  sensors,
  roles,
  um,
  users
});

const persistedReducer = persistReducer(persistConfig, rootReducer);

export const store = configureStore({
  reducer: persistedReducer,
  middleware: (getDefaultMiddleware) => getDefaultMiddleware({ serializableCheck: false })
});

export const persistor = persistStore(store);
