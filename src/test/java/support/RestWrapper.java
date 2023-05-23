package support;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class RestWrapper {

    private String baseUrl = "https://skryabin.com/recruit/api/v1/";
    private static String loginToken;

    private static Map<String, Object> lastPosition;

    private static JsonPath metadata;

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String JSON = "application/json";
    public static final String AUTH = "Authorization";

    public static Map<String, Object> getLastPosition() {
        return lastPosition;
    }

    public void login(Map<String, String> credentials) {

        // prepare
        RequestSpecification request = RestAssured
                .given()
                .log().all()
                .header(CONTENT_TYPE, JSON)
                .body(credentials);

        // execute
        Response response = request
                .post(baseUrl + "login");

        // verify and extract
        Map<String, String> result = response
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getMap("");

        loginToken = "Bearer " + result.get("token");
        getMetadata();
    }

    public Map<String, Object> createPosition(Map<String, String> position) {

        String dateOpen = position.get("dateOpen");
        String isoDateOpen = new SimpleDateFormat("yyyy-MM-dd").format(new Date(dateOpen));
        position.put("dateOpen", isoDateOpen);
        // prepare
        RequestSpecification request = RestAssured
                .given()
                .log().all()
                .header(CONTENT_TYPE, JSON)
                .header(AUTH, loginToken)
                .body(position);

        // execute
        Response response = request
                .post(baseUrl + "positions");

        // verify and extract
        Map<String, Object> result = response
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .jsonPath()
                .getMap("");

        lastPosition = result;

        return result;
    }

    public List<Map<String, Object>> getPositions() {
        // prepare
        RequestSpecification request = RestAssured
                .given()
                .log().all();

        // execute
        Response response = request.get(baseUrl + "positions");

        // extract and verify
        List<Map<String, Object>> result = response
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("");

        assertMetadata(result.get(0), "positions");
        return result;
    }

    public Map<String, Object> updatePosition(Map<String, String> updatedFields, Object id) {
        // prepare
        RequestSpecification request = RestAssured
                .given()
                .log().all()
                .header(CONTENT_TYPE, JSON)
                .header(AUTH, loginToken)
                .body(updatedFields);

        // execute
        Response response = request
                .put(baseUrl + "positions/" + id);

        // verify
        Map<String, Object> result = response
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getMap("");

//        lastPosition = result;

        for (String key : result.keySet()) {
            lastPosition.put(key, result.get(key));
        }

        return result;
    }

    public Map<String, Object> getPositionById(Object id) {
        Map<String, Object> result = RestAssured
                .given()
                .log().all()
                .when()
                .get(baseUrl + "positions/" + id)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getMap("");

        assertMetadata(result, "positions");
        return result;
    }

    public void deletePositionById(Object id) {
        RestAssured
                .given()
                .log().all()
                .header(AUTH, loginToken)
                .when()
                .delete(baseUrl + "positions/" + id)
                .then()
                .log().all()
                .statusCode(204)
                .header("X-Frame-Options", "SAMEORIGIN");
    }

    public JsonPath getMetadata() {
        JsonPath result = RestAssured
                .given()
                .log().all()
                .when()
                .get(baseUrl + "swagger.json")
                .then()
                .log().headers()
                .extract()
                .jsonPath();

        metadata = result;

        return result;
    }

    private static void assertMetadata(Map<String, Object> actual, String type) {
        for (String key : actual.keySet()) {
            if (actual.get(key) != null) {
                String actualDataType = actual.get(key).getClass().toString();
                actualDataType = actualDataType.substring(actualDataType.lastIndexOf('.') + 1);
                String expectedDataType = metadata.getString("definitions." + type + ".properties." + key + ".type");
                assertThat(actualDataType).isEqualToIgnoringCase(expectedDataType);
            }
        }
    }
}
