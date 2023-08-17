package com.app.travelx.weather;

import com.amadeus.exceptions.ResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.concurrent.*;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService service;

//    @GetMapping()
//    public ResponseEntity<WeatherModel> weather(@RequestParam(required = true) String latitude,
//            @RequestParam(required = true) String longitude,
//            @RequestParam(required = true) String date) throws ResponseException {
//        return service.getWeather(latitude, longitude, date);
//    }
//
//
//    @Timed
//    @Transactional(timeout = 120)
//    @GetMapping("/week")
//    public ArrayList<WeatherModel> detailWeather(@RequestParam(required = true) String latitude,
//                                                                 @RequestParam(required = true) String longitude,
//                                                                 @RequestParam(required = true) String date) throws ResponseException {
//
//
//        return null;
//





        public void test() {

            float latitude = (float) 52.21;
            float longitude = (float) -13.01;
            String date = "2023-08-23";
            ExecutorService executor = Executors.newSingleThreadExecutor();

            // Wrap the API call in a Callable
            Callable<String> apiCall = () -> {
                // Simulate an API call
                Thread.sleep(3000);
                return "API call result";
            };

            // Submit the Callable to the executor service.getWeather(latitude, longitude, date)
            Future<String> future = executor.submit(apiCall);

            try {
                // Wait for the result with a timeout
                String result = future.get(2, TimeUnit.SECONDS);
                System.out.println("API call result: " + result);
            } catch (TimeoutException e) {
                System.out.println("API call timed out");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                executor.shutdown();
            }
        }



//        @GetMapping("/w")
//    public Callable<String> detailWeather(@RequestParam(required = true) String latitude,
//                                          @RequestParam(required = true) String longitude,
//                                          @RequestParam(required = true) String date) throws InterruptedException {
//        return new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//
//                Thread.sleep(8000); //this will cause a timeout
//                return "foobar";
//            }
//        };
//    }


}
