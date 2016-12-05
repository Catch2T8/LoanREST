package hello;

import com.mysql.jdbc.Driver;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String HOST = "jdbc:mysql://ap-cdbr-azure-southeast-b.cloudapp.net/creditcard_db";
    private static final String USER = "b3c5016a02f8e2";
    private static final String PASSWORD = "ae5686cd";
    private static final String INSERT = "INSERT INTO DATA (age, "
            + "sex, civil_status, children, own_car, house, subdivision, "
            + "employment, net_per_annum, assets, liabilities, predicted_appraisal)"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping("/add")
    public Record add(@RequestParam(value = "age") int age,
            @RequestParam(value = "sex") String sex,
            @RequestParam(value = "civil") String civil,
            @RequestParam(value = "children") int children,
            @RequestParam(value = "car") int car,
            @RequestParam(value = "house") String house,
            @RequestParam(value = "subdivision") String subdivision,
            @RequestParam(value = "employment") String employment,
            @RequestParam(value = "annum") int annum,
            @RequestParam(value = "assets") int assets,
            @RequestParam(value = "liabilities") int liabilities
    )
            throws JSONException, IOException, SQLException, ClassNotFoundException {

        /*Retrieve suggested appraisal from the machine learning web service*/
        URL mlConnect = new URL(("http://uhac.mybluemix.net/SimpleServlet?"
                + "age=" + age
                + "&sex=" + sex
                + "&civil=" + civil
                + "&children=" + children
                + "&car=" + (car == 1 ? "yes" : "no")
                + "&house=" + house
                + "&subdivision=" + subdivision
                + "&employment=" + employment
                + "&annum=" + annum
                + "&assets=" + assets
                + "&liabilities=" + liabilities)
                .replaceAll(" ", "%20"));
        JSONTokener token = new JSONTokener(new InputStreamReader(
                mlConnect.openStream()));
        JSONObject obj = new JSONObject(token);
        double appraisal = obj.getDouble("appraisal");

        /*Add the prospect client to the database*/
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection dbConnection = DriverManager
                .getConnection(HOST, USER, PASSWORD);
                PreparedStatement preparedStatement
                        = dbConnection.prepareStatement(INSERT)) {
            preparedStatement.setInt(1, age);
            preparedStatement.setString(2, sex);
            preparedStatement.setString(3, civil);
            preparedStatement.setInt(4, children);
            preparedStatement.setInt(5, car);
            preparedStatement.setString(6, house);
            preparedStatement.setString(7, subdivision);
            preparedStatement.setString(8, employment);
            preparedStatement.setInt(9, annum);
            preparedStatement.setInt(10, assets);
            preparedStatement.setInt(11, liabilities);
            preparedStatement.setDouble(12, appraisal);
            preparedStatement.executeUpdate();
        }

        return new Record(age, sex, civil, children, car, house, subdivision, employment, annum, assets, liabilities, appraisal);
    }
}
