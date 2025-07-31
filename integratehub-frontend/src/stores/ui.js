import { defineStore } from "pinia";

export const useUIStore = defineStore("ui", {
    state: () => ({
        loading: false,
        error: null,
    }),
    actions: {
        setLoading(value) {
            this.loading = value;
        },
        setError(message) {
            this.error = message;
        },
        clearError() {
            this.error = null;
        },
    },
});
