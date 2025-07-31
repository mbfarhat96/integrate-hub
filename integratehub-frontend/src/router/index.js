import { createRouter, createWebHistory } from "vue-router";

import WeatherPage from "../pages/WeatherPage.vue";
import GitHubPage from "../pages/GitHubPage.vue";
import CurrencyPage from "../pages/CurrencyPage.vue";
import StocksPage from "../pages/StocksPage.vue";
import NewsPage from "../pages/NewsPage.vue";
import SystemHealthPage from "../pages/SystemHealthPage.vue";

const routes = [
    { path: "/", redirect: "/system" },
    { path: "/weather", component: WeatherPage },
    { path: "/github", component: GitHubPage },
    { path: "/currency", component: CurrencyPage },
    { path: "/stocks", component: StocksPage },
    { path: "/news", component: NewsPage },
    { path: "/system", component: SystemHealthPage },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
