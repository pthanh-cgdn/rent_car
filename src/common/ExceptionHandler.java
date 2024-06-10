package common;

import repositories.CarRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

public class ExceptionHandler {
    private static final Scanner scanner = new Scanner(System.in);
    public static boolean isFormatDate(String date, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    public static String checkDateFormat() {
        String line;
        do {
            line = scanner.nextLine();
            if (!isFormatDate(line, "dd-MM-yyyy")) {
                System.out.println("Please enter a valid date (DD-MM-YYYY)");
            }
        } while (!isFormatDate(line, "dd-MM-yyyy"));
        return line;
    }
    public static String checkAndCompareDay(String startDay) {
        String endDay;
        Date day1;
        Date day2;
        do {
            endDay = checkDateFormat();
            day1 = new Date(Integer.parseInt(startDay.substring(0,2)),Integer.parseInt(startDay.substring(3,5)),Integer.parseInt(startDay.substring(6,10)));
            day2 = new Date(Integer.parseInt(endDay.substring(0,2)),Integer.parseInt(endDay.substring(3,5)),Integer.parseInt(endDay.substring(6,10)));
            if(!day2.after(day1)){
                System.out.println("The end day need to be after the start day:");
            }
        } while(!day2.after(day1));
        return endDay;
    }
    public static String checkCarExists(){
        CarRepository carRepository = new CarRepository();
        String carId;
        do{
            System.out.println("Please enter the car id");
            carId = scanner.nextLine();
            if(carRepository.containCarId(carId) == null){
                System.out.println("Car does not exist");
            }
        } while (carRepository.containCarId(carId) == null);
        return carId;
    }

}
