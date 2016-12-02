package hello;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

    @RequestMapping("/add")
    public Record add(@RequestParam(value="age") int age,
            @RequestParam(value="sex") String sex,
            @RequestParam(value="civil") String civil,
            @RequestParam(value="children") int children,
            @RequestParam(value="car") 			String car,
            @RequestParam(value="house") 			String house,
            @RequestParam(value="subdivision") 	String subdivision,
            @RequestParam(value="employment") 	String employment,
            @RequestParam(value="annum") 			int annum,
            @RequestParam(value="assets") 		int assets,
            @RequestParam(value="liabilities") 	int liabilities
            ) throws JSONException, IOException {
        
        URL url = new URL(("http://uhac.mybluemix.net/SimpleServlet?"
                + "age=" + age
                + "&sex=" + sex
                + "&civil=" + civil
                + "&children=" + children
                + "&car=" + car
                + "&house="       +house
                + "&subdivision=" +subdivision
                + "&employment="  +employment
                + "&annum="       +annum
                + "&assets="      +assets
                + "&liabilities=" +liabilities).replaceAll(" ", "%20"));
        JSONTokener token = new JSONTokener(new InputStreamReader(url.openStream()));
        JSONObject obj = new JSONObject(token);
        double appraisal = obj.getDouble("appraisal");
        return new Record(appraisal);
    }
}
