package hello;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
   private Connection dbConnection;

   public GreetingController() throws SQLException {
      dbConnection = DriverManager.getConnection(
              "jdbc:mysql://Data Source=ap-cdbr-azure-southeast-b.cloudapp.net;"
              + "database=creditcard_db;"
              + "User Id=b3c5016a02f8e2;"
              + "Password=ae5686cd");
   }

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
           throws JSONException, IOException, SQLException {

      URL url = new URL(("http://uhac.mybluemix.net/SimpleServlet?"
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

      JSONTokener token = new JSONTokener(new InputStreamReader(url.openStream()));
      JSONObject obj = new JSONObject(token);
      double predictedAppraisal = obj.getDouble("appraisal");

      Record record = new Record(age, sex, civil, children, car, house, subdivision,
                                 employment, annum, assets, liabilities, predictedAppraisal);

      String query = "INSERT INTO DATA"
              + "(age, sex, civil_status, children, own_car, house, subdivision, employment, net_per_annum, assets, liabilities, predicted_appraisal)"
              + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

      PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
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
      preparedStatement.setDouble(12, predictedAppraisal);

      preparedStatement.executeUpdate();
      dbConnection.close();

      return record;
   }

   @RequestMapping("/getAll")
   public ArrayList<Record> getAll() throws SQLException {
      ArrayList<Record> records = new ArrayList<>();

      PreparedStatement p = dbConnection.prepareStatement("SELECT * FROM data");
      ResultSet rs = p.executeQuery();

      while (rs.next()) {
         records.add(new Record(
            rs.getInt(1),
            rs.getString(2),
            rs.getString(3),
            rs.getInt(4),
            rs.getInt(5),
            rs.getString(6),
            rs.getString(7),
            rs.getString(8),
            rs.getInt(9),
            rs.getInt(10),
            rs.getInt(11),
            rs.getDouble(12)));
      }
      return records;
   }
}
