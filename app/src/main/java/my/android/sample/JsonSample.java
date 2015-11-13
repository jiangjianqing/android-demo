package my.android.sample;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import my.android.model.Person;
import my.android.utils.LogUtil;

/**
 * Created by ztxs on 15-11-13.
 */
public class JsonSample {
    public static void illustrateGson(){
        //demonstrate how to use gson
        String strJson="{\"name\":\"Tom\",\"age\":20}";
        Gson gson = new Gson();
        Person person = gson.fromJson(strJson, Person.class);
        LogUtil.i("MyApplication", "person.name=" + person.getName());
        LogUtil.i("MyApplication","person.age="+person.getAge());

        String strJsonArray="[{\"name\":\"Tom\",\"age\":20},{\"name\":\"Jemi\",\"age\":35}]";
        List<Person> peopleArray = gson.fromJson(strJsonArray, new TypeToken<List<Person>>(){}.getType());
        LogUtil.i("MyApplication","person array.size="+peopleArray.size());
    }
}
