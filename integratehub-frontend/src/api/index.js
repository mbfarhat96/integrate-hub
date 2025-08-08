import axios from "axios";

const api = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL || "http://localhost:8080",
});

async function apiRequest(fn) {
    try {
        const res = await fn();
        return res.data;
    } catch (err) {
        const message =
            err.response?.data?.message ||
            err.response?.data?.error ||
            err.response?.data?.details?.join?.(", ") ||
            "Something went wrong while calling the server.";
        throw new Error(message);
    }
}

export const weatherApi = {
    getWeather(city) {
        return apiRequest(() =>
            api.get("/api/weather", {
                params: { city },
            })
        );
    },
};



export const githubApi = {
    getUser(username) {
        return apiRequest(() => api.get(`/api/github/user/${username}`));
    },
};

export const currencyApi = {
    convert(from, to, amount) {
        return apiRequest(() =>
            api.get("/api/currency", {
                params: { from, to, amount },
            })
        );
    },
};

export const stockApi = {
    quote(symbol) {
        return apiRequest(() =>
            api.get("/api/stocks/quote", {
                params: { symbol },
            })
        );
    },
    search(query) {
        return apiRequest(() =>
            api.get("/api/stocks/search", {
                params: { query },
            })
        );
    },
};

export const newsApi = {
    search(query) {
        return apiRequest(() =>
            api.get("/api/news", {
                params: { query },
            })
        );
    },
};

export const healthApi = {
    systemHealth() {
        return apiRequest(() => api.get("/api/system-health"));
    },
};