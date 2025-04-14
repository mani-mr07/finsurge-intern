public class DataOperation {
    public static void main(String[] args) {
        String givenDate="31-12-2000";
        int addDay=3;
        int day;
        int month;
        int year;

        day=Integer.parseInt(givenDate.substring(0,2));
        month=Integer.parseInt(givenDate.substring(3,5));

        year=Integer.parseInt(givenDate.substring(6,givenDate.length()));
        Date date=new Date(day,month,year);
        date.addaDays(addDay);
        System.out.println(date.toString());

    }
}
