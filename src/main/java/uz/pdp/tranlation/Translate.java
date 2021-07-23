package uz.pdp.tranlation;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import uz.pdp.model.Result;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Translate {

    public static final String APIKEY = "dict.1.1.20210602T105850Z.e0eda08b64127d04.6c7aaca5a62ecb6854f97fe7fbc1857038eb4af9";

    @SneakyThrows
    public static String[] GetLangs() {

        HttpGet httpGet = new HttpGet("https://dictionary.yandex.net/api/v1/dicservice.json/getLangs?key=" + APIKEY);
        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(httpGet);

        System.out.println(response.getEntity().getContent());

        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        Gson gson = new Gson();
        String[] result = gson.fromJson(reader, String[].class);

        System.out.println(Arrays.toString(result));
        return result;
    }

    @SneakyThrows
    public static Result lookUp(String lang, String text) {

        HttpGet httpGet = new HttpGet("https://dictionary.yandex.net/api/v1/dicservice.json/lookup?key=" + APIKEY + "&lang=" + lang + "&text=" + text);
        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(httpGet);

        System.out.println(response.getEntity().getContent());

        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        Gson gson = new Gson();
        Result result = gson.fromJson(reader, Result.class);

        System.out.println(result);
        return result;

    }

}
