package dnhieuhuy.hoanghuy.translationgoogleapi.transhelper

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

/**
 * Created by Administrator on 17/07/2017.
 */
class GoogleTranslateHelper
{
    private var key: String? = null

    constructor(apiKey: String)
    {
        key = apiKey
    }

    fun translte(text: String, from: String, to: String): String? {
        var result: StringBuilder = StringBuilder()
        try {
            var encodedText: String = URLEncoder.encode(text, "UTF-8")
            var urlStr: String = "https://www.googleapis.com/language/translate/v2?key=" +
                    key + "&q=" +
                    encodedText + "&target=" + to +
                    "&source=" + from

            var url: URL = URL(urlStr)

            var conn: HttpURLConnection = url.openConnection() as HttpURLConnection
            var stream: InputStream

            if (conn.responseCode == 200) //success
            {
                stream = conn.inputStream
            } else {
                stream = conn.errorStream
            }

            var reader: BufferedReader = BufferedReader(InputStreamReader(stream))
            var line: String

            while (reader.readLine() != null) {
                line = reader.readLine()
                result.append(line)
            }

            var parser: JsonParser = JsonParser()
            var element: JsonElement = parser.parse(reader.toString())

            if (element.isJsonObject()) {
                var obj: JsonObject = element.getAsJsonObject();
                if (obj.get("error") == null) {
                    var translatedText: String = obj.get("data").getAsJsonObject().
                            get("translations").getAsJsonArray().
                            get(0).getAsJsonObject().
                            get("translatedText").getAsString();
                    return translatedText
                }
            }
            if (conn.getResponseCode() != 200) {
                System.err.println(result)
            }


        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }
}