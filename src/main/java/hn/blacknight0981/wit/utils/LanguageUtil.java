package hn.blacknight0981.wit.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import hn.blacknight0981.wit.Wit;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class LanguageUtil {
    public static final Map<String, String> language = new HashMap<>();

    private LanguageUtil() {}

    private static String getMinecraftVersionFromServer(String fullVersion) {

        return fullVersion.substring(fullVersion.indexOf("MC:") + 4, fullVersion.length() - 1);
    }

    public static void loadLanguage(String fullVersion, String minecraftLocale) {
        String version = getMinecraftVersionFromServer(fullVersion);
        String url = "https://raw.githubusercontent.com/InventivetalentDev/minecraft-assets/" + version + "/assets/minecraft/lang/" + minecraftLocale + ".json";
        try {
            URI uri = new URI(url);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(uri).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String jsonResponse = response.body();
                Gson gson = new Gson();
                language.putAll(gson.fromJson(jsonResponse, new TypeToken<Map<String, String>>(){}.getType()));

                Wit.getInstance().getLogger().info("Load " + minecraftLocale + " language");
            } else {
                Wit.getInstance().getLogger().warning("Unable to load " + minecraftLocale + " language - " + response.statusCode());
            }
        } catch (Exception e) {
            Wit.getInstance().getLogger().severe("Unable to load " + minecraftLocale + " language error - " + e.getMessage());
        }
    }

    public static String getTranslation(String key) {
        return language.getOrDefault(key, key);
    }
}
