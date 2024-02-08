import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Выполнение задачи следует начать с получения JSON из файла.
        // Сделайте это с помощью метода readString()
        String json = readString("data.json");
        //Прочитанный JSON необходимо преобразовать в список сотрудников.
        // Сделайте это с помощью метода jsonToList()
        List<Employee> list = jsonToList(json);

        for (Employee emp : list) {
            System.out.println(emp.toString());
        }
    }

    /*
    Метод readString() реализуйте самостоятельно с использованием BufferedReader и FileReader.
    Метод должен возвращать прочитанный из файла JSON типа String
     */
    public static String readString(String fileName) {
        StringBuilder str = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                str.append(line);
                //System.out.println(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(str.toString());
        return str.toString().replaceAll("\\s+", "");
    }

    public static List<Employee> jsonToList(String myjson) {
        List<Employee> list = new ArrayList<>();
        JSONArray jsonArr;
        try {
            jsonArr = (JSONArray) new JSONParser().parse(myjson);

            for (int i = 0; i < jsonArr.size(); i++) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                JSONObject jsonObj = (JSONObject) jsonArr.get(i);
                Employee employee = gson.fromJson(String.valueOf(jsonObj), Employee.class);
                list.add(employee);
                //
                //System.out.println( employee.toString() );
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return list;
    }

}
